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

import com.rtfparserkit.document.CharacterStyle;
import com.rtfparserkit.document.ParagraphStyle;
import com.rtfparserkit.document.StyleSheet;
import com.rtfparserkit.rtf.Command;

/**
 * Processes RTF events that may be encountered in the style sheet section of
 * the file.
 */
class StyleSheetContext extends NullContext
{

   private final StyleSheet styleSheet;

   StyleSheetContext(StyleSheet styleSheet)
   {
      this.styleSheet = styleSheet;
   }

   @Override
   public void processGroupStart(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      // The Style Sheet should only contain style definition groups. These
      // can start with one of these commands, 
      switch (command)
      {
         case s:
         {
            // Paragraph style
            ParagraphStyle style = styleSheet.getParagraphStyleTable().createStyle();
            styleSheet.getParagraphStyleTable().addStyle(parameter, style);
            stack.pushContext(new ParagraphStyleContext(style));
            break;
         }
         case cs:
         {
            // Character style
            CharacterStyle style = styleSheet.getCharacterStyleTable().createStyle();
            styleSheet.getCharacterStyleTable().addStyle(parameter, style);
            stack.pushContext(new CharacterStyleContext(style));
            break;
         }
         case ds:
            // TODO: Section style
            stack.pushContext(new NullContext());
            break;
         case ts:
            // TODO: Table style
            stack.pushContext(new NullContext());
            break;
         default:
            stack.pushContext(new NullContext());
            break;
      }
   }
}
