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

package com.rtfparserkit.parser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Implementation of IRtfSource which will read the RTF file from a stream.
 */
public class RtfStreamSource implements IRtfSource
{
   private final InputStream stream;
   private int pushBackChar = -1;

   public RtfStreamSource(InputStream stream)
   {
      if (stream instanceof BufferedInputStream)
      {
         this.stream = stream;
      }
      else
      {
         this.stream = new BufferedInputStream(stream);
      }
   }

   @Override
   public int read() throws IOException
   {
      int result;

      if (pushBackChar != -1)
      {
         result = pushBackChar;
         pushBackChar = -1;
      }
      else
      {
         result = stream.read();
      }

      return result;
   }

   @Override
   public void unread(int c) throws IOException
   {
      if (pushBackChar != -1)
      {
         throw new IOException("Unread not possible");
      }

      pushBackChar = c;
   }

   @Override
   public int read(byte[] b) throws IOException
   {
      return stream.read(b);
   }
}
