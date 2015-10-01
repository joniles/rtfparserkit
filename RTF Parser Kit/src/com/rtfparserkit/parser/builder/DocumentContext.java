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
package com.rtfparserkit.parser.builder;

import com.rtfparserkit.document.Document;
import com.rtfparserkit.rtf.Command;

/**
 * RtfContext for handling the {\rtf group. 
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
	public void processCommand(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
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
			super.processCommand(stack, command, parameter, hasParameter,
				optional);
		}
	}
}
