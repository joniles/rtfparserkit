/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document.impl;

import com.rtfparserkit.document.Document;
import com.rtfparserkit.document.DocumentSettings;
import com.rtfparserkit.document.PageSettings;
import com.rtfparserkit.document.Paragraph;
import com.rtfparserkit.document.Section;
import com.rtfparserkit.document.Style;

/**
 *
 * @author stippi
 */
public class DefaultDocument extends SectionList implements Document {

	private final DefaultColorTable colors = new DefaultColorTable();
	private final DefaultFontTable fonts = new DefaultFontTable();
	private final DefaultStyleSheet styles = new DefaultStyleSheet();
	
	private final PageMargins pageMargins = new PageMargins(); 
	private final PageSize pageSize = new PageSize(); 
	
	public DefaultFontTable getFontTable() {
		return fonts;
	}

	public DefaultColorTable getColorTable() {
		return colors;
	}
	
	public DefaultStyleSheet getStyleSheet() {
		return styles;
	}
	
	public PageMargins getPageMargins() {
		return pageMargins;
	}

	public PageSize getPageSize() {
		return pageSize;
	}

	public DocumentSettings getDocumentSettings() {
		return new DocumentSettings() {

			public PageSettings getPageSettings() {
				return new PageSettings() {
					public void setPageMarginLeft(int value) {
						pageMargins.left = value;
					}

					public void setPageMarginRight(int value) {
						pageMargins.right = value;
					}

					public void setPageMarginTop(int value) {
						pageMargins.top = value;
					}

					public void setPageMarginBottom(int value) {
						pageMargins.bottom = value;
					}

					public void setPageWidth(int value) {
						pageSize.width = value;
					}

					public void setPageHeight(int value) {
						pageSize.height = value;
					}
				};
			}
		};
	}

	public void append(String text, Style style) {
		getLastSection().append(text, style);
	}

	public void nextParagraph(Style lastStyle) {
		getLastSection().nextParagraph(lastStyle);
	}

	public void nextLine() {
		getLastSection().nextLine();
	}

	public Style createDefaultStyle() {
		return getLastSection().createDefaultStyle();
	}

	public int countParagraphs() {
		int count = 0;
		for (Section section : this)
			count += section.countParagraphs();
		return count;
	}

	public Paragraph paragraphAt(int index) {
		int originalIndex = index;
		for (Section section : this) {
			int paragrapgsInSection = section.countParagraphs();
			if (index > paragrapgsInSection) {
				index -= paragrapgsInSection;
				continue;
			}
			return section.paragraphAt(index);
		}
		
		throw new IndexOutOfBoundsException("paragraphs in section: "
			+ countParagraphs() + ", requested index: " + originalIndex);
	}
}
