/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A list of Section objects. There is always at least one, empty section
 * in the list.
 *
 * @author stippi
 */
public class SectionList implements Iterable<DefaultSection> {
	private final List<DefaultSection> sections;
	
	/**
	 * Creates a new instance which already contains an empty initial section
	 * by calling clear().
	 */
	public SectionList() {
		sections = new ArrayList<DefaultSection>();
		clear();
	}

	/**
	 * Returns and iterator over the Sections contained in this list.
	 */
	public Iterator<DefaultSection> iterator() {
		return sections.iterator();
	}

	/**
	 * Starts the next Section by appending a new empty Section to the list.
	 */
	public void nextSection() {
		DefaultSection next = new DefaultSection();
		sections.add(next);
	}

	/**
	 * Removes all Sections that are currently in the list and adds a new
	 * empty Section as the initial Section by calling nextSection()
	 */
	public void clear() {
		sections.clear();
		// Add the initial empty section
		nextSection();
	}

	/**
	 * @return The last Paragraph of the list. There is always at least
	 * 		one paragraph in the list.
	 */
	public DefaultSection getLastSection() {
		return sections.get(sections.size() - 1);
	}
	
	/**
	 * @return The number of paragraphs in this list.
	 */
	public int countSections() {
		return sections.size();
	}

	/**
	 * Return the Section at the specified index.
	 *  
	 * @param index The index of the desired section. The index must be
	 * 		>= 0 and < countSections().
	 * @return The Section at the given index. Throws an
	 * 		IndexOutOfBoundsExpception if index is out of bounds.
	 */
	public DefaultSection sectionAt(int index) {
		return sections.get(index);
	}
	
	/**
	 * @return The concatenated text of all contained Sections.
	 */
	public String getText() {
		StringBuilder builder = new StringBuilder();
		for (DefaultSection section : sections)
			builder.append(section.getText());
		return builder.toString();
	}
}
