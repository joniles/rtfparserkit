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

import com.rtfparserkit.document.ParagraphStyle;
import com.rtfparserkit.rtf.Command;

/**
 * RtfContext for parsing a style definition group located in the style sheet
 * section of an RTF document.
 */
class ParagraphStyleContext extends AbstractRtfContext {

	private final ParagraphStyle style;
	
	ParagraphStyleContext(ParagraphStyle style) {
		this.style = style;
	}

	@Override
	public void processGroupStart(RtfContextStack stack) {
		// TODO: Implement
		stack.pushContext(new NullContext());
	}

	@Override
	public void processGroupStart(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
		// TODO: Implement
		stack.pushContext(new NullContext());
	}

	@Override
	public void processString(String string) {
		int semicolon = string.indexOf(';');
		int end = semicolon >= 0 ? semicolon : string.length();
		string = string.substring(0, end);
		style.setName(string);
	}

	@Override
	public void processCommand(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
		// TODO: Implement
	}
}
