/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document.impl;

import com.rtfparserkit.document.Footer;
import com.rtfparserkit.document.Header;
import com.rtfparserkit.document.PageSettings;
import com.rtfparserkit.document.Section;

/**
 *
 * @author stippi
 */
public class DefaultSection extends ParagraphList implements Section {

	private DefaultHeader header;
	private DefaultFooter footer;
	private final PageMargins pageMargins = new PageMargins(); 
	private final PageSize pageSize = new PageSize(); 
	
	public Header createHeader() {
		if (header == null)
			header = new DefaultHeader();
		return header;
	}
	
	public Header getHeader() {
		return header;
	}

	public Footer createFooter() {
		if (footer == null)
			footer = new DefaultFooter();
		return footer;
	}
	
	public Footer getFooter() {
		return footer;
	}

	public PageMargins getPageMargins() {
		return pageMargins;
	}

	public PageSize getPageSize() {
		return pageSize;
	}

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

}
