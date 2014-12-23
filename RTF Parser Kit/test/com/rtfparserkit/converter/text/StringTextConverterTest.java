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

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.rtfparserkit.parser.RtfStreamSource;
import com.rtfparserkit.utils.TestUtilities;

public class StringTextConverterTest
{
   @Test
   public void testTextConversion() throws IOException
   {
      StringTextConverter tc = new StringTextConverter();

      InputStream is = null;
      try
      {
         is = StringTextConverterTest.class.getResourceAsStream("data/testTextConversion.rtf");
         tc.convert(new RtfStreamSource(is));
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
               // Ignore
            }
         }
      }

      InputStream expectedStream = StringTextConverterTest.class.getResourceAsStream("data/testTextConversion.txt");
      try
      {
         expectedStream = StringTextConverterTest.class.getResourceAsStream("data/testTextConversion.txt");
         String expectedText = TestUtilities.readStreamToString(expectedStream);
         String actualText = tc.getText();
         assertEquals(expectedText, actualText);
      }

      finally
      {
         if (expectedStream != null)
         {
            try
            {
               expectedStream.close();
            }

            catch (Exception ex)
            {
               // Ignore
            }
         }
      }
   }
}
