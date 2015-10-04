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

import com.rtfparserkit.document.Annotation;
import com.rtfparserkit.document.Document;
import com.rtfparserkit.document.DocumentSettings;
import com.rtfparserkit.document.PageSettings;
import com.rtfparserkit.document.Paragraph;
import com.rtfparserkit.document.Section;
import com.rtfparserkit.document.ParagraphStyle;

/**
 * Default implementation of Document. Note that Document itself is also
 * a DocumentPart. It implements relevant functionality by forwarding to the
 * last Section.
 */
public class DefaultDocument extends SectionList implements Document
{

   private final DefaultColorTable colors = new DefaultColorTable();
   private final DefaultFontTable fonts = new DefaultFontTable();
   private final DefaultStyleSheet styles = new DefaultStyleSheet();

   private final PageMargins pageMargins = new PageMargins();
   private final PageSize pageSize = new PageSize();

   public DefaultFontTable getFontTable()
   {
      return fonts;
   }

   public DefaultColorTable getColorTable()
   {
      return colors;
   }

   public DefaultStyleSheet getStyleSheet()
   {
      return styles;
   }

   public PageMargins getPageMargins()
   {
      return pageMargins;
   }

   public PageSize getPageSize()
   {
      return pageSize;
   }

   public DocumentSettings getDocumentSettings()
   {
      return new DocumentSettings()
      {

         public PageSettings getPageSettings()
         {
            return new PageSettings()
            {
               public void setPageMarginLeft(int value)
               {
                  pageMargins.left = value;
               }

               public void setPageMarginRight(int value)
               {
                  pageMargins.right = value;
               }

               public void setPageMarginTop(int value)
               {
                  pageMargins.top = value;
               }

               public void setPageMarginBottom(int value)
               {
                  pageMargins.bottom = value;
               }

               public void setPageWidth(int value)
               {
                  pageSize.width = value;
               }

               public void setPageHeight(int value)
               {
                  pageSize.height = value;
               }
            };
         }
      };
   }

   /**
    * Appends a string of text to the last Section.
    * 
    * @param text The string to append
    * @param style The Style in which the appended string is to appear.
    */
   public void append(String text, ParagraphStyle style)
   {
      getLastSection().append(text, style);
   }

   /**
    * Starts a new Paragraph at the last Section and sets the Style of the
    * previous last Paragraph.
    * 
    * @param lastStyle The Style to be set on the previous paragraph.
    */
   public void nextParagraph(ParagraphStyle lastStyle)
   {
      getLastSection().nextParagraph(lastStyle);
   }

   /**
    * Creates a new line at the last Section.
    */
   public void nextLine()
   {
      getLastSection().nextLine();
   }

   /**
    * @return The default Style created by the last Section.
    */
   public ParagraphStyle createDefaultStyle()
   {
      return getLastSection().createDefaultStyle();
   }

   /**
    * @return The total count of all paragraphs contained in all Sections.
    */
   public int countParagraphs()
   {
      int count = 0;
      for (Section section : this)
         count += section.countParagraphs();
      return count;
   }

   /**
    * @param index The index of the paragraph relative to the total paragraph
    * 		count of all Sections.
    */
   public Paragraph paragraphAt(int index)
   {
      int originalIndex = index;
      for (Section section : this)
      {
         int paragrapgsInSection = section.countParagraphs();
         if (index > paragrapgsInSection)
         {
            index -= paragrapgsInSection;
            continue;
         }
         return section.paragraphAt(index);
      }

      throw new IndexOutOfBoundsException("paragraphs in section: " + countParagraphs() + ", requested index: " + originalIndex);
   }

   /**
    * Creates a new Annotation instance and appends it to the last Section.
    * 
    * @return The appended annotation
    */
   public Annotation appendAnnotation()
   {
      return getLastSection().appendAnnotation();
   }
}