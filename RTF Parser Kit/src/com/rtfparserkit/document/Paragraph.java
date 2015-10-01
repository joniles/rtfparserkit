/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document;

/**
 * Interface for paragraph functionality.
 *
 * @author stippi
 */
public interface Paragraph extends Iterable<Chunk> {

	public String getText();
	
	public int countChunks();
	
	public Chunk chunkAt(int index);
}
