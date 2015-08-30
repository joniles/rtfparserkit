/*
 * Copyright 2013 Jon Iles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rtfparserkit.parser.standard;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import com.rtfparserkit.parser.IRtfListener;
import com.rtfparserkit.parser.IRtfParser;
import com.rtfparserkit.parser.IRtfSource;
import com.rtfparserkit.parser.raw.RawRtfParser;
import com.rtfparserkit.rtf.Command;
import com.rtfparserkit.rtf.CommandType;

/**
 * This class builds on the RawRtfParser to provide a parser which can
 * deal with character encodings and Unicode. All of the character data it reads
 * is presented back to the client as Unicode strings to make it as simple as
 * possible to deal with.
 */
public class StandardRtfParser implements IRtfParser, IRtfListener
{
   /**
    * Main entry point: parse RTF data from the input stream, and pass events based on
    * the RTF content to the listener.
    */
   @Override
   public void parse(IRtfSource source, IRtfListener listener) throws IOException
   {
      handler = new DefaultEventHandler(listener);
      IRtfParser reader = new RawRtfParser();
      reader.parse(source, this);
   }

   /**
    * Handle event from the RawRtfParser.
    */
   @Override
   public void processGroupStart()
   {
      handleEvent(GROUP_START);
      stack.push(state);
      state = new ParserState(state);
   }

   /**
    * Handle event from the RawRtfParser.
    */
   @Override
   public void processGroupEnd()
   {
      handleEvent(GROUP_END);
      state = stack.pop();
   }

   /**
    * Handle event from the RawRtfParser.
    */
   @Override
   public void processCharacterBytes(byte[] data)
   {
      try
      {
         if (data.length != 0)
         {
            if (skipBytes < data.length)
            {
               handleEvent(new StringEvent(new String(data, skipBytes, data.length - skipBytes, currentEncoding())));
            }
            skipBytes = 0;
         }
      }

      catch (UnsupportedEncodingException ex)
      {
         throw new RuntimeException(ex);
      }
   }

   /**
    * Determine which encoding to use, one defined by the current font, or the current default encoding.
    */
   private String currentEncoding()
   {
      return state.currentFontEncoding == null ? state.currentEncoding : state.currentFontEncoding;
   }

   /**
    * Handle event from the RawRtfParser.
    */
   @Override
   public void processDocumentStart()
   {
      handleEvent(DOCUMENT_START);
   }

   /**
    * Handle event from the RawRtfParser.
    */
   @Override
   public void processDocumentEnd()
   {
      handleEvent(DOCUMENT_END);
   }

   /**
    * Handle event from the RawRtfParser.
    */
   @Override
   public void processBinaryBytes(byte[] data)
   {
      handleEvent(new BinaryBytesEvent(data));
   }

   /**
    * Handle event from the RawRtfParser.
    */
   @Override
   public void processString(String string)
   {
      handleEvent(new StringEvent(string));
   }

   /**
    * Handle event from the RawRtfParser.
    */
   @Override
   public void processCommand(Command command, int parameter, boolean hasParameter, boolean optional)
   {
      if (command.getCommandType() == CommandType.Encoding)
      {
         processEncoding(command, hasParameter, parameter);
      }
      else
      {
         boolean optionalFlag = false;

         IParserEvent lastEvent = handler.getLastEvent();
         if (lastEvent.getType() == ParserEventType.COMMAND_EVENT)
         {
            if (((CommandEvent) lastEvent).getCommand() == Command.optionalcommand)
            {
               handler.removeLastEvent();
               optionalFlag = true;
            }
         }

         switch (command)
         {
            case u:
            {
               processUnicode(parameter);
               break;
            }

            case uc:
            {
               processUnicodeAlternateSkipCount(parameter);
               break;
            }

            case upr:
            {
               processUpr(new CommandEvent(command, parameter, hasParameter, optionalFlag));
               break;
            }

            case emdash:
            {
               processCharacter('\u2014');
               break;
            }

            case endash:
            {
               processCharacter('\u2013');
               break;
            }

            case emspace:
            {
               processCharacter('\u2003');
               break;
            }

            case enspace:
            {
               processCharacter('\u2002');
               break;
            }

            case qmspace:
            {
               processCharacter('\u2005');
               break;
            }

            case bullet:
            {
               processCharacter('\u2022');
               break;
            }

            case lquote:
            {
               processCharacter('\u2018');
               break;
            }

            case rquote:
            {
               processCharacter('\u2019');
               break;
            }

            case ldblquote:
            {
               processCharacter('\u201c');
               break;
            }

            case rdblquote:
            {
               processCharacter('\u201d');
               break;
            }

            case backslash:
            {
               processCharacter('\\');
               break;
            }

            case opencurly:
            {
               processCharacter('{');
               break;
            }

            case closecurly:
            {
               processCharacter('}');
               break;
            }

            case f:
            {
               processFont(parameter);
               handleCommand(command, parameter, hasParameter, optionalFlag);
               break;
            }

            case fcharset:
            {
               processFontCharset(parameter);
               handleCommand(command, parameter, hasParameter, optionalFlag);
               break;
            }

            default:
            {
               handleCommand(command, parameter, hasParameter, optionalFlag);
               break;
            }
         }
      }
   }

