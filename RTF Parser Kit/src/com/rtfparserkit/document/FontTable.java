/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document;

/**
 * Interface for adding a font definition to the global font table.
 * 
 * @author stippi
 */
public interface FontTable {

	public enum FontFamily {
		DEFAULT,
		ROMAN,
		SWISS,
		MODERN,
		SCRIPT,
		DECOR,
		TECH,
		BIDI
	}
	
	public void addFont(String name, String alternativeName, String fileName,
		FontFamily fontFamily);

	public Font getFont(int index);
}
