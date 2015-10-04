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

import java.util.HashMap;
import java.util.Iterator;

import com.rtfparserkit.document.Style;

/**
 * A table of Style objects. Styles can be added and retrieved by ID.
 */
class StyleTable<E extends Style> implements Iterable<E>
{
   private final HashMap<Integer, E> styles;

   StyleTable()
   {
      styles = new HashMap<Integer, E>();
   }

   public Iterator<E> iterator()
   {
      return styles.values().iterator();
   }

   public void addStyle(int id, E style)
   {
      styles.put(id, style);
   }

   public int countStyles()
   {
      return styles.size();
   }

   public E styleFor(int id)
   {
      return styles.get(id);
   }
}
