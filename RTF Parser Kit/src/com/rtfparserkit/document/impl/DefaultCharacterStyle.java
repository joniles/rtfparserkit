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

import java.util.EnumSet;

import com.rtfparserkit.document.CharacterStyle;
import com.rtfparserkit.document.Color;
import com.rtfparserkit.document.Font;
import com.rtfparserkit.document.Style;

/**
 * Default CharacterStyle implementation
 */
public class DefaultCharacterStyle extends DefaultStyle implements CharacterStyle
{

   private final CharacterStyle parent;

   private Font font;
   private float fontSize;
   private boolean bold;
   private boolean italic;
   private UnderlineStyle underlineStyle;
   private boolean strikeOut;
   private boolean caps;
   private Color backgroundColor;
   private Color foregroundColor;
   // TODO: Support more RTF properties

   static private final EnumSet<Property> CHARACTER_STYLE_PROPERTIES = EnumSet.of(Property.FONT, Property.FONT_SIZE, Property.BOLD, Property.ITALIC, Property.UNDERLINED, Property.STRIKE_OUT, Property.CAPS, Property.BACKGROUND_COLOR, Property.FOREGROUND_COLOR);

   public DefaultCharacterStyle()
   {
      parent = null;
      resetToDefaults();
   }

   public DefaultCharacterStyle(CharacterStyle other)
   {
      parent = other;
   }

   public void resetToDefaults()
   {
      // TODO: Mechanics should probably be changed. Instead of having a 
      // method resetFontToDefaults() here, the StyleSheet should have methods
      // to set and get the default style. And Style should have a method
      // to set(Style other) to copy the values from the parameter.
      // Then \plain can be handled by copying the default style. The problem
      // is that there are RTF commands which define the default style values.
      font = new DefaultFont("default");
      fontSize = 12.0f;
      bold = false;
      italic = false;
      underlineStyle = UnderlineStyle.NONE;
      strikeOut = false;
      caps = false;
      backgroundColor = DefaultColor.WHITE;
      foregroundColor = DefaultColor.BLACK;

      overriddenProperties.addAll(CHARACTER_STYLE_PROPERTIES);
   }

   public CharacterStyle createDerivedStyle()
   {
      return new DefaultCharacterStyle(this);
   }

   public CharacterStyle createFlattenedStyle()
   {
      DefaultCharacterStyle style = new DefaultCharacterStyle();
      style.copyFrom(this);
      return style;
   }

   protected void copyFrom(CharacterStyle style)
   {
      font = style.getFont();
      fontSize = style.getFontSize();
      bold = style.getBold();
      italic = style.getItalic();
      underlineStyle = style.getUnderlined();
      strikeOut = style.getStrikeOut();
      caps = style.getCaps();
      backgroundColor = style.getBackgroundColor();
      foregroundColor = style.getForegroundColor();

      overriddenProperties.addAll(CHARACTER_STYLE_PROPERTIES);
   }

   @Override
   public boolean equals(Style object)
   {
      if (object == this)
         return true;
      if (object == null || !(object instanceof DefaultCharacterStyle))
         return false;
      if (!super.equals(object))
         return false;

      DefaultCharacterStyle other = (DefaultCharacterStyle) object;
      return parent == other.parent && (font != null ? font.equals(other.font) : other.font == null) && fontSize == other.fontSize && bold == other.bold && italic == other.italic && underlineStyle == other.underlineStyle && strikeOut == other.strikeOut && caps == other.caps && backgroundColor == other.backgroundColor && foregroundColor == other.foregroundColor;
   }

   public void setFont(Font font)
   {
      this.font = font;
      overriddenProperties.add(Property.FONT);
   }

   public Font getFont()
   {
      if (overriddenProperties.contains(Property.BOLD))
         return font;
      else
         return parent.getFont();
   }

   public void setFontSize(float value)
   {
      this.fontSize = value;
      overriddenProperties.add(Property.FONT_SIZE);
   }

   public float getFontSize()
   {
      if (overriddenProperties.contains(Property.FONT_SIZE))
         return fontSize;
      else
         return parent.getFontSize();
   }

   public void setBold(boolean bold)
   {
      this.bold = bold;
      overriddenProperties.add(Property.BOLD);
   }

   public boolean getBold()
   {
      if (overriddenProperties.contains(Property.BOLD))
         return bold;
      else
         return parent.getBold();
   }

   public void setItalic(boolean italic)
   {
      this.italic = italic;
      overriddenProperties.add(Property.ITALIC);
   }

   public boolean getItalic()
   {
      if (overriddenProperties.contains(Property.ITALIC))
         return italic;
      else
         return parent.getItalic();
   }

   public void setUnderlined(UnderlineStyle style)
   {
      this.underlineStyle = style;
      overriddenProperties.add(Property.UNDERLINED);
   }

   public UnderlineStyle getUnderlined()
   {
      if (overriddenProperties.contains(Property.UNDERLINED))
         return underlineStyle;
      else
         return parent.getUnderlined();
   }

   public void setStrikeOut(boolean strikeOut)
   {
      this.strikeOut = strikeOut;
      overriddenProperties.add(Property.STRIKE_OUT);
   }

   public boolean getStrikeOut()
   {
      if (overriddenProperties.contains(Property.STRIKE_OUT))
         return strikeOut;
      else
         return parent.getStrikeOut();
   }

   public void setCaps(boolean caps)
   {
      this.caps = caps;
      overriddenProperties.add(Property.CAPS);
   }

   public boolean getCaps()
   {
      if (overriddenProperties.contains(Property.CAPS))
         return caps;
      else
         return parent.getCaps();
   }

   public void setBackgroundColor(Color color)
   {
      if (color instanceof DefaultColor)
      {
         backgroundColor = (DefaultColor) color;
         overriddenProperties.add(Property.BACKGROUND_COLOR);
      }
   }

   public Color getBackgroundColor()
   {
      if (overriddenProperties.contains(Property.BACKGROUND_COLOR))
         return backgroundColor;
      else
         return parent.getBackgroundColor();
   }

   public void setForegroundColor(Color color)
   {
      if (color instanceof DefaultColor)
      {
         foregroundColor = (DefaultColor) color;
         overriddenProperties.add(Property.FOREGROUND_COLOR);
      }
   }

   public Color getForegroundColor()
   {
      if (overriddenProperties.contains(Property.FOREGROUND_COLOR))
         return foregroundColor;
      else
         return parent.getForegroundColor();
   }

   @Override
   public String toString()
   {
      return "DefaultCharacterStyle(" + (font != null ? font.getName() : "<null>") + "@" + fontSize + ", " + (bold ? "bold, " : "") + (italic ? "italic, " : "") + (underlineStyle != UnderlineStyle.NONE ? ("underline: " + underlineStyle.name() + ", ") : "") + "fg: " + foregroundColor + ", " + "bg: " + backgroundColor + ")";
   }
}
