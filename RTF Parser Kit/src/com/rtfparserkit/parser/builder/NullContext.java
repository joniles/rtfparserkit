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
 * RtfContext ignoring all events. Used when text and other comments should not
 * end up in a known destination. Groups within a null destination will also
 * be completely ignored.
 */
class NullContext implements RtfContext
{

   public void processGroupStart(RtfContextStack stack)
   {
      stack.pushContext(new NullContext());
   }

   public void processGroupStart(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      processGroupStart(stack);
   }

   public void processGroupEnd(RtfContextStack stack)
   {
      stack.popContext();
   }

   public void processCharacterBytes(byte[] data)
   {
      // Ignore
   }

   public void processBinaryBytes(byte[] data)
   {
      // Ignore
   }

   public void processString(String string)
   {
      // Ignore
   }

   public void processCommand(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      // Ignore
   }

}