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

import com.rtfparserkit.parser.IRtfListener;
import com.rtfparserkit.rtf.Command;

/**
 * Represents an event to be sent to the listener.
 */
class CommandEvent implements IParserEvent
{
   /**
    * Constructor.
    */
   public CommandEvent(Command command, int parameter, boolean hasParameter, boolean optional)
   {
      this.command = command;
      this.parameter = parameter;
      this.hasParameter = hasParameter;
      this.optional = optional;
   }

   /**
    * Retrieve the event type.
    */
   @Override
   public ParserEventType getType()
   {
      return ParserEventType.COMMAND_EVENT;
   }

   /**
    * Pass the event to the listener.
    */
   @Override
   public void fire(IRtfListener listener)
   {
      listener.processCommand(command, parameter, hasParameter, optional);
   }

   /**
    * Retrieve the command represented by this event.
    */
   public Command getCommand()
   {
      return command;
   }

   @Override
   public String toString()
   {
      return "[CommandEvent command=" + command + (hasParameter ? " parameter=" + parameter : "") + (optional ? " optional" : "") + "]";
   }

   private final Command command;
   private final int parameter;
   private final boolean hasParameter;
   private final boolean optional;
}
