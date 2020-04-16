/*
 * Copyright 2015 Stephan Aßmus <superstippi@gmx.de>
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

package com.rtfparserkit.parser.builder;

import com.rtfparserkit.rtf.Command;

/**
 * An implementation of all methods defined by RtfContext. All methods
 * throw an IllegalStateException, unless the object is configured not to
 * throw exception at construction time. The idea is that derived classes
 * implement exactly the needed functionality and RTF events resulting in
 * calling methods which have not been overridden means something is wrong.
 * The only method which is supposed to be used by sub-classes is
 * processGroupEnd(), which pops the context from the provided RtfContextStack.
 */
abstract class AbstractRtfContext implements RtfContext
{

   private final boolean throwExceptions;

   protected AbstractRtfContext()
   {
      this(true);
   }

   protected AbstractRtfContext(boolean throwExceptions)
   {
      this.throwExceptions = throwExceptions;
   }

   @Override
   public void processGroupStart(RtfContextStack stack)
   {
      handleUnexpectedEvent("Unexpected anonymous group start");
      stack.pushContext(this);
   }

   @Override
   public void processGroupStart(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      handleUnexpectedEvent("Unexpected destination group start");
      stack.pushContext(this);
   }

   @Override
   public void processGroupEnd(RtfContextStack stack)
   {
      stack.popContext();
   }

   @Override
   public void processCharacterBytes(byte[] data)
   {
      handleUnexpectedEvent("Unexpected character bytes");
   }

   @Override
   public void processBinaryBytes(byte[] data)
   {
      handleUnexpectedEvent("Unexpected binary bytes");
   }

   @Override
   public void processString(String string)
   {
      handleUnexpectedEvent("Unexpected string '" + string + "'");
   }

   @Override
   public void processCommand(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      handleUnexpectedEvent("Unexpected command '" + command + "'");
   }

   private void handleUnexpectedEvent(String eventInfo)
   {
      if (throwExceptions)
         throw new IllegalStateException(eventInfo);
   }
}
