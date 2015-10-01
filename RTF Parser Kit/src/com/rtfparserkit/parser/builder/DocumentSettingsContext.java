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

import com.rtfparserkit.document.DocumentSettings;
import com.rtfparserkit.rtf.Command;

/**
 * RtfContext implementing the storing of global settings to the document.
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
