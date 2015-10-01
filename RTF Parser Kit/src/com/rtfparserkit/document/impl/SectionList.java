/*
 * Copyright 2015 Stephan Aßmus <superstippi@gmx.de>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rtfparserkit.document.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A list of Section objects. There is always at least one, empty section
 * in the list.
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
