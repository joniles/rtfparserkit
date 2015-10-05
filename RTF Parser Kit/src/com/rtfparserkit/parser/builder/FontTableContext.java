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

import com.rtfparserkit.document.FontTable;
import com.rtfparserkit.rtf.Command;

/**
 * Processes RTF events that may be encountered in the font table section of
 * the file. Whenever a new group is started, a new FontContext is created to
 * handle the events within the group which specify the font parameters.
 * Note that FontTableContext inherits from FontContext, since RTF files may
 * or may not have the \font elements within a group.
 */
class FontTableContext extends FontContext
{

   FontTableContext(FontTable table)
   {
      super(table);
   }

   @Override
   public void processGroupStart(RtfContextStack stack)
   {
      stack.pushContext(new FontContext(fontTable));
   }

   @Override
   public void processGroupStart(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      switch (command)
      {
         case f:
            stack.pushContext(new FontContext(parameter, fontTable));
            break;
         default:
            super.processGroupStart(stack, command, parameter, hasParameter, optional);
            break;
      }
   }

}
