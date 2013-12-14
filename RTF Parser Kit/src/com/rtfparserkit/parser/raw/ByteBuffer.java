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

package com.rtfparserkit.parser.raw;

/**
 * Implements a simple byte array based buffer. Used to collect
 * data one byte at a time into a buffer, then pass the
 * collected data to the caller as an array.
 */
class ByteBuffer
{
   /**
    * Add a byte to the buffer.
    */
   public void add(int b)
   {
      if (bufferSize == buffer.length)
      {
         byte[] newBuffer = new byte[buffer.length + (buffer.length >> 1)];
         System.arraycopy(buffer, 0, newBuffer, 0, bufferSize);
         buffer = newBuffer;
      }

      buffer[bufferSize++] = (byte) b;
   }

   /**
    * Clear the buffer.
    */
   public void clear()
   {
      bufferSize = 0;
   }

   /**
    * Return the buffer as an array.
    */
   public byte[] toArray()
   {
      byte[] result = new byte[bufferSize];
      System.arraycopy(buffer, 0, result, 0, bufferSize);
      return result;
   }

   /**
    * Determines if the buffer is empty.
    */
   public boolean isEmpty()
   {
      return bufferSize == 0;
   }

   @Override
   public String toString()
   {
      return "[ByteBuffer bufferSize=" + bufferSize + " buffer=" + new String(buffer, 0, bufferSize) + "]";
   }

   private static final int INITIAL_BUFFER_CAPACITY = 10240;
   private int bufferSize;
   private byte[] buffer = new byte[INITIAL_BUFFER_CAPACITY];
}
