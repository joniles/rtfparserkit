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

package com.rtfparserkit.converter.text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.Deque;

import com.rtfparserkit.parser.IRtfListener;
import com.rtfparserkit.parser.IRtfParser;
import com.rtfparserkit.parser.standard.StandardRtfParser;
import com.rtfparserkit.rtf.Command;
import com.rtfparserkit.rtf.CommandType;

/**
 * This class implements a trivial RTF to text converter.
 * No doubt it could be much improved - code contributions welcome!
 */
public class TextConverter implements IRtfListener
{
   public void convert(InputStream is, OutputStream os, String outputCharsetName) throws IOException
   {
      this.os = os;
      this.charset = Charset.forName(outputCharsetName);
      IRtfParser parser = new StandardRtfParser();
      currentDestination = Command.rtf;
      parser.parse(is, this);
   }

   @Override
   public void processDocumentStart()
   {
      // Do nothing
   }

   @Override
   public void processDocumentEnd()
   {
      // Do nothing
   }

   @Override
   public void processGroupStart()
   {
      destinationStack.push(currentDestination);
   }

   @Override
   public void processGroupEnd()
   {
      currentDestination = destinationStack.pop();
   }

   @Override
   public void processCharacterBytes(byte[] data)
   {
      // We won't receive any of these events as we are using the standard parser
   }

   @Override
   public void processBinaryBytes(byte[] data)
   {
      // Do nothing
   }

   @Override
   public void processString(String string)
   {
      try
      {
         switch (currentDestination)
         {
            case rtf:
            case pntext:
            case fldrslt:
            {
               os.write(string.getBytes(charset));
               break;
            }

            default:
            {
               // Do nothing
               break;
            }
         }
      }

      catch (IOException ex)
      {
         throw new RuntimeException(ex);
      }
   }

   @Override
   public void processCommand(Command command, int parameter, boolean hasParameter, boolean optional)
   {
      try
      {
         if (command.getCommandType() == CommandType.Destination)
         {
            currentDestination = command;
         }

         switch (command)
         {
            case par:
            case line:
            case row:
            {
               os.write("\n".getBytes(charset));
               break;
            }

            case tab:
            case cell:
            {
               os.write("\t".getBytes(charset));
               break;
            }

            default:
            {
               // Do nothing
               break;
            }
         }
      }

      catch (IOException ex)
      {
         throw new RuntimeException(ex);
      }
   }

   private Charset charset;
   private OutputStream os;
   private Command currentDestination;
   private final Deque<Command> destinationStack = new ArrayDeque<>();
}
