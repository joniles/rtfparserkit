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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class ByteBufferTest
{
   @Test
   public void testBasicOperations()
   {
      ByteBuffer buffer = new ByteBuffer();
      byte[] array = buffer.toArray();
      assertEquals(0, array.length);
      assertTrue(buffer.isEmpty());

      buffer.add(1);
      array = buffer.toArray();
      assertEquals(1, array.length);
      assertEquals(1, array[0]);
      assertFalse(buffer.isEmpty());

      buffer.clear();
      array = buffer.toArray();
      assertEquals(0, array.length);
   }

   @Test
   public void testBufferSizeIncrease()
   {
      Random r = new Random();

      byte[] array = new byte[1025];
      ByteBuffer buffer = new ByteBuffer();

      for (int loop = 0; loop < array.length; loop++)
      {
         byte value = (byte) r.nextInt();
         array[loop] = value;
         buffer.add(value);
      }

      byte[] actualArray = buffer.toArray();
      assertArrayEquals(array, actualArray);
   }
}
