/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.parser.builder;

import com.rtfparserkit.document.DocumentSettings;
import com.rtfparserkit.rtf.Command;

/**
 * RtfContext implementing the storing of global settings to the document.
 *
 * @author stippi
 */
class DocumentSettingsContext extends AbstractRtfContext {

	private final DocumentSettings settings;
	
	DocumentSettingsContext(DocumentSettings settings) {
		this.settings = settings;
	}
	
	public void processCommand(Command command, int parameter,
		boolean hasParameter, boolean optional) {
		switch (command) {
		case margl:
			settings.getPageSettings().setPageMarginLeft(parameter);
			break;
		case margr:
			settings.getPageSettings().setPageMarginRight(parameter);
			break;
		case margt:
			settings.getPageSettings().setPageMarginTop(parameter);
			break;
		case margb:
			settings.getPageSettings().setPageMarginBottom(parameter);
			break;
		case paperw:
			settings.getPageSettings().setPageWidth(parameter);
			break;
		case paperh:
			settings.getPageSettings().setPageHeight(parameter);
			break;
		// TODO: More commands...
		}
	}

}
