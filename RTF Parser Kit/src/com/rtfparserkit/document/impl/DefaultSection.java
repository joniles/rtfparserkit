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

import com.rtfparserkit.document.Footer;
import com.rtfparserkit.document.Header;
import com.rtfparserkit.document.PageSettings;
import com.rtfparserkit.document.Section;

/**
 * Default Section implementation
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
