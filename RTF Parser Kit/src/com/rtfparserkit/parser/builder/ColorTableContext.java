/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.parser.builder;

import com.rtfparserkit.document.ColorTable;
import com.rtfparserkit.rtf.Command;

/**
 * Processes RTF events that may be encountered in the color table section of
 * the file. Whenever a color entry is closed with ';' a new color entry is
 * added to the ColorTable instance which is passed on construction.
 *
 * @author stippi
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
	public void processCommand(Command command, int parameter,
		boolean hasParameter, boolean optional) {
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
