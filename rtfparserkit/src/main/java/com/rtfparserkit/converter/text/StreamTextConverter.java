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
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.rtfparserkit.parser.IRtfSource;

/**
 * This class implements a trivial RTF to text converter.
 * The extracted text is written to the OutputStream as it is extracted.
 */
public class StreamTextConverter extends AbstractTextConverter
{
   public void convert(IRtfSource source, OutputStream os, String outputCharsetName) throws IOException
   {
      this.os = os;
      this.charset = Charset.forName(outputCharsetName);
      convert(source);
   }

   @Override
   public void processExtractedText(String text)
   {
      try
      {
         os.write(text.getBytes(charset));
      }

      catch (IOException ex)
      {
         throw new RuntimeException(ex);
      }
   }

   private Charset charset;
   private OutputStream os;
}
