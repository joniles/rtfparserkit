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

import java.util.HashMap;
import java.util.Map;

/**
 * Represents character encodings which may be encountered in an RTF file.
 */
class Encoding
{
   public static final String ANSI_ENCODING = "Cp1252";
   public static final String PC_ENCODING = "Cp437";
   public static final String PCA_ENCODING = "Cp850";

   public static final Map<String, String> LOCALEID_MAPPING = new HashMap<String, String>();
   static
   {
      LOCALEID_MAPPING.put("936", "Cp936"); // Simplified Chinese
      LOCALEID_MAPPING.put("1025", "Cp1256"); // Arabic (Saudi Arabia)
      LOCALEID_MAPPING.put("1026", "Cp1251"); // Bulgarian
      LOCALEID_MAPPING.put("1028", "Cp950"); // Chinese (Taiwan)
      LOCALEID_MAPPING.put("1029", "Cp1250"); // Czech
      LOCALEID_MAPPING.put("1032", "Cp1253"); // Greek
      LOCALEID_MAPPING.put("1037", "Cp1255"); // Hebrew
      LOCALEID_MAPPING.put("1038", "Cp1250"); // Hungarian
      LOCALEID_MAPPING.put("1041", "SJIS"); // Japanese
      LOCALEID_MAPPING.put("1042", "Cp949"); // Korean
      LOCALEID_MAPPING.put("1045", "Cp1250"); // Polish
      LOCALEID_MAPPING.put("1048", "Cp1250"); // Romanian
      LOCALEID_MAPPING.put("1049", "Cp1251"); // Russian
      LOCALEID_MAPPING.put("1050", "Cp1250"); // Croatian
      LOCALEID_MAPPING.put("1051", "Cp1250"); // Slovak
      LOCALEID_MAPPING.put("1052", "Cp1250"); // Albanian
      LOCALEID_MAPPING.put("1054", "Cp874"); // Thai
      LOCALEID_MAPPING.put("1055", "Cp1254"); // Turkish
      LOCALEID_MAPPING.put("1056", "Cp1256"); // Urdu
      LOCALEID_MAPPING.put("1058", "Cp1251"); // Ukrainian
      LOCALEID_MAPPING.put("1059", "Cp1251"); // Belarusian
      LOCALEID_MAPPING.put("1060", "Cp1250"); // Slovenian
      LOCALEID_MAPPING.put("1061", "Cp1257"); // Estonian
      LOCALEID_MAPPING.put("1062", "Cp1257"); // Latvian
      LOCALEID_MAPPING.put("1063", "Cp1257"); // Lithuanian
      LOCALEID_MAPPING.put("1065", "Cp1256"); // Farsi
      LOCALEID_MAPPING.put("1066", "Cp1258"); // Vietnamese
      LOCALEID_MAPPING.put("1068", "Cp1254"); // Azeri (Latin)
      LOCALEID_MAPPING.put("1071", "Cp1251"); // FYRO Macedonian
      LOCALEID_MAPPING.put("1087", "Cp1251"); // Kazakh
      LOCALEID_MAPPING.put("1088", "Cp1251"); // Kyrgyz (Cyrillic)
      LOCALEID_MAPPING.put("1091", "Cp1254"); // Uzbek (Latin)
      LOCALEID_MAPPING.put("1092", "Cp1251"); // Tatar
      LOCALEID_MAPPING.put("1104", "Cp1251"); // Mongolian (Cyrillic)
      LOCALEID_MAPPING.put("1252", "Cp1252"); // Mongolian (Cyrillic)
      LOCALEID_MAPPING.put("1255", "Cp1255"); // Windows Hebrew
      LOCALEID_MAPPING.put("2049", "Cp1256"); // Arabic (Iraq)
      LOCALEID_MAPPING.put("2052", "MS936"); // Chinese (PRC)
      LOCALEID_MAPPING.put("2074", "Cp1250"); // Serbian (Latin)
      LOCALEID_MAPPING.put("2092", "Cp1251"); // Azeri (Cyrillic)
      LOCALEID_MAPPING.put("2115", "Cp1251"); // Uzbek (Cyrillic)
      LOCALEID_MAPPING.put("3073", "Cp1256"); // Arabic (Egypt)
      LOCALEID_MAPPING.put("3076", "Cp950"); // Chinese (Hong Kong S.A.R.)
      LOCALEID_MAPPING.put("3098", "Cp1251"); // Serbian (Cyrillic)
      LOCALEID_MAPPING.put("4097", "Cp1256"); // Arabic (Libya)
      LOCALEID_MAPPING.put("4100", "MS936"); // Chinese (Singapore)
      LOCALEID_MAPPING.put("5121", "Cp1256"); // Arabic (Algeria)
      LOCALEID_MAPPING.put("5124", "Cp950"); // Chinese (Macau S.A.R.)
      LOCALEID_MAPPING.put("6145", "Cp1256"); // Arabic (Morocco)
      LOCALEID_MAPPING.put("7169", "Cp1256"); // Arabic (Tunisia)
      LOCALEID_MAPPING.put("8193", "Cp1256"); // Arabic (Oman)
      LOCALEID_MAPPING.put("9217", "Cp1256"); // Arabic (Yemen)
      LOCALEID_MAPPING.put("10000", "MacRoman"); // Mac Roman
      LOCALEID_MAPPING.put("10241", "Cp1256"); // Arabic (Syria)
      LOCALEID_MAPPING.put("11265", "Cp1256"); // Arabic (Jordan)
      LOCALEID_MAPPING.put("12289", "Cp1256"); // Arabic (Lebanon)
      LOCALEID_MAPPING.put("13313", "Cp1256"); // Arabic (Kuwait)
      LOCALEID_MAPPING.put("14337", "Cp1256"); // Arabic (U.A.E.)
      LOCALEID_MAPPING.put("15361", "Cp1256"); // Arabic (Bahrain)
      LOCALEID_MAPPING.put("16385", "Cp1256"); // Arabic (Qatar)
   }
}
