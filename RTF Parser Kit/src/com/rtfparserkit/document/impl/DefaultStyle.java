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

import com.rtfparserkit.document.Color;
import com.rtfparserkit.document.Font;
import com.rtfparserkit.document.Style;

/**
 * Default Style implementation
 */
public class DefaultStyle implements Style {

	public enum Property {
		ALIGNMENT, SPACING_TOP, SPACING_BOTTOM, FIRST_LINE_INDENT, LEFT_INDENT,
		RIGHT_INDENT, LINE_SPACING, TABS, FONT, FONT_SIZE, BOLD, ITALIC,
		UNDERLINED, STRIKE_OUT, CAPS, BACKGROUND_COLOR, FOREGROUND_COLOR
	}
	
	private final Style parent;
	private EnumSet<Property> overriddenProperties;
	
	private Alignment alignment;
	private float spacingTop;
	private float spacingBottom;
	private float firstLineIndent;
	private float leftIndent;
	private float rightIndent;
	private float lineSpacing;
	private Font font;
	private float fontSize;
	// TODO: ...
	private boolean bold;
	private boolean italic;
	private UnderlineStyle underlineStyle;
	private boolean strikeOut;
	private boolean caps;
	private Color backgroundColor;
	private Color foregroundColor;
	
	public DefaultStyle() {
		parent = null;
		resetToDefaults();
	}

	public DefaultStyle(Style other) {
		parent = other;
		overriddenProperties = EnumSet.noneOf(Property.class);
	}

	public void resetToDefaults() {
		alignment = Alignment.LEFT;
		spacingTop = 0;
		spacingBottom = 0;
		firstLineIndent = 0;
		leftIndent = 0;
		rightIndent = 0;
		lineSpacing = 0;
		// TODO: ...
		overriddenProperties = EnumSet.allOf(Property.class);

		resetFontToDefaults();
	}
	
	public void resetFontToDefaults() {
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
		
		overriddenProperties.addAll(EnumSet.of(
			Property.FONT,
			Property.FONT_SIZE,
			Property.BOLD,
			Property.ITALIC,
			Property.UNDERLINED,
			Property.STRIKE_OUT,
			Property.CAPS,
			Property.BACKGROUND_COLOR,
			Property.FOREGROUND_COLOR
		));
	}
	
	public Style createDerivedStyle() {
		return new DefaultStyle(this);
	}
	
	public Style createFlattenedStyle() {
		DefaultStyle style = new DefaultStyle();
		style.copyFrom(this);
		return style;
	}
	
	private void copyFrom(Style style) {
		alignment = style.getAlignment();
		spacingTop = style.getSpacingTop();
		spacingBottom = style.getSpacingBottom();
		firstLineIndent = style.getFirstLineIndent();
		leftIndent = style.getLeftIndent();
		rightIndent = style.getRightIndent();
		lineSpacing = style.getLineSpacing();
		// TODO: ...
		font = style.getFont();
		fontSize = style.getFontSize();
		bold = style.getBold();
		italic = style.getItalic();
		underlineStyle = style.getUnderlined();
		strikeOut = style.getStrikeOut();
		caps = style.getCaps();
		backgroundColor = style.getBackgroundColor();
		foregroundColor = style.getForegroundColor();

		overriddenProperties = EnumSet.allOf(Property.class);
	}
	
	public boolean equals(Style object) {
		if (object == this)
			return true;
		if (object == null || object.getClass() != DefaultStyle.class)
			return false;
		
		DefaultStyle other = (DefaultStyle) object;
		return parent == other.parent
			&& overriddenProperties.equals(other.overriddenProperties)
			&& alignment == other.alignment
			&& spacingTop == other.spacingTop
			&& spacingBottom == other.spacingBottom
			&& firstLineIndent == other.firstLineIndent
			&& leftIndent == other.leftIndent
			&& rightIndent == other.rightIndent
			&& lineSpacing == other.lineSpacing
			// TODO: ...
			&& (font != null ? font.equals(other.font) : other.font == null)
			&& fontSize == other.fontSize
			&& bold == other.bold
			&& italic == other.italic
			&& underlineStyle == other.underlineStyle
			&& strikeOut == other.strikeOut
			&& caps == other.caps
			&& backgroundColor == other.backgroundColor
			&& foregroundColor == other.foregroundColor;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
		overriddenProperties.add(Property.ALIGNMENT);
	}
	
	public Alignment getAlignment() {
		if (overriddenProperties.contains(Property.ALIGNMENT))
			return alignment;
		else
			return parent.getAlignment();
	}
	
	public void setSpacingTop(float value) {
		spacingTop = value;
		overriddenProperties.add(Property.SPACING_TOP);
	}

	public float getSpacingTop() {
		if (overriddenProperties.contains(Property.SPACING_TOP))
			return spacingTop;
		else
			return parent.getSpacingTop();
	}
	
	public void setSpacingBottom(float value) {
		spacingBottom = value;
		overriddenProperties.add(Property.SPACING_BOTTOM);
	}

