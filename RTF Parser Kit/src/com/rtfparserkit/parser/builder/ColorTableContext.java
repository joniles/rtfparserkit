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

import com.rtfparserkit.document.ColorTable;
import com.rtfparserkit.rtf.Command;

/**
 * Processes RTF events that may be encountered in the color table section of
 * the file. Whenever a color entry is closed with ';' a new color entry is
 * added to the ColorTable instance which is passed on construction.
 */
class ColorTableContext extends AbstractRtfContext {

	private final ColorTable table;
	
	private int red;
	private int green;
	private int blue;
	
	ColorTableContext(ColorTable table) {
		this.table = table;
	}

	@Override
	public void processString(String string) {
		if (";".equals(string)) {
			table.addColor(red, green, blue);
		} else {
			throw new IllegalStateException("Unexpected string in color table");
		}
	}

	@Override
	public void processCommand(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
		switch (command) {
		case red:
			red = parameter;
			break;
		case green:
			green = parameter;
			break;
		case blue:
			blue = parameter;
			break;
		default:
			throw new IllegalStateException(
				"Unexpected command in color table");
		}
	}

}
