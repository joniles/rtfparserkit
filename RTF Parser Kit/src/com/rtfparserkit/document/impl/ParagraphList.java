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

import com.rtfparserkit.document.Annotation;
import com.rtfparserkit.document.DocumentPart;
import com.rtfparserkit.document.Paragraph;
import com.rtfparserkit.document.ParagraphStyle;

/**
 * A list of Paragraph objects. There is always at least one, empty paragraph
 * in the list.
 */
public class ParagraphList implements Iterable<Paragraph>, DocumentPart
{
   private final List<DefaultParagraph> paragraphs;

   /**
    * Creates a new instance which already contains an empty initial paragraph
    * by calling clear().
    */
   public ParagraphList()
   {
      paragraphs = new ArrayList<DefaultParagraph>();
      clear();
   }

   /**
    * Returns and iterator over the Paragraphs contained in this list.
    */
   public Iterator<Paragraph> iterator()
   {
      return new ParagraphIterator(paragraphs.iterator());
   }

   /**
    * Finalizes the current paragraph by appending a line-break character '\n'.
    * Starts the next paragraph by appending a new empty Paragraph to the list.
    */
   public void nextParagraph(ParagraphStyle lastStyle)
   {
      if (countParagraphs() > 0)
         getLastParagraph().end(lastStyle);
      paragraphs.add(new DefaultParagraph());
   }

   /**
    * Implements nextLine() by appending the Unicode character "Line Separator"
    * to the current paragraph.
    */
   public void nextLine()
   {
      getLastParagraph().append("\u2028");
   }

   /**
    * @return The concatenated text of all contained Paragraphs.
    */
   public String getText()
   {
      StringBuilder builder = new StringBuilder();
      for (Paragraph paragraph : paragraphs)
         builder.append(paragraph.getText());
      return builder.toString();
   }

   public ParagraphStyle createDefaultStyle()
   {
      return new DefaultParagraphStyle();
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
   public void append(String text, ParagraphStyle style)
   {
      int offset = 0;
      while (offset < text.length())
      {
         int nextLineBreak = text.indexOf('\n', offset);
         if (nextLineBreak == offset)
         {
            nextParagraph(style);
            offset++;
            continue;
         }

         int end = nextLineBreak > offset ? nextLineBreak : text.length();
         String subString = text.substring(offset, end);
         getLastParagraph().append(subString, style);

         offset = end;
      }
   }

   public void append(String string)
   {
      ParagraphStyle style = getLastParagraph().getLastStyle();
      append(string, style);
   }

   public Annotation appendAnnotation()
   {
      Annotation annotation = new DefaultAnnotation();
      getLastParagraph().append(annotation);
      return annotation;
   }

   /**
    * Removes all Paragraphs that are currently in the list and adds a new
    * empty Paragraph as the initial Paragraph by calling nextParagraph()
    */
   public void clear()
   {
      paragraphs.clear();
      // Add the initial empty paragraph
      nextParagraph(new DefaultParagraphStyle());
   }

   /**
    * @return The last Paragraph of the list. There is always at least
    * 		one paragraph in the list.
    */
   public DefaultParagraph getLastParagraph()
   {
      return paragraphs.get(paragraphs.size() - 1);
   }

   /**
    * @return The number of paragraphs in this list.
    */
   public int countParagraphs()
   {
      return paragraphs.size();
   }

   /**
    * Return the Paragraph at the specified index.
    *  
    * @param index The index of the desired paragraph. The index must be
    * 		>= 0 and < countParagraphs().
    * @return The paragraph at the given index. Throws an
    * 		IndexOutOfBoundsExpception if index is out of bounds.
    */
   public Paragraph paragraphAt(int index)
   {
      return paragraphs.get(index);
   }

   private static class ParagraphIterator implements Iterator<Paragraph>
   {

      private final Iterator<DefaultParagraph> internalIterator;

      ParagraphIterator(Iterator<DefaultParagraph> iterator)
      {
         internalIterator = iterator;
      }

      public boolean hasNext()
      {
         return internalIterator.hasNext();
      }

      public Paragraph next()
      {
         return internalIterator.next();
      }

      public void remove()
      {
         internalIterator.remove();
      }

   }
}
