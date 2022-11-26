
package com.rtfparserkit.utils;

import com.rtfparserkit.parser.IRtfParser;
import com.rtfparserkit.parser.RtfStreamSource;
import com.rtfparserkit.parser.standard.StandardRtfParser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class ImageDump
{
   public static void main(String[] argv)
   {
      try
      {
         InputStream is = new FileInputStream(argv[0]);
         IRtfParser parser = new StandardRtfParser();
         ImageListener listener = new ImageListener() {
            @Override
            public void handleImageData(Map<String, Object> data) {
               // Handle image data here
               System.out.println(data);
            }
         };
         parser.parse(new RtfStreamSource(is), listener);
      }

      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }
}
