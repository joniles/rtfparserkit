/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rtfparserkit.document.Chunk;
import com.rtfparserkit.document.DocumentPart;
import com.rtfparserkit.document.Paragraph;
import com.rtfparserkit.document.Style;

/**
 * A list of Paragraph objects. There is always at least one, empty paragraph
 * in the list.
 *
 * @author stippi
 */
public class ParagraphList implements Iterable<DefaultParagraph>, DocumentPart {
	private final List<DefaultParagraph> defaultParagraphs;
	
	/**
	 * Creates a new instance which already contains an empty initial paragraph
	 * by calling clear().
	 */
	public ParagraphList() {
		defaultParagraphs = new ArrayList<DefaultParagraph>();
		clear();
	}

	/**
	 * Returns and iterator over the Paragraphs contained in this list.
	 */
	public Iterator<DefaultParagraph> iterator() {
		return defaultParagraphs.iterator();
	}

	/**
	 * Finalizes the current paragraph by appending a line-break character '\n'.
	 * Starts the next paragraph by appending a new empty Paragraph to the list.
	 */
	public void nextParagraph(Style lastStyle) {
		if (countParagraphs() > 0)
			getCurrentParagraph().end(lastStyle);
		DefaultParagraph next = new DefaultParagraph();
		defaultParagraphs.add(next);
	}

	/**
	 * Implements nextLine() by appending the Unicode character "Line Separator"
	 * to the current paragraph.
	 */
	public void nextLine() {
		getCurrentParagraph().append("\u2028");
	}
	
	/**
	 * @return The concatenated text of all contained Paragraphs.
	 */
	public String getText() {
		StringBuilder builder = new StringBuilder();
		for (DefaultParagraph defaultParagraph : defaultParagraphs)
			builder.append(defaultParagraph.getText());
		return builder.toString();
	}

	public Style createDefaultStyle() {
		return new DefaultStyle();
	}
	
	/**
	 * Implements handleText() by separating the given text at line-breaks
	 * (calling nextParagraph() at '\n') and appending the chunks between 
	 * line-breaks to the currently last Paragraph. This makes sure that all
	 * paragraphs in this list are normalized in that the last paragraph never
	 * contains a line-break, while all preceding paragraphs contain exactly
	 * one line-break which is at the very end of the paragraph.
	 * 
	 * @param text The string to append.
	 * 
	 * @see #nextParagraph()
	 */
	public void append(String text, Style style) {
		int offset = 0;
		while (offset < text.length()) {
			int nextLineBreak = text.indexOf('\n', offset);
			if (nextLineBreak == offset) {
				nextParagraph(style);
				offset++;
				continue;
			}
			
			int end = nextLineBreak > offset ? nextLineBreak : text.length();
			String subString = text.substring(offset, end);
			getCurrentParagraph().append(subString, style);
		
			offset = end;
		}
	}

	public void append(String string) {
		Style style;
		Chunk chunk = getCurrentParagraph().getLastChunk();
		if (chunk != null)
			style = chunk.getStyle();
		else
			style = new DefaultStyle();
		append(string, style);
	}
	
	/**
	 * Removes all Paragraphs that are currently in the list and adds a new
	 * empty Paragraph as the initial Paragraph by calling nextParagraph()
	 */
	public void clear() {
		defaultParagraphs.clear();
		// Add the initial empty paragraph
		nextParagraph(new DefaultStyle());
	}

	/**
	 * @return The last Paragraph of the list. There is always at least
	 * 		one paragraph in the list.
	 */
	public DefaultParagraph getCurrentParagraph() {
		return defaultParagraphs.get(defaultParagraphs.size() - 1);
	}
	
	/**
	 * @return The number of paragraphs in this list.
	 */
	public int countParagraphs() {
		return defaultParagraphs.size();
	}

	/**
	 * Return the Paragraph at the specified index.
	 *  
	 * @param index The index of the desired paragraph. The index must be
	 * 		>= 0 and < countParagraphs().
	 * @return The paragraph at the given index. Throws an
	 * 		IndexOutOfBoundsExpception if index is out of bounds.
	 */
	public Paragraph paragraphAt(int index) {
		return defaultParagraphs.get(index);
	}
}
