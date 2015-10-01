/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.parser.builder;

import com.rtfparserkit.document.FontTable;
import com.rtfparserkit.rtf.Command;

/**
 * Processes RTF events that may be encountered in the font table section of
 * the file. Whenever a new group is started, a new FontContext is created to
 * handle the events within the group which specify the font parameters.
 *
 * @author stippi
 */
class FontTableContext extends FontContext {

	FontTableContext(FontTable table) {
		super(table);
	}

	@Override
	public void processGroupStart(RtfContextStack stack) {
		stack.pushContext(new FontContext(fontTable));
	}

	@Override
	public void processGroupStart(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
		stack.pushContext(new FontContext(fontTable));
	}

}
