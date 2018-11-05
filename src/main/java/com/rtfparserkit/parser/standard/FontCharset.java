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

/**
 * Represents font character sets which may be encountered in an RTF file.
 */
class FontCharset
{
   /**
    * Convert a font character set to an encoding name.
    */
   public static String getCharset(int parameter)
   {
      String result = null;
      if (parameter >= 0 && parameter < MAPPING.length)
      {
         result = MAPPING[parameter];
      }
      return result;
   }

   private static final String[] MAPPING = new String[256];
   static
   {
      MAPPING[0] = "1252"; // ANSI
      MAPPING[1] = null; // Default
      MAPPING[2] = "1252"; // Symbol - according to the specs this is codepage 42 "Symbol". What's the Java equivalent? 1252 seems to work...
      MAPPING[77] = "10000"; // Mac Roman
      MAPPING[78] = "10001"; // Mac Shift Jis
      MAPPING[79] = "10003"; // Mac Hangul
      MAPPING[80] = "10008"; // Mac GB2312
      MAPPING[81] = "10002"; // Mac Big5
      MAPPING[82] = null; // Mac Johab (old)
      MAPPING[83] = "10005"; // Mac Hebrew
      MAPPING[84] = "10004"; // Mac Arabic
      MAPPING[85] = "10006"; // Mac Greek
      MAPPING[86] = "10081"; // Mac Turkish
      MAPPING[87] = "10021"; // Mac Thai
      MAPPING[88] = "10029"; // Mac East Europe
      MAPPING[89] = "10007"; // Mac Russian
      MAPPING[128] = "932"; // Shift JIS
      MAPPING[129] = "949"; // Hangul
      MAPPING[130] = "1361"; // Johab
      MAPPING[134] = "936"; // GB2312
      MAPPING[136] = "950"; // Big5
      MAPPING[161] = "1253"; // Greek
      MAPPING[162] = "1254"; // Turkish
      MAPPING[163] = "1258"; // Vietnamese
      MAPPING[177] = "1255"; // Hebrew
      MAPPING[178] = "1256"; // Arabic 
      MAPPING[179] = null; // Arabic Traditional (old)
      MAPPING[180] = null; // Arabic user (old)
      MAPPING[181] = null; // Hebrew user (old)
      MAPPING[186] = "1257"; // Baltic
      MAPPING[204] = "1251"; // Russian
      MAPPING[222] = "874"; // Thai
      MAPPING[238] = "1250"; // Eastern European
      MAPPING[254] = "437"; // PC 437
      MAPPING[255] = "850"; // OEM
   }
}
