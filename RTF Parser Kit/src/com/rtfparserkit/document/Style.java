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

/**
 * Interface for setting style parameters.
 */
public interface Style {

	public enum Alignment {
		LEFT,
		RIGHT,
		CENTER,
		JUSTIFIED,
		DISTRIBUTED,
	}
	
	public enum TabAlignment {
		LEFT,
		CENTER,
		RIGHT,
		DECIMAL,
	}
	
	public enum UnderlineStyle {
		NONE,
		SINGLE,
		DOUBLE,
		WORD,
		DOTTED,
		DASHED,
		DASH_DOTTED,
		DASH_DOT_DOTTED,
		LONG_DASHED,
		THICK,
		THICK_DOTTED,
		THICK_DASHED,
		THICK_DASH_DOTTED,
		THICK_DASH_DOT_DOTTED,
		THICK_LONG_DASHED,
		WAVE,
		HEAVY_WAVE,
		DOUBLE_WAVE
	}
	
	public void resetToDefaults();
	
	public void resetFontToDefaults();
	
	public Style createDerivedStyle();
	
	public Style createFlattenedStyle();
	
	public boolean equals(Style other);
	
	public void setAlignment(Alignment alignment);
	
	public Alignment getAlignment();
	
	public void setSpacingTop(float value);
	
	public float getSpacingTop();
	
	public void setSpacingBottom(float value);
	
	public float getSpacingBottom();
	
	public void setFirstLineIndent(float value);

	public float getFirstLineIndent();
	
	public void setLeftIndent(float value);
	
	public float getLeftIndent();
	
	public void setRightIndent(float value);
	
	public float getRightIndent();
	
	public void setLineSpacing(float value);
	
	public float getLineSpacing();
	
	public void addTab(float position, TabAlignment aligment);

	public void setFont(Font font);
	
	public Font getFont();
	
	public void setFontSize(float value);
	
	public float getFontSize();
	
	public void setBold(boolean bold);
	
	public boolean getBold();
	
	public void setItalic(boolean italic);

	public boolean getItalic();
	
	public void setUnderlined(UnderlineStyle style);
	
	public UnderlineStyle getUnderlined();
	
	public void setStrikeOut(boolean strikeOut);
	
	public boolean getStrikeOut();
	
	public void setCaps(boolean caps);
	
	public boolean getCaps();
	
	public void setBackgroundColor(Color color);

	public Color getBackgroundColor();

	public void setForegroundColor(Color color);

	public Color getForegroundColor();
}
