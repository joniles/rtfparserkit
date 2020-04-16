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
 * Interface for getting to all relevant parts of the document model.
 */
public interface Document extends DocumentPart
{

   public FontTable getFontTable();

   public ColorTable getColorTable();

   public StyleSheet getStyleSheet();

   public DocumentSettings getDocumentSettings();

   public int countSections();

   public Section sectionAt(int index);

   public void nextSection();

   public Section getLastSection();
}
