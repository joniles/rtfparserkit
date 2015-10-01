/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rtfparserkit.document.Chunk;
import com.rtfparserkit.document.Paragraph;
import com.rtfparserkit.document.Style;

/**
 * A Paragraph contains text which is formed by chunks.
 * 
 * @author stippi
 */
public class DefaultParagraph implements Iterable<Chunk>, Paragraph {
	private final List<Chunk> chunks;
	
	public DefaultParagraph() {
		chunks = new ArrayList<Chunk>();
	}

	public Iterator<Chunk> iterator() {
		return chunks.iterator();
	}
	
	public String getText() {
		StringBuilder builder = new StringBuilder();
		for (Chunk chunk : chunks)
			builder.append(chunk.getString());
		return builder.toString();
	}
	
	public int countChunks() {
		return chunks.size();
	}
	
	public Chunk chunkAt(int index) {
		return chunks.get(index);
	}
	
	public void append(Chunk chunk) {
		if (chunk == null)
			throw new IllegalArgumentException("Chunk may not be null");
		chunks.add(chunk);
	}

	public Chunk getLastChunk() {
		if (chunks.size() > 0)
			return chunks.get(chunks.size() - 1);
		return null;
	}

	public void append(String string) {
		Style style;
		Chunk defaultChunk = getLastChunk();
		if (defaultChunk == null)
			style = new DefaultStyle();
		else
			style = defaultChunk.getStyle();
		append(string, style);
	}
	
	public void append(String string, Style style) {
		if (string == null)
			throw new IllegalArgumentException("String may not be null!");
		if (style == null)
			throw new IllegalArgumentException("Style may not be null!");
		int firstLineBreak = string.indexOf('\n');
		if (firstLineBreak >= 0 && firstLineBreak != string.length() - 1) {
			throw new IllegalArgumentException("String must not contain a "
				+ "line-break (\\n) unless right at the end.");
		}
		assertNotDelimited();
		appendString(string, style);
	}

	/**
	 * Makes sure that the paragraph doesn't already end with '\n'.
	 */
	private void assertNotDelimited() {
		Chunk last = getLastChunk();
		if (last != null && last.getString().endsWith("\n")) {
			throw new IllegalArgumentException(
				"Paragraph is already delimited.");
		}
	}
	
	public void end() {
		end(new DefaultStyle());
	}
	
	public void end(Style lastStyle) {
		append("\n", lastStyle);
	}
	
	private void appendString(String string, Style style) {
		Chunk chunk = getLastChunk();
		if (chunk == null || !chunk.getStyle().equals(style)) {
			chunk = new DefaultChunk(style);
			append(chunk);
		}
		chunk.append(string);
	}
}
