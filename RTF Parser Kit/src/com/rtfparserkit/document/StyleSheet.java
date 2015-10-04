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
 * Interface for adding a style definition to a global style sheet. The style
 * sheet contains four style tables: Paragraph styles, character styles,
 * section styles and table styles. Each have their own index name space.
 */
public interface StyleSheet
{

   public ParagraphStyleTable getParagraphStyleTable();

   public CharacterStyleTable getCharacterStyleTable();

   // TODO: Section style table
   // TODO: Table style table
}
