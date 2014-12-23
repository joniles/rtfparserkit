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
import java.util.ArrayDeque;
import java.util.Deque;

import com.rtfparserkit.parser.IRtfParser;
import com.rtfparserkit.parser.IRtfSource;
import com.rtfparserkit.parser.RtfListenerAdaptor;
import com.rtfparserkit.parser.standard.StandardRtfParser;
import com.rtfparserkit.rtf.Command;
import com.rtfparserkit.rtf.CommandType;

/**
 * This class implements the core of a trivial RTF to text converter.
 * Subclasses implement the processExtractedText method to determine 
 * how the extracted text is handled.
 */
public abstract class AbstractTextConverter extends RtfListenerAdaptor
{
   public void convert(IRtfSource source) throws IOException
   {
      IRtfParser parser = new StandardRtfParser();
      currentDestination = Command.rtf;
      parser.parse(source, this);
   }

   /**
    * This method is called to process the test we've extracted from the RTF file.
    */
   public abstract void processExtractedText(String text);

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
   public void processString(String string)
   {
      switch (currentDestination)
      {
         case rtf:
         case pntext:
         case fldrslt:
         {
            processExtractedText(string);
            break;
         }

         default:
         {
            // Do nothing
            break;
         }
      }

   }

   @Override
   public void processCommand(Command command, int parameter, boolean hasParameter, boolean optional)
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
            processExtractedText("\n");
            break;
         }

         case tab:
         case cell:
         {
            processExtractedText("\t");
            break;
         }

         default:
         {
            // Do nothing
            break;
         }
      }
   }

   private Command currentDestination = Command.rtf;
   private final Deque<Command> destinationStack = new ArrayDeque<Command>();
}
