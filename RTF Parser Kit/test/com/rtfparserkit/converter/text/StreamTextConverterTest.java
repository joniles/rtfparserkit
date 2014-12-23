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

      InputStream is = null;
      OutputStream os = null;

      try
      {
         is = StreamTextConverterTest.class.getResourceAsStream("data/testTextConversion.rtf");
         os = new FileOutputStream(tempFile);
         tc.convert(new RtfStreamSource(is), os, "UTF-8");
      }

      finally
      {
         if (is != null)
         {
            try
            {
               is.close();
            }

            catch (Exception ex)
            {
               // Ignored
            }
         }

         if (os != null)
         {
            try
            {
               os.close();
            }

            catch (Exception ex)
            {
               // Ignored
            }
         }
      }

      InputStream actualStream = null;
      InputStream expectedStream = null;

      try
      {
         actualStream = new FileInputStream(tempFile);
         expectedStream = StreamTextConverterTest.class.getResourceAsStream("data/testTextConversion.txt");
         String expectedText = TestUtilities.readStreamToString(expectedStream);
         String actualText = TestUtilities.readStreamToString(actualStream);
         assertEquals(expectedText, actualText);
      }

      finally
      {
         try
         {
            actualStream.close();
         }

         catch (Exception ex)
         {
            // Ignored
         }

         try
         {
            expectedStream.close();
         }

         catch (Exception ex)
         {
            // Ignored
         }
      }
   }
}
