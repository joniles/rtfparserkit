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

import com.rtfparserkit.document.Font;

/**
 * Default Font implementation
 */
public class DefaultFont implements Font
{
   private String name;

   public DefaultFont(String fontName)
   {
      name = fontName;
   }

   @Override
   public void setName(String name)
   {
      this.name = name;
   }

   @Override
   public String getName()
   {
      return name;
   }

   @Override
   public boolean equals(Font object)
   {
      if (object == this)
         return true;
      if (object == null || object.getClass() != DefaultFont.class)
         return false;
      DefaultFont other = (DefaultFont) object;
      return name.equals(other.name);
   }

   // TODO: Hold all necessary data.

}
