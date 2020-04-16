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

import com.rtfparserkit.document.Document;
import com.rtfparserkit.rtf.Command;

/**
 * Initial RtfContext. Pushes a DocumentContext upon encountering a group
 * starting with \rtf.
 */
class RootContext extends AbstractRtfContext
{

   private final Document document;

   RootContext(Document document)
   {
      this.document = document;
   }

   @Override
   public void processGroupStart(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      switch (command)
      {
         case rtf:
            stack.pushContext(new DocumentContext(document));
            break;

         default:
            // Unknown destinations should be ignored.
            stack.pushContext(new NullContext());
            break;
      }
   }

   @Override
   public void processGroupStart(RtfContextStack stack)
   {
      // Unknown groups should be ignored.
      stack.pushContext(new NullContext());
   }
}
