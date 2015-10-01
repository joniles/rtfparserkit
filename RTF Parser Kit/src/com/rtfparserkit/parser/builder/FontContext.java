/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.parser.builder;

import com.rtfparserkit.document.FontTable;
import com.rtfparserkit.document.FontTable.FontFamily;
import com.rtfparserkit.rtf.Command;

/**
 * RtfContext implementation which handles RTF events inside a group inside
 * of the font table section.
 *
 * @author stippi
 */
class FontContext extends AbstractRtfContext {

	protected final FontTable fontTable;
	
	private String fontName = "";
	private String alternativeName = "";
	private String fileName = "";
	private FontFamily fontFamily = FontFamily.DEFAULT;
	
	private enum ExpectedName {
		DEFAULT,
		ALTERNATIVE,
		FILE
	}
	
	private ExpectedName expectedName = ExpectedName.DEFAULT;
	
	FontContext(FontTable fontTable) {
		this.fontTable = fontTable;
	}
	
	@Override
	public void processGroupEnd(RtfContextStack stack) {
		super.processGroupEnd(stack);
	}
	
	@Override
	public void processString(String string) {
		int offset = 0;
		while (offset < string.length()) {
			// Chop off anything after and including the first semicolon
			int semicolon = string.indexOf(';', offset);
			
			if (semicolon == offset) {
				fontTable.addFont(fontName, alternativeName, fileName,
					fontFamily);
				offset = semicolon + 1;
				continue;
			}
			
			int end = semicolon >= 0 ? semicolon : string.length();
			
			String name = string.substring(offset, end);
			
			switch (expectedName) {
			case DEFAULT:
				fontName = name;
				break;
			case ALTERNATIVE:
				alternativeName = name;
				break;
			case FILE:
				fileName = name;
				break;
			}
			
			offset = end;
		}
	}

	@Override
	public void processCommand(Command command, int parameter,
		boolean hasParameter, boolean optional) {
		switch (command) {
		case fname:
			expectedName = ExpectedName.DEFAULT;
			break;
		case falt:
			expectedName = ExpectedName.ALTERNATIVE;
			break;
		case fontfile:
			expectedName = ExpectedName.FILE;
			break;

		case fnil:
			fontFamily = FontFamily.DEFAULT;
			break;
		case froman:
			fontFamily = FontFamily.ROMAN;
			break;
		case fswiss:
			fontFamily = FontFamily.SWISS;
			break;
		case fmodern:
			fontFamily = FontFamily.MODERN;
			break;
		case fscript:
			fontFamily = FontFamily.SCRIPT;
			break;
		case fdecor:
			fontFamily = FontFamily.DECOR;
			break;
		case ftech:
			fontFamily = FontFamily.TECH;
			break;
		case fbidi:
			fontFamily = FontFamily.BIDI;
			break;
		}
	}
}
