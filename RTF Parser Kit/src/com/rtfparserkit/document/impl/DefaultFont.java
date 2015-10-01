/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document.impl;

import com.rtfparserkit.document.Font;

/**
 * Default Font implementation
 *
 * @author stippi
 */
public class DefaultFont implements Font {
	private String name;

	public DefaultFont(String fontName) {
		name = fontName;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public boolean equals(Font object) {
		if (object == this)
			return true;
		if (object == null || object.getClass() != DefaultFont.class)
			return false;
		DefaultFont other = (DefaultFont) object;
		return name.equals(other.name);
	}

	// TODO: Hold all necessary data.

}
