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

import com.rtfparserkit.utils.TestUtilities;

public class TextConverterTest
{
   @Test
   public void testTextConversion() throws IOException
   {
      TextConverter tc = new TextConverter();
      File tempFile = File.createTempFile("testTextConversion", ".rtf");
      tempFile.deleteOnExit();

      try (InputStream is = TextConverterTest.class.getResourceAsStream("data/testTextConversion.rtf"); OutputStream os = new FileOutputStream(tempFile))
      {
         tc.convert(is, os, "UTF-8");
      }

      try (InputStream actualStream = new FileInputStream(tempFile); InputStream expectedStream = TextConverterTest.class.getResourceAsStream("data/testTextConversion.txt"))
      {

         String expectedText = TestUtilities.readStreamToString(expectedStream);
         String actualText = TestUtilities.readStreamToString(actualStream);
         assertEquals(expectedText, actualText);
      }
   }
}
