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

package com.rtfparserkit.parser.raw;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import com.rtfparserkit.parser.IRtfListener;
import com.rtfparserkit.parser.IRtfParser;
import com.rtfparserkit.rtf.Command;

/**
 * This class implements a low level RTF parser. It performs the minimum amount
 * of processing on the data read from an RTF file, just passing the caller a
 * stream of events representing the commands and character bytes. In particular
 * the parser doesn't not deal with character encodings or the various
 * Unicode related commands which may be present in an RTF file.
 *
 * This code is based on the approach outlined in the sample C code provided in
 * the RTF Specification 1.9.1 (http://www.microsoft.com/en-gb/download/details.aspx?id=10725)
 */
public class RawRtfParser implements IRtfParser
{
   /**
    * Parse RTF data from an input stream.
    */
   public void parse(InputStream is, IRtfListener listener) throws IOException
   {
      this.listener = listener;
      groupDepth = 0;
      parsingHex = false;
      buffer = new ByteBuffer();

      PushbackInputStream fp = new PushbackInputStream(new BufferedInputStream(is));

      listener.processDocumentStart();

      int ch;
      parsingHex = false;

      while (true)
      {

         ch = fp.read();
         if (ch == -1)
         {
            break;
         }

         if (groupDepth < 0)
         {
            throw new IllegalStateException("Group stack underflow");
         }

         switch (ch)
         {
            case '{':
            {
               handleGroupStart();
               break;
            }

            case '}':
            {
               handleGroupEnd();
               break;
            }

            case '\\':
            {
               handleCommand(fp);
               break;
            }

            case '\r':
            case '\n':
            {
               break;
            }

            case '\t':
            {
               handleCharacterData();
               listener.processCommand(Command.tab, 0, false, false);
               break;
            }

            default:
            {
               handleCharacterByte(fp, ch);
               break;
            }
         }
      }

      if (groupDepth < 0)
      {
         throw new IllegalStateException("Group stack underflow");
      }

      if (groupDepth > 0)
      {
         throw new IllegalStateException("Unmatched brace");
      }

      listener.processDocumentEnd();
   }

   /**
    * Process a single character byte, or hex encoded character byte.
    */
   private void handleCharacterByte(InputStream fp, int ch) throws IOException
   {
      if (parsingHex)
      {
         int b = parseHexDigit(ch) << 4;
         ch = fp.read();
         if (ch == -1)
         {
            throw new IllegalStateException("Unexpected end of file");
         }
         b += parseHexDigit(ch);
         buffer.add(b);
         parsingHex = false;
      }
      else
      {
         buffer.add(ch);
      }
   }

   /**
    * Parse a hex digit.
    */
   private int parseHexDigit(int ch)
   {
      int b;
      if (Character.isDigit(ch))
      {
         b = ch - '0';
      }
      else
      {
         if (Character.isLowerCase(ch))
         {
            if (ch < 'a' || ch > 'f')
            {
               throw new IllegalArgumentException("Invalid hex digit " + ch);
            }
            b = (char) ch - 'a' + 10;
         }
         else
         {
            if (ch < 'A' || ch > 'F')
            {
               throw new IllegalArgumentException("Invalid hex digit " + ch);
            }
            b = (char) ch - 'A' + 10;
         }
      }
      return b;
   }

   /**
    * Read and process an RTF command.
    */
   private void handleCommand(PushbackInputStream fp) throws IOException
   {
      handleCharacterData();

      boolean commandHasParameter = false;
      boolean parameterIsNegative = false;
      int parameterValue = 0;
      StringBuilder commandText = new StringBuilder();
      StringBuilder parameterText = new StringBuilder();

      int ch = fp.read();
      if (ch == -1)
      {
         throw new EOFException();
      }

      commandText.append((char) ch);

      if (!Character.isLetter(ch))
      {
         handleCommand(fp, commandText, 0, commandHasParameter);
         return;
      }

      while (true)
      {
         ch = fp.read();
         if (ch == -1 || !Character.isLetter(ch))
         {
            break;
         }
         commandText.append((char) ch);
         if (commandText.length() > MAX_COMMAND_LENGTH)
         {
            break;
         }
      }

      if (ch == -1)
      {
         throw new EOFException();
      }

      if (commandText.length() > MAX_COMMAND_LENGTH)
      {
         throw new IllegalArgumentException("Invalid keyword: " + commandText.toString());
      }

      if (ch == '-')
      {
         parameterIsNegative = true;
         ch = fp.read();
         if (ch == -1)
         {
            throw new EOFException();
         }
      }
      if (Character.isDigit(ch))
      {
         commandHasParameter = true;
         parameterText.append((char) ch);
         while (true)
         {
            ch = fp.read();
            if (ch == -1 || !Character.isDigit(ch))
            {
               break;
            }
            parameterText.append((char) ch);
            if (parameterText.length() > MAX_PARAMETER_LENGTH)
            {
               break;
            }
         }

         if (parameterText.length() > MAX_PARAMETER_LENGTH)
         {
            throw new IllegalArgumentException("Invalid parameter: " + parameterText.toString());
         }

         parameterValue = Integer.parseInt(parameterText.toString());
         if (parameterIsNegative)
         {
            parameterValue = -parameterValue;
         }
      }

      if (ch != ' ')
      {
         fp.unread(ch);
      }

      handleCommand(fp, commandText, parameterValue, commandHasParameter);
   }

   /**
    * Determine what to do with the extracted command. 
    */
   private void handleCommand(InputStream fp, StringBuilder commandBuffer, int parameter, boolean hasParameter) throws IOException
   {
      String commandName = commandBuffer.toString();
      Command command = Command.getInstance(commandName);
      if (command == null)
      {
         throw new IllegalArgumentException("Unknown command " + commandName);
      }

      switch (command)
      {
         case bin:
         {
            handleBinaryData(fp, parameter);
            break;
         }

         case hex:
         {
            parsingHex = true;
            break;
         }

         default:
         {
            listener.processCommand(command, parameter, hasParameter, false);
            break;
         }
      }
   }

   /**
    * Pass accumulated character data to the listener.
    */
   private void handleCharacterData()
   {
      byte[] data = buffer.toArray();
      buffer.clear();
      listener.processCharacterBytes(data);
   }

   /**
    * Pass binary data to the listener.
    */
   private void handleBinaryData(InputStream is, int size) throws IOException
   {
      byte[] data = new byte[size];
      int bytesRead = is.read(data);
      if (bytesRead != size)
      {
         throw new EOFException();
      }
      listener.processBinaryBytes(data);
   }

   /**
    * Inform the listener of a group start.
    */
   private void handleGroupStart()
   {
      handleCharacterData();
      groupDepth++;
      listener.processGroupStart();
   }

   /**
    * Inform the listener of a group end.
    */
   private void handleGroupEnd()
   {
      handleCharacterData();
      listener.processGroupEnd();
      groupDepth--;
   }

   private int groupDepth;
   private boolean parsingHex;
   private ByteBuffer buffer;
   private IRtfListener listener;

   private static final int MAX_PARAMETER_LENGTH = 20;
   private static final int MAX_COMMAND_LENGTH = 30;
}
