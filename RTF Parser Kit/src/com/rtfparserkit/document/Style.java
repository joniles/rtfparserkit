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

package com.rtfparserkit.document;

import java.util.EnumSet;

/**
 * Interface style base class. Also defines a bunch of properties.
 */
public interface Style
{
   public enum Property
   {
      ALIGNMENT, SPACING_TOP, SPACING_BOTTOM, FIRST_LINE_INDENT, LEFT_INDENT, RIGHT_INDENT, LINE_SPACING, TABS, FONT, FONT_SIZE, BOLD, ITALIC, UNDERLINED, STRIKE_OUT, CAPS, BACKGROUND_COLOR, FOREGROUND_COLOR
   }

   public void setName(String name);

   public String getName();

   public Style getParent();

   public EnumSet<Property> getOverriddenProperties();

   public void resetToDefaults();

   public boolean equals(Style other);

   public void setTo(Style other);
}
