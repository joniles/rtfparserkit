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
      if (Character.isDigit(ch))
      {
         b = ch - '0';
      }
      else
      {
         if (Character.isLowerCase(ch))
         {
            if (ch < 'a' || ch > 'f')
            {
               throw new IllegalArgumentException("Invalid hex digit " + ch);
            }
            b = (char) ch - 'a' + 10;
         }
         else
         {
            if (ch < 'A' || ch > 'F')
            {
               throw new IllegalArgumentException("Invalid hex digit " + ch);
            }
            b = (char) ch - 'A' + 10;
         }
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
}