   /**
    * Set the current font and current font encoding in the state.
    */
   private void processFont(int parameter)
   {
      state.currentFont = parameter;
      state.currentFontEncoding = m_fontEncodings.get(Integer.valueOf(parameter));
   }

   /**
    * Set the charset for the current font.
    */
   private void processFontCharset(int parameter)
   {
      String charset = FontCharset.getCharset(parameter);
      if (charset != null)
      {
         m_fontEncodings.put(Integer.valueOf(state.currentFont), Encoding.LOCALEID_MAPPING.get(charset));
      }
   }

   /**
    * Switch the encoding based on the RTF command received.
    */
   private void processEncoding(Command command, boolean hasParameter, int parameter)
   {
      String encoding = null;
      switch (command)
      {
         case ansi:
         {
            encoding = Encoding.ANSI_ENCODING;
            break;
         }

         case pc:
         {
            encoding = Encoding.PC_ENCODING;
            break;
         }

         case pca:
         {
            encoding = Encoding.PCA_ENCODING;
            break;
         }

         case ansicpg:
         {
            encoding = hasParameter ? Encoding.LOCALEID_MAPPING.get(Integer.toString(parameter)) : null;
            break;
         }

         default:
         {
            encoding = null;
            break;
         }
      }

      if (encoding == null)
      {
         throw new IllegalArgumentException("Unsupported encoding command " + command.getCommandName() + (hasParameter ? parameter : ""));
      }

      state.currentEncoding = encoding;
   }

   /**
    * Process an RTF command parameter representing a Unicode character.
    */
   private void processUnicode(int parameter)
   {
      if (parameter < 0)
      {
         parameter += 65536;
      }

      processCharacter((char) parameter);
      skipBytes = state.unicodeAlternateSkipCount;
   }

   /**
    * Set the number of bytes to skip after a Unicode character.
    */
   private void processUnicodeAlternateSkipCount(int parameter)
   {
      state.unicodeAlternateSkipCount = parameter;
   }

   /**
    * Process a upr command: consume all of the RTF commands relating to this
    * and emit events representing the Unicode content.
    * @param command
    */
   private void processUpr(IParserEvent command)
   {
      IParserEventHandler uprHandler = new UprHandler(handler);
      uprHandler.handleEvent(command);

      handlerStack.push(handler);
      handler = uprHandler;
   }

   /**
    * Process a single character.
    */
   private void processCharacter(char c)
   {
      handleEvent(new StringEvent(Character.toString(c)));
   }

   /**
    * Process an RTF command.
    */
   private void handleCommand(Command command, int parameter, boolean hasParameter, boolean optional)
   {
      handleEvent(new CommandEvent(command, parameter, hasParameter, optional));
   }

   /**
    * Pass an event to the event handler, pop the event handler stack if the current
    * event handler has consumed all of the events it can.
    */
   private void handleEvent(IParserEvent event)
   {
      handler.handleEvent(event);
      if (handler.isComplete())
      {
         handler = handlerStack.pop();
      }
   }

   private IParserEventHandler handler;
   private final Deque<IParserEventHandler> handlerStack = new ArrayDeque<IParserEventHandler>();

   private ParserState state = new ParserState();
   private final Deque<ParserState> stack = new ArrayDeque<ParserState>();
   private int skipBytes;
   private Map<Integer, String> m_fontEncodings = new HashMap<Integer, String>();

   private static final IParserEvent DOCUMENT_START = new DocumentStartEvent();
   private static final IParserEvent DOCUMENT_END = new DocumentEndEvent();
   private static final IParserEvent GROUP_START = new GroupStartEvent();
   private static final IParserEvent GROUP_END = new GroupEndEvent();
}
