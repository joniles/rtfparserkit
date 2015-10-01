package com.rtfparserkit.document;

/**
 * Interface for appending text to a destination.
 *
 * @author stippi
 */
public interface DocumentPart {

	public String getText();

	public int countParagraphs();
	
	public Paragraph paragraphAt(int index);

	public void append(String text, Style style);
	
	public void nextParagraph(Style lastStyle);
	
	public void nextLine();

	public Style createDefaultStyle();
}
