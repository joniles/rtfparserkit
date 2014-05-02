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

package com.rtfparserkit.converter.text;

import java.io.IOException;

import com.rtfparserkit.parser.IRtfSource;

/**
 * This class implements a trivial RTF to text converter.
 * The extracted text is cached in a buffer and is available
 * to the caller using the getText() method.
 */
public class StringTextConverter extends AbstractTextConverter
{
   @Override
   public void convert(IRtfSource source) throws IOException
   {
      buffer.setLength(0);
      super.convert(source);
   }

   @Override
   public void processExtractedText(String text)
   {
      buffer.append(text);
   }

   public String getText()
   {
      return buffer.toString();
   }

   private StringBuilder buffer = new StringBuilder();
}
