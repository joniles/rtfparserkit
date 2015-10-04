/*
 * Copyright 2015 Stephan Aßmus <superstippi@gmx.de>
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

package com.rtfparserkit.document.impl;

import com.rtfparserkit.document.Color;

/**
 * Default Color implementation. After creation, DefaultColor is not mutable.
 */
public class DefaultColor implements Color
{

   private final int red;
   private final int green;
   private final int blue;

   static final DefaultColor BLACK = new DefaultColor(0, 0, 0);
   static final DefaultColor WHITE = new DefaultColor(255, 255, 255);

   public DefaultColor(int red, int green, int blue)
   {
      this.red = red;
      this.green = green;
      this.blue = blue;
   }

   public DefaultColor(Color other)
   {
      this.red = other.getRed();
      this.green = other.getGreen();
      this.blue = other.getBlue();
   }

   public int getRed()
   {
      return red;
   }

   public int getGreen()
   {
      return green;
   }

   public int getBlue()
   {
      return blue;
   }

   @Override
   public String toString()
   {
      return "(r" + red + "g" + green + "b" + blue + ")";
   }
}
