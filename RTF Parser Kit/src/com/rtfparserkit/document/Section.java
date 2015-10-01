/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document;

/**
 * Interface for starting a new section in a document. A section can have
 * its own paper settings (for example orientation and margins).
 *
 * @author stippi
 */
public interface Section extends DocumentPart {

	public Header createHeader();
	
	public Footer createFooter();

	public Header getHeader();
	
	public Footer getFooter();
	
	public PageSettings getPageSettings();
}
