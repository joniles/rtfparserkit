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
import com.rtfparserkit.document.FontTable.FontFamily;
import com.rtfparserkit.rtf.Command;

/**
 * RtfContext implementation which handles RTF events inside a group inside
 * of the font table section.
 */
class FontContext extends AbstractRtfContext
{

   protected final FontTable fontTable;

   private int id = 0;
   private String fontName = "";
   private String alternativeName = "";
   private String fileName = "";
   private FontFamily fontFamily = FontFamily.DEFAULT;

   private enum ExpectedName
   {
      DEFAULT, ALTERNATIVE, FILE
   }

   private ExpectedName expectedName = ExpectedName.DEFAULT;

   FontContext(FontTable fontTable)
   {
      this.fontTable = fontTable;
   }

   FontContext(int id, FontTable fontTable)
   {
      this(fontTable);
      this.id = id;
   }

   @Override
   public void processGroupEnd(RtfContextStack stack)
   {
      super.processGroupEnd(stack);
   }

   @Override
   public void processString(String string)
   {
      int offset = 0;
      while (offset < string.length())
      {
         // Chop off anything after and including the first semicolon
         int semicolon = string.indexOf(';', offset);

         if (semicolon == offset)
         {
            fontTable.addFont(id, fontName, alternativeName, fileName, fontFamily);
            offset = semicolon + 1;
            continue;
         }

         int end = semicolon >= 0 ? semicolon : string.length();

         String name = string.substring(offset, end);

         switch (expectedName)
         {
            case DEFAULT:
               fontName = name;
               break;
            case ALTERNATIVE:
               alternativeName = name;
               break;
            case FILE:
               fileName = name;
               break;
         }

         offset = end;
      }
   }

   @Override
   public void processCommand(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      switch (command)
      {
         case f:
            id = parameter;
            break;

         case fname:
            expectedName = ExpectedName.DEFAULT;
            break;
         case falt:
            expectedName = ExpectedName.ALTERNATIVE;
            break;
         case fontfile:
            expectedName = ExpectedName.FILE;
            break;

         case fnil:
            fontFamily = FontFamily.DEFAULT;
            break;
         case froman:
            fontFamily = FontFamily.ROMAN;
            break;
         case fswiss:
            fontFamily = FontFamily.SWISS;
            break;
         case fmodern:
            fontFamily = FontFamily.MODERN;
            break;
         case fscript:
            fontFamily = FontFamily.SCRIPT;
            break;
         case fdecor:
            fontFamily = FontFamily.DECOR;
            break;
         case ftech:
            fontFamily = FontFamily.TECH;
            break;
         case fbidi:
            fontFamily = FontFamily.BIDI;
            break;

         default:
            break;
      }
   }
}
