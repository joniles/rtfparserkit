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

import com.rtfparserkit.document.Chunk;
import com.rtfparserkit.document.Element;
import com.rtfparserkit.document.Paragraph;
import com.rtfparserkit.document.ParagraphStyle;

/**
 * Default Paragraph implementation
 */
public class DefaultParagraph implements Iterable<Element>, Paragraph
{
   private final List<Element> chunks;
   private ParagraphStyle style;

   public DefaultParagraph()
   {
      this(new DefaultParagraphStyle());
   }

   public DefaultParagraph(ParagraphStyle style)
   {
      chunks = new ArrayList<Element>();
      this.style = style;
   }

   @Override
   public Iterator<Element> iterator()
   {
      return chunks.iterator();
   }

   @Override
   public String getText()
   {
      StringBuilder builder = new StringBuilder();
      for (Element element : chunks)
      {
         if (element instanceof Chunk)
            builder.append(((Chunk) element).getText());
      }
      return builder.toString();
   }

   @Override
   public int countElements()
   {
      return chunks.size();
   }

   @Override
   public Element elementAt(int index)
   {
      return chunks.get(index);
   }

   @Override
   public ParagraphStyle getStyle()
   {
      return style;
   }

   public void append(Element element)
   {
      if (element == null)
         throw new IllegalArgumentException("Element may not be null");
      chunks.add(element);
   }

   /**
    * Appends the string to the Paragraph. If the last Element of the Paragraph
    * is a Chunk, the string will be appended to the Chunk. Otherwise a new
    * Chunk will be created to hold the String. If the Paragraph already
    * contains any Chunks, the Style of the last Chunk is used for the string. 
    * The paragraph may not already be delimited (end with '\n'). The appended
    * string may contain a delimiter ('\n'), but it must be at the end of the
    * string.
    * 
    * @param string The string to append
    */
   public void append(String string)
   {
      append(string, getLastStyle());
   }

   /**
    * Appends the string to the Paragraph. If the last Element of the Paragraph
    * is a Chunk, the string will be appended to that Chunk if the Style
    * matches the Style of the last Chunk. Otherwise a new Chunk with the given
    * Style will be created to hold the String. 
    * The paragraph may not already be delimited (end with '\n'). The appended
    * string may contain a delimiter ('\n'), but it must be at the end of the
    * string.
    * 
    * @param string The string to append
    * @param style The Style in which the string is to appear
    */
   public void append(String string, ParagraphStyle style)
   {
      if (string == null)
         throw new IllegalArgumentException("String may not be null!");
      if (style == null)
         throw new IllegalArgumentException("Style may not be null!");
      int firstLineBreak = string.indexOf('\n');
      if (firstLineBreak >= 0 && firstLineBreak != string.length() - 1)
      {
         throw new IllegalArgumentException("String must not contain a " + "line-break (\\n) unless right at the end.");
      }
      assertNotDelimited();
      appendString(string, style);
   }

   public void end()
   {
      end(getLastStyle());
   }

   public void end(ParagraphStyle lastStyle)
   {
      append("\n", lastStyle);
   }

   public ParagraphStyle getLastStyle()
   {
      // Try to use the last used Style instead of the default style
      Chunk lastChunk = findLastChunk();
      if (lastChunk != null)
         return lastChunk.getStyle();

      return style;
   }

   /**
    * Makes sure that the paragraph doesn't already end with '\n'.
    */
   private void assertNotDelimited()
   {
      for (int i = chunks.size() - 1; i >= 0; i--)
      {
         Element element = chunks.get(i);
         if (element instanceof Chunk)
         {
            Chunk chunk = (Chunk) element;
            if (chunk.getText().endsWith("\n"))
            {
               throw new IllegalArgumentException("Paragraph is already delimited.");
            }
            break;
         }
      }
   }

   private void appendString(String string, ParagraphStyle style)
   {
      Chunk chunk = null;
      if (chunks.size() > 0)
      {
         Element last = chunks.get(chunks.size() - 1);
         if (last instanceof Chunk)
            chunk = (Chunk) last;
      }
      if (chunk == null || !chunk.getStyle().equals(style))
      {
         chunk = new DefaultChunk(style);
         append(chunk);
      }
      chunk.append(string);
   }

   private Chunk findLastChunk()
   {
      for (int i = chunks.size() - 1; i >= 0; i--)
      {
         Element element = chunks.get(i);
         if (element instanceof Chunk)
            return (Chunk) element;
      }

      return null;
   }
}
