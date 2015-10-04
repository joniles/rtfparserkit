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

package com.rtfparserkit.document;

/**
 * Interface for adding a font definition to the global font table and 
 * retrieving the font information at a specific index.
 */
public interface FontTable
{

   public enum FontFamily
   {
      DEFAULT, ROMAN, SWISS, MODERN, SCRIPT, DECOR, TECH, BIDI
   }

   public void addFont(String name, String alternativeName, String fileName, FontFamily fontFamily);

   public int countFonts();

   public Font fontAt(int index);
}
