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
 * A simple "struct" (hence the public members) representing the current state of the parser.
 */
class ParserState
{
   public ParserState()
   {

   }

   public ParserState(ParserState state)
   {
      currentFont = state.currentFont;
      currentEncoding = state.currentEncoding;
      currentFontEncoding = state.currentFontEncoding;
      unicodeAlternateSkipCount = state.unicodeAlternateSkipCount;
   }

   public int currentFont;
   public String currentEncoding = Encoding.ANSI_ENCODING;
   public String currentFontEncoding;
   public int unicodeAlternateSkipCount = 1;
}
