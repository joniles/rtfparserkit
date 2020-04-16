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
 * Interface for starting a new section in a document. A section can have
 * its own paper settings (for example orientation and margins).
 */
public interface Section extends DocumentPart, Iterable<Paragraph>
{

   public Header createHeader();

   public Footer createFooter();

   public Header getHeader();

   public Footer getFooter();

   public PageSettings getPageSettings();
}
