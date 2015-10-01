package com.rtfparserkit.document;

/**
 * Interface for getting to all relevant parts of the document model.
 *
 * @author stippi
 */
public interface Document extends DocumentPart {

	public FontTable getFontTable();
	
	public ColorTable getColorTable();
	
	public StyleSheet getStyleSheet();

	public DocumentSettings getDocumentSettings();

	public int countSections();
	
	public Section sectionAt(int index);
	
	public void nextSection();
	
	public Section getLastSection();
}
