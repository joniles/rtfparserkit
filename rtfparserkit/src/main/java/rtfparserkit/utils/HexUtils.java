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

package com.rtfparserkit.utils;

import java.util.Arrays;

/**
 * Utilities for working with hex numbers.
 */
public class HexUtils
{
   /**
    * Parse a hex digit.
    */
   public static int parseHexDigit(int ch)
   {
      int b;

      try
      {
         b = HEX_MAP[ch];
      }

      catch (IndexOutOfBoundsException ex)
      {
         b = -1;
      }

      if (b == -1)
      {
         throw new IllegalArgumentException("Invalid hex digit " + ch);
      }

      return b;
   }

   /**
    * Convert a string of hex digits into an array of bytes.
    */
   public static final byte[] parseHexString(String hex)
   {
      if (hex.length() % 2 != 0)
      {
         throw new IllegalArgumentException("Invalid hex string");
      }

      byte[] bytes = new byte[hex.length() / 2];
      for (int byteIndex = 0; byteIndex < bytes.length; byteIndex++)
      {
         int stringIndex = byteIndex * 2;
         int b = parseHexDigit(hex.charAt(stringIndex)) << 4;
         b += parseHexDigit(hex.charAt(stringIndex + 1));
         bytes[byteIndex] = (byte) b;
      }

      return bytes;
   }

   private static final int[] HEX_MAP = new int['g'];
   static
   {
      Arrays.fill(HEX_MAP, -1);

      HEX_MAP['0'] = 0;
      HEX_MAP['1'] = 1;
      HEX_MAP['2'] = 2;
      HEX_MAP['3'] = 3;
      HEX_MAP['4'] = 4;
      HEX_MAP['5'] = 5;
      HEX_MAP['6'] = 6;
      HEX_MAP['7'] = 7;
      HEX_MAP['8'] = 8;
      HEX_MAP['9'] = 9;

      HEX_MAP['A'] = 10;
      HEX_MAP['B'] = 11;
      HEX_MAP['C'] = 12;
      HEX_MAP['D'] = 13;
      HEX_MAP['E'] = 14;
      HEX_MAP['F'] = 15;

      HEX_MAP['a'] = 10;
      HEX_MAP['b'] = 11;
      HEX_MAP['c'] = 12;
      HEX_MAP['d'] = 13;
      HEX_MAP['e'] = 14;
      HEX_MAP['f'] = 15;
   }
}
