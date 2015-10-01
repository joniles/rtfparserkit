/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document.impl;

import com.rtfparserkit.document.Chunk;
import com.rtfparserkit.document.Style;

/**
 * Default Chunk implementation.
 *
 * @author stippi
 */
public class DefaultChunk implements Chunk {
	private final StringBuilder stringBuilder;
	private final Style style;

	public DefaultChunk(Style style) {
		stringBuilder = new StringBuilder();
		this.style = style;
	}
	
	public String getString() {
		return stringBuilder.toString();
	}
	
	public Style getStyle() {
		return style;
	}

	public void append(String string) {
		stringBuilder.append(string);
	}
}
