/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document;

/**
 * A chunk of text with a certain style.
 *
 * @author stippi
 */
public interface Chunk {

	public String getString();
	
	public Style getStyle();

	public void append(String string);
}
