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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.rtfparserkit.parser.RtfStreamSource;
import com.rtfparserkit.utils.TestUtilities;

public class StreamTextConverterTest
{
   @Test
   public void testTextConversion() throws IOException
   {
      StreamTextConverter tc = new StreamTextConverter();
      File tempFile = File.createTempFile("testTextConversion", ".rtf");
      tempFile.deleteOnExit();

      InputStream is = StreamTextConverterTest.class.getResourceAsStream("data/testTextConversion.rtf");
      try
      {
         OutputStream os = new FileOutputStream(tempFile);
         try
         {
            tc.convert(new RtfStreamSource(is), os, "UTF-8");
         }
         finally
         {
            os.close();
         }
      }
      finally
      {
         is.close();
      }
      InputStream actualStream = new FileInputStream(tempFile);
      try
      {
         InputStream expectedStream = StreamTextConverterTest.class.getResourceAsStream("data/testTextConversion.txt");
         try
         {
            String expectedText = TestUtilities.readStreamToString(expectedStream);
            String actualText = TestUtilities.readStreamToString(actualStream);
            assertEquals(expectedText, actualText);
         }
         finally
         {
            expectedStream.close();
         }
      }
      finally
      {
         actualStream.close();
      }
   }
}