	public float getSpacingBottom() {
		if (overriddenProperties.contains(Property.SPACING_BOTTOM))
			return spacingBottom;
		else
			return parent.getSpacingBottom();
	}
	
	public void setFirstLineIndent(float value) {
		firstLineIndent = value;
		overriddenProperties.add(Property.FIRST_LINE_INDENT);
	}

	public float getFirstLineIndent() {
		if (overriddenProperties.contains(Property.FIRST_LINE_INDENT))
			return firstLineIndent;
		else
			return parent.getFirstLineIndent();
	}
	
	public void setLeftIndent(float value) {
		leftIndent = value;
		overriddenProperties.add(Property.LEFT_INDENT);
	}

	public float getLeftIndent() {
		if (overriddenProperties.contains(Property.LEFT_INDENT))
			return leftIndent;
		else
			return parent.getLeftIndent();
	}
	
	public void setRightIndent(float value) {
		rightIndent = value;
		overriddenProperties.add(Property.RIGHT_INDENT);
	}

	public float getRightIndent() {
		if (overriddenProperties.contains(Property.RIGHT_INDENT))
			return rightIndent;
		else
			return parent.getRightIndent();
	}
	
	public void setLineSpacing(float value) {
		lineSpacing = value;
		overriddenProperties.add(Property.LINE_SPACING);
	}

	public float getLineSpacing() {
		if (overriddenProperties.contains(Property.LINE_SPACING))
			return lineSpacing;
		else
			return parent.getLineSpacing();
	}
	
	public void addTab(float position, TabAlignment aligment) {
		// TODO Auto-generated method stub
	}
	
	public void setFont(Font font) {
		this.font = font;
		overriddenProperties.add(Property.FONT);
	}
	
	public Font getFont() {
		if (overriddenProperties.contains(Property.BOLD))
			return font;
		else
			return parent.getFont();
	}
	
	public void setFontSize(float value) {
		this.fontSize = value;
		overriddenProperties.add(Property.FONT_SIZE);
	}
	
	public float getFontSize() {
		if (overriddenProperties.contains(Property.FONT_SIZE))
			return fontSize;
		else
			return parent.getFontSize();
	}
	
	public void setBold(boolean bold) {
		this.bold = bold;
		overriddenProperties.add(Property.BOLD);
	}
	
	public boolean getBold() {
		if (overriddenProperties.contains(Property.BOLD))
			return bold;
		else
			return parent.getBold();
	}
	
	public void setItalic(boolean italic) {
		this.italic = italic;
		overriddenProperties.add(Property.ITALIC);
	}

	public boolean getItalic() {
		if (overriddenProperties.contains(Property.ITALIC))
			return italic;
		else
			return parent.getItalic();
	}
	
	public void setUnderlined(UnderlineStyle style) {
		this.underlineStyle = style;
		overriddenProperties.add(Property.UNDERLINED);
	}

	public UnderlineStyle getUnderlined() {
		if (overriddenProperties.contains(Property.UNDERLINED))
			return underlineStyle;
		else
			return parent.getUnderlined();
	}
	
	public void setStrikeOut(boolean strikeOut) {
		this.strikeOut = strikeOut;
		overriddenProperties.add(Property.STRIKE_OUT);
	}

	public boolean getStrikeOut() {
		if (overriddenProperties.contains(Property.STRIKE_OUT))
			return strikeOut;
		else
			return parent.getStrikeOut();
	}
	
	public void setCaps(boolean caps) {
		this.caps = caps;
		overriddenProperties.add(Property.CAPS);
	}

	public boolean getCaps() {
		if (overriddenProperties.contains(Property.CAPS))
			return caps;
		else
			return parent.getCaps();
	}
	
	public void setBackgroundColor(Color color) {
		if (color instanceof DefaultColor) {
			backgroundColor = (DefaultColor) color;
			overriddenProperties.add(Property.BACKGROUND_COLOR);
		}
	}
	
	public Color getBackgroundColor() {
		if (overriddenProperties.contains(Property.BACKGROUND_COLOR))
			return backgroundColor;
		else
			return parent.getBackgroundColor();
	}

	public void setForegroundColor(Color color) {
		if (color instanceof DefaultColor) {
			foregroundColor = (DefaultColor) color;
			overriddenProperties.add(Property.FOREGROUND_COLOR);
		}
	}
	
	public Color getForegroundColor() {
		if (overriddenProperties.contains(Property.FOREGROUND_COLOR))
			return foregroundColor;
		else
			return parent.getForegroundColor();
	}
	
	@Override
	public String toString() {
		return "DefaultStyle("
			+ alignment.name() + ", "
			+ (font != null ? font.getName() : "<null>") + "@" + fontSize + ", "
			+ (bold ? "bold, " : "")
			+ (italic ? "italic, " : "")
			+ (underlineStyle != UnderlineStyle.NONE ? ("underline: " + underlineStyle.name() + ", ") : "")
			+ "fg: " + foregroundColor + ", "
			+ "bg: " + backgroundColor
			+ ")";
	}
}
