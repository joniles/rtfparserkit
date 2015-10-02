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
import com.rtfparserkit.document.ParagraphStyle;
import com.rtfparserkit.document.Style;

/**
 * Default ParagraphStyle implementation
 */
public class DefaultParagraphStyle extends DefaultCharacterStyle
	implements ParagraphStyle {

	private final ParagraphStyle parent;
	
	private Alignment alignment;
	private float spacingTop;
	private float spacingBottom;
	private float firstLineIndent;
	private float leftIndent;
	private float rightIndent;
	private float lineSpacing;
	// TODO: Support more RTF properties
	
	public DefaultParagraphStyle() {
		parent = null;
		resetToDefaults();
	}

	public DefaultParagraphStyle(ParagraphStyle other) {
		super(other);
		parent = other;
	}

	@Override
	public void resetToDefaults() {
		super.resetToDefaults();

		alignment = Alignment.LEFT;
		spacingTop = 0;
		spacingBottom = 0;
		firstLineIndent = 0;
		leftIndent = 0;
		rightIndent = 0;
		lineSpacing = 0;

		overriddenProperties = EnumSet.allOf(Property.class);
	}

	@Override
	public ParagraphStyle createDerivedStyle() {
		return new DefaultParagraphStyle(this);
	}
	
	public CharacterStyle createDerivedCharacterStyle() {
		return new DefaultCharacterStyle(this);
	}
	
	@Override
	public ParagraphStyle createFlattenedStyle() {
		DefaultParagraphStyle style = new DefaultParagraphStyle();
		style.copyFrom(this);
		return style;
	}
	
	protected void copyFrom(ParagraphStyle style) {
		super.copyFrom(style);

		alignment = style.getAlignment();
		spacingTop = style.getSpacingTop();
		spacingBottom = style.getSpacingBottom();
		firstLineIndent = style.getFirstLineIndent();
		leftIndent = style.getLeftIndent();
		rightIndent = style.getRightIndent();
		lineSpacing = style.getLineSpacing();

		overriddenProperties = EnumSet.allOf(Property.class);
	}
	
	@Override
	public boolean equals(Style object) {
		if (object == this)
			return true;
		if (object == null || !(object instanceof DefaultParagraphStyle))
			return false;
		if (!super.equals(object))
			return false;
		
		DefaultParagraphStyle other = (DefaultParagraphStyle) object;
		return parent == other.parent
			&& alignment == other.alignment
			&& spacingTop == other.spacingTop
			&& spacingBottom == other.spacingBottom
			&& firstLineIndent == other.firstLineIndent
			&& leftIndent == other.leftIndent
			&& rightIndent == other.rightIndent
			&& lineSpacing == other.lineSpacing;
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
	
	@Override
	public String toString() {
		return "ParagraphStyle("
			+ alignment.name() + ", "
			+ (getFont() != null ? getFont().getName() : "<null>")
			+ "@" + getFontSize() + ", "
			+ (getBold() ? "bold, " : "")
			+ (getItalic() ? "italic, " : "")
			+ (getUnderlined() != UnderlineStyle.NONE
				? ("underline: " + getUnderlined().name() + ", ") : "")
			+ "fg: " + getForegroundColor() + ", "
			+ "bg: " + getBackgroundColor()
			+ ")";
	}
}
