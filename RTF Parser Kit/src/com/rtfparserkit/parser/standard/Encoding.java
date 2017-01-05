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
   public static final String MAC_ENCODING = "MacRoman";

   public static final Map<String, String> LOCALEID_MAPPING = new HashMap<String, String>();
   static
   {
      // Comment lines based on: https://msdn.microsoft.com/en-us/library/windows/desktop/dd317756(v=vs.85).aspx

      // 037   IBM037      IBM EBCDIC US-Canada
      LOCALEID_MAPPING.put("437", "Cp437"); // IBM437      OEM United States
      // 500   IBM500      IBM EBCDIC International
      // 708   ASMO-708    Arabic (ASMO 708)
      // 709               Arabic (ASMO-449+, BCON V4)
      // 710               Arabic - Transparent Arabic
      // 720   DOS-720     Arabic (Transparent ASMO); Arabic (DOS)
      // 737   ibm737      OEM Greek (formerly 437G); Greek (DOS)
      // 775   ibm775      OEM Baltic; Baltic (DOS)
      // 850   ibm850      OEM Multilingual Latin 1; Western European (DOS)
      // 852   ibm852      OEM Latin 2; Central European (DOS)
      // 855   IBM855      OEM Cyrillic (primarily Russian)
      // 857   ibm857      OEM Turkish; Turkish (DOS)
      // 858   IBM00858    OEM Multilingual Latin 1 + Euro symbol
      // 860   IBM860      OEM Portuguese; Portuguese (DOS)
      // 861   ibm861      OEM Icelandic; Icelandic (DOS)
      // 862   DOS-862     OEM Hebrew; Hebrew (DOS)
      // 863   IBM863      OEM French Canadian; French Canadian (DOS)
      // 864   IBM864      OEM Arabic; Arabic (864)
      // 865   IBM865      OEM Nordic; Nordic (DOS)
      // 866   cp866 OEM   Russian; Cyrillic (DOS)
      // 869   ibm869      OEM Modern Greek; Greek, Modern (DOS)
      // 870   IBM870      IBM EBCDIC Multilingual/ROECE (Latin 2); IBM EBCDIC Multilingual Latin 2
      LOCALEID_MAPPING.put("874", "Cp874"); // windows-874 ANSI/OEM Thai (ISO 8859-11); Thai (Windows)
      // 875   cp875       IBM EBCDIC Greek Modern
      LOCALEID_MAPPING.put("932", "SJIS"); // Japanese
      LOCALEID_MAPPING.put("936", "Cp936"); // Simplified Chinese
      LOCALEID_MAPPING.put("949", "Cp949"); // Korean
      LOCALEID_MAPPING.put("950", "Cp950"); // ANSI/OEM Traditional Chinese (Taiwan; Hong Kong SAR, PRC); Chinese Traditional (Big5)
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
      // 1047  IBM01047 IBM EBCDIC Latin 1/Open System
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
      // 1140  IBM01140 IBM EBCDIC US-Canada (037 + Euro symbol); IBM EBCDIC (US-Canada-Euro)
      // 1141  IBM01141 IBM EBCDIC Germany (20273 + Euro symbol); IBM EBCDIC (Germany-Euro)
      // 1142  IBM01142 IBM EBCDIC Denmark-Norway (20277 + Euro symbol); IBM EBCDIC (Denmark-Norway-Euro)
      // 1143  IBM01143 IBM EBCDIC Finland-Sweden (20278 + Euro symbol); IBM EBCDIC (Finland-Sweden-Euro)
      // 1144  IBM01144 IBM EBCDIC Italy (20280 + Euro symbol); IBM EBCDIC (Italy-Euro)
      // 1145  IBM01145 IBM EBCDIC Latin America-Spain (20284 + Euro symbol); IBM EBCDIC (Spain-Euro)
      // 1146  IBM01146 IBM EBCDIC United Kingdom (20285 + Euro symbol); IBM EBCDIC (UK-Euro)
      // 1147  IBM01147 IBM EBCDIC France (20297 + Euro symbol); IBM EBCDIC (France-Euro)
      // 1148  IBM01148 IBM EBCDIC International (500 + Euro symbol); IBM EBCDIC (International-Euro)
      // 1149  IBM01149 IBM EBCDIC Icelandic (20871 + Euro symbol); IBM EBCDIC (Icelandic-Euro)
      // 1200  utf-16   Unicode UTF-16, little endian byte order (BMP of ISO 10646)
      // 1201  unicodeFFFE Unicode UTF-16, big endian byte order
      LOCALEID_MAPPING.put("1250", "Cp1250"); // Windows Latin 2 (Central Europe)
      LOCALEID_MAPPING.put("1251", "Cp1251"); // Cyrillic
      LOCALEID_MAPPING.put("1252", "Cp1252"); // Latin
      LOCALEID_MAPPING.put("1253", "Cp1253"); // Greek
      LOCALEID_MAPPING.put("1254", "Cp1254"); // Turkish      
      LOCALEID_MAPPING.put("1255", "Cp1255"); // Windows Hebrew
      LOCALEID_MAPPING.put("1256", "Cp1256"); // Arabic (Iraq)
      LOCALEID_MAPPING.put("1257", "Cp1257"); // Baltic
      LOCALEID_MAPPING.put("1258", "Cp1258"); // Vietnamese
      // 1361  Johab Korean (Johab)
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
      LOCALEID_MAPPING.put("10001", "Shift_JIS"); // x-mac-japanese Japanese (Mac)
      // 10002 x-mac-chinesetrad MAC Traditional Chinese (Big5); Chinese Traditional (Mac)
      // 10003 x-mac-korean   Korean (Mac)
      LOCALEID_MAPPING.put("10004", "x-MacArabic"); // x-mac-arabic   Arabic (Mac)
      LOCALEID_MAPPING.put("10005", "x-MacHebrew"); // x-mac-hebrew   Hebrew (Mac)
      LOCALEID_MAPPING.put("10006", "x-MacHebrew"); // x-mac-greek Greek (Mac)
      LOCALEID_MAPPING.put("10007", "x-MacCyrillic"); // x-mac-cyrillic Cyrillic (Mac)
      // 10008 x-mac-chinesesimp MAC Simplified Chinese (GB 2312); Chinese Simplified (Mac)
      LOCALEID_MAPPING.put("10010", "x-MacRomania"); // x-mac-romanian Romanian (Mac)
      LOCALEID_MAPPING.put("10017", "x-MacUkraine"); // x-mac-ukrainian   Ukrainian (Mac)
      LOCALEID_MAPPING.put("10021", "x-MacThai"); // x-mac-thai  Thai (Mac)     
      LOCALEID_MAPPING.put("10029", "x-MacCentralEurope"); // x-mac-ce MAC Latin 2; Central European (Mac)
      LOCALEID_MAPPING.put("10079", "x-MacIceland"); // x-mac-icelandic   Icelandic (Mac)
      LOCALEID_MAPPING.put("10081", "x-MacTurkish"); // x-mac-turkish  Turkish (Mac)
      LOCALEID_MAPPING.put("10082", "x-MacCroatian"); // x-mac-croatian Croatian (Mac)
      LOCALEID_MAPPING.put("10241", "Cp1256"); // Arabic (Syria)
      LOCALEID_MAPPING.put("11265", "Cp1256"); // Arabic (Jordan)
      // 12000 utf-32   Unicode UTF-32, little endian byte order
      // 12001 utf-32BE Unicode UTF-32, big endian byte order
      LOCALEID_MAPPING.put("12289", "Cp1256"); // Arabic (Lebanon)
      LOCALEID_MAPPING.put("13313", "Cp1256"); // Arabic (Kuwait)
      LOCALEID_MAPPING.put("14337", "Cp1256"); // Arabic (U.A.E.)
      LOCALEID_MAPPING.put("15361", "Cp1256"); // Arabic (Bahrain)
      LOCALEID_MAPPING.put("16385", "Cp1256"); // Arabic (Qatar)
      // 20000 x-Chinese_CNS  CNS Taiwan; Chinese Traditional (CNS)
      // 20001 x-cp20001   TCA Taiwan
      // 20002 x_Chinese-Eten Eten Taiwan; Chinese Traditional (Eten)
      // 20003 x-cp20003   IBM5550 Taiwan
      // 20004 x-cp20004   TeleText Taiwan
      // 20005 x-cp20005   Wang Taiwan
      // 20105 x-IA5 IA5 (IRV International Alphabet No. 5, 7-bit); Western European (IA5)
      // 20106 x-IA5-German   IA5 German (7-bit)
      // 20107 x-IA5-Swedish  IA5 Swedish (7-bit)
      // 20108 x-IA5-Norwegian   IA5 Norwegian (7-bit)
      // 20127 us-ascii US-ASCII (7-bit)
      // 20261 x-cp20261   T.61
      // 20269 x-cp20269   ISO 6937 Non-Spacing Accent
      // 20273 IBM273   IBM EBCDIC Germany
      // 20277 IBM277   IBM EBCDIC Denmark-Norway
      // 20278 IBM278   IBM EBCDIC Finland-Sweden
      // 20280 IBM280   IBM EBCDIC Italy
      // 20284 IBM284   IBM EBCDIC Latin America-Spain
      // 20285 IBM285   IBM EBCDIC United Kingdom
      // 20290 IBM290   IBM EBCDIC Japanese Katakana Extended
      // 20297 IBM297   IBM EBCDIC France
      // 20420 IBM420   IBM EBCDIC Arabic
      // 20423 IBM423   IBM EBCDIC Greek
      // 20424 IBM424   IBM EBCDIC Hebrew
      // 20833 x-EBCDIC-KoreanExtended IBM EBCDIC Korean Extended
      // 20838 IBM-Thai IBM EBCDIC Thai
      // 20866 koi8-r   Russian (KOI8-R); Cyrillic (KOI8-R)
      // 20871 IBM871   IBM EBCDIC Icelandic
      // 20880 IBM880   IBM EBCDIC Cyrillic Russian
      // 20905 IBM905   IBM EBCDIC Turkish
      // 20924 IBM00924 IBM EBCDIC Latin 1/Open System (1047 + Euro symbol)
      // 20932 EUC-JP   Japanese (JIS 0208-1990 and 0212-1990)
      // 20936 x-cp20936   Simplified Chinese (GB2312); Chinese Simplified (GB2312-80)
      // 20949 x-cp20949   Korean Wansung
      // 21025 cp1025   IBM EBCDIC Cyrillic Serbian-Bulgarian
      // 21027    (deprecated)
      // 21866 koi8-u   Ukrainian (KOI8-U); Cyrillic (KOI8-U)
      // 28591 iso-8859-1  ISO 8859-1 Latin 1; Western European (ISO)
      // 28592 iso-8859-2  ISO 8859-2 Central European; Central European (ISO)
      // 28593 iso-8859-3  ISO 8859-3 Latin 3
      // 28594 iso-8859-4  ISO 8859-4 Baltic
      // 28595 iso-8859-5  ISO 8859-5 Cyrillic
      // 28596 iso-8859-6  ISO 8859-6 Arabic
      // 28597 iso-8859-7  ISO 8859-7 Greek
      // 28598 iso-8859-8  ISO 8859-8 Hebrew; Hebrew (ISO-Visual)
      // 28599 iso-8859-9  ISO 8859-9 Turkish
      // 28603 iso-8859-13 ISO 8859-13 Estonian
      // 28605 iso-8859-15 ISO 8859-15 Latin 9
      // 29001 x-Europa Europa 3
      // 38598 iso-8859-8-i   ISO 8859-8 Hebrew; Hebrew (ISO-Logical)
      // 50220 iso-2022-jp ISO 2022 Japanese with no halfwidth Katakana; Japanese (JIS)
      // 50221 csISO2022JP ISO 2022 Japanese with halfwidth Katakana; Japanese (JIS-Allow 1 byte Kana)
      // 50222 iso-2022-jp ISO 2022 Japanese JIS X 0201-1989; Japanese (JIS-Allow 1 byte Kana - SO/SI)
      // 50225 iso-2022-kr ISO 2022 Korean
      // 50227 x-cp50227   ISO 2022 Simplified Chinese; Chinese Simplified (ISO 2022)
      // 50229    ISO 2022 Traditional Chinese
      // 50930    EBCDIC Japanese (Katakana) Extended
      // 50931    EBCDIC US-Canada and Japanese
      // 50933    EBCDIC Korean Extended and Korean
      // 50935    EBCDIC Simplified Chinese Extended and Simplified Chinese
      // 50936    EBCDIC Simplified Chinese
      // 50937    EBCDIC US-Canada and Traditional Chinese
      // 50939    EBCDIC Japanese (Latin) Extended and Japanese
      // 51932 euc-jp   EUC Japanese
      // 51936 EUC-CN   EUC Simplified Chinese; Chinese Simplified (EUC)
      // 51949 euc-kr   EUC Korean
      // 51950    EUC Traditional Chinese
      // 52936 hz-gb-2312  HZ-GB2312 Simplified Chinese; Chinese Simplified (HZ)
      // 54936 GB18030  Windows XP and later: GB18030 Simplified Chinese (4 byte); Chinese Simplified (GB18030)
      // 57002 x-iscii-de  ISCII Devanagari
      // 57003 x-iscii-be  ISCII Bangla
      // 57004 x-iscii-ta  ISCII Tamil
      // 57005 x-iscii-te  ISCII Telugu
      // 57006 x-iscii-as  ISCII Assamese
      // 57007 x-iscii-or  ISCII Odia
      // 57008 x-iscii-ka  ISCII Kannada
      // 57009 x-iscii-ma  ISCII Malayalam
      // 57010 x-iscii-gu  ISCII Gujarati
      // 57011 x-iscii-pa  ISCII Punjabi
      LOCALEID_MAPPING.put("65000", null); // UTF-7 - not a supported Java encoding, see: http://stackoverflow.com/questions/19861987/java-io-unsupportedencodingexception-unicode-1-1-utf-7
      LOCALEID_MAPPING.put("65001", "UTF-8"); // UTF-8
   }
}
