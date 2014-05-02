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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.rtfparserkit.parser.IRtfParser;
import com.rtfparserkit.parser.RtfStreamSource;
import com.rtfparserkit.parser.standard.StandardRtfParserTest;

public class TestUtilities
{

   public static String readStreamToString(InputStream is) throws IOException
   {
      StringBuilder result = new StringBuilder();

      byte[] buffer = new byte[1024];
      while (true)
      {
         int bytesRead = is.read(buffer);
         if (bytesRead == -1)
         {
            break;
         }

         result.append(new String(buffer, 0, bytesRead));
      }

      return result.toString();
   }

   public static void assertRtfParserDumpMatches(Object parentTest, IRtfParser parser, String filename) throws Exception
   {

      File outputFile = File.createTempFile(filename, ".xml");
      outputFile.deleteOnExit();
      //File outputFile = new File("c:/temp/" + filename + ".xml");
      InputStream is = parentTest.getClass().getResourceAsStream("data/" + filename + ".rtf");
      try
      {
         OutputStream os = new FileOutputStream(outputFile);
         try
         {
            parser.parse(new RtfStreamSource(is), new RtfDump(os));
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
      InputStream actualStream = new FileInputStream(outputFile);
      try
      {

         InputStream expectedStream = parentTest.getClass().getResourceAsStream("data/" + filename + ".xml");
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

   public static void dump(IRtfParser parser, String filename, String outputFilename) throws Exception
   {

      File outputFile = new File(outputFilename);

      InputStream is = StandardRtfParserTest.class.getResourceAsStream("data/" + filename + ".rtf");
      try
      {
         OutputStream os = new FileOutputStream(outputFile);
         try
         {
            parser.parse(new RtfStreamSource(is), new RtfDump(os));
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
   }
}
