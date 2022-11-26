
package com.rtfparserkit.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.rtfparserkit.parser.IRtfParser;
import com.rtfparserkit.parser.RtfStreamSource;
import com.rtfparserkit.parser.standard.StandardRtfParser;

public class RtfDump
{
   public static void main(String[] argv)
   {
      try
      {
         InputStream is = new FileInputStream(argv[0]);
         OutputStream os = new FileOutputStream(argv[1]);
         IRtfParser parser = new StandardRtfParser();
         parser.parse(new RtfStreamSource(is), new RtfDumpListener(os));
         os.close();
      }

      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

}
