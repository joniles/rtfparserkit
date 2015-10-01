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
class RootContext extends AbstractRtfContext {

	private final Document document;
	
	RootContext(Document document) {
		this.document = document;
	}

	@Override
	public void processGroupStart(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
		switch (command) {
		case rtf:
			stack.pushContext(new DocumentContext(document));
			break;

		default:
			// Unknown destinations should be ignored.
			stack.pushContext(new NullContext());
			break;
		}
	}
	
	@Override
	public void processGroupStart(RtfContextStack stack) {
		// Unknown groups should be ignored.
		stack.pushContext(new NullContext());
	}
}
