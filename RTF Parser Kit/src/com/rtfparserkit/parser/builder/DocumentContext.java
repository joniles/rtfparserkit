/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.parser.builder;

import com.rtfparserkit.document.Document;
import com.rtfparserkit.rtf.Command;

/**
 * Initial RtfContext. 
 *
 * @author stippi
 */
class DocumentContext extends DocumentPartContext {

	private final Document document;
	
	DocumentContext(Document document) {
		super(document, document);
		this.document = document;
	}

	@Override
	public void processGroupStart(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
		switch (command) {
		case header:
			stack.pushContext(new DocumentPartContext(
				document.getLastSection().createHeader(),
				document));
			break;
		case footer:
			stack.pushContext(new DocumentPartContext(
				document.getLastSection().createFooter(),
				document));
			break;
		case colortbl:
			stack.pushContext(new ColorTableContext(document.getColorTable()));
			break;
		case fonttbl:
			stack.pushContext(new FontTableContext(document.getFontTable()));
			break;

		default:
			// Unknown destinations should be ignored.
			stack.pushContext(new NullContext());
			break;
		}
	}
	
	@Override
	public void processGroupStart(RtfContextStack stack) {
		stack.pushContext(new DocumentPartContext(document, document));
	}

	@Override
	public void processCommand(Command command, int parameter,
		boolean hasParameter, boolean optional) {
		switch (command) {
		case margl:
			document.getDocumentSettings()
				.getPageSettings().setPageMarginLeft(parameter);
			break;
		case margr:
			document.getDocumentSettings()
				.getPageSettings().setPageMarginRight(parameter);
			break;
		case margt:
			document.getDocumentSettings()
				.getPageSettings().setPageMarginTop(parameter);
			break;
		case margb:
			document.getDocumentSettings()
				.getPageSettings().setPageMarginBottom(parameter);
			break;
		case paperw:
			document.getDocumentSettings()
				.getPageSettings().setPageWidth(parameter);
			break;
		case paperh:
			document.getDocumentSettings()
				.getPageSettings().setPageHeight(parameter);
			break;
		
		default:
			super.processCommand(command, parameter, hasParameter, optional);
		}
	}
}
