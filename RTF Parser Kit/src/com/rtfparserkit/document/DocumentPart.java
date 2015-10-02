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
package com.rtfparserkit.document;

/**
 * Interface for appending styled text and handling paragraphs. A flattened
 * String representation of all contained text can be obtained via the Text
 * functionality.
 */
public interface DocumentPart extends Text {

	public int countParagraphs();
	
	public Paragraph paragraphAt(int index);

	public void append(String text, ParagraphStyle style);
	
	public void nextParagraph(ParagraphStyle lastStyle);
	
	public void nextLine();

	public Annotation appendAnnotation();
	
	public ParagraphStyle createDefaultStyle();
}
