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

package com.rtfparserkit.parser.builder;

import com.rtfparserkit.document.Annotation;
import com.rtfparserkit.document.CharacterStyle.UnderlineStyle;
import com.rtfparserkit.document.Document;
import com.rtfparserkit.document.DocumentPart;
import com.rtfparserkit.document.ParagraphStyle;
import com.rtfparserkit.document.ParagraphStyle.Alignment;
import com.rtfparserkit.rtf.Command;

/**
 * RtfContext for processing RTF events which have a main styled text 
 * destination.
 */
class DocumentPartContext extends AbstractRtfContext
{
   private final DocumentPart documentPart;
   protected final Document document;
   private final ParagraphStyle style;

   private Annotation currentAnnotation;

   DocumentPartContext(DocumentPart part, Document document)
   {
      documentPart = part;
      this.document = document;
      style = part.createDefaultStyle();
   }

   DocumentPartContext(DocumentPartContext parent)
   {
      documentPart = parent.documentPart;
      document = parent.document;
      style = parent.style.createDerivedStyle();
   }

   @Override
   public void processGroupStart(RtfContextStack stack)
   {
      stack.pushContext(new DocumentPartContext(this));
   }

   @Override
   public void processGroupStart(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      switch (command)
      {
         case atnid:
            // A new Annotation started
            if (currentAnnotation != null)
            {
               stack.handleError("An annotation has already started, but encountered another annotation ID.");
            }
            currentAnnotation = documentPart.appendAnnotation();
            stack.pushContext(new AnnotationIdContext());
            break;
         case atnauthor:
            stack.pushContext(new AnnotationAuthorContext());
            break;
         case annotation:
            stack.pushContext(new AnnotationContext(currentAnnotation, this));
            break;
         default:
            stack.pushContext(new DocumentPartContext(this));
      }
   }

   @Override
   public void processCharacterBytes(byte[] data)
   {
      // Ignore
   }

   @Override
   public void processBinaryBytes(byte[] data)
   {
      // Ignore
   }

   @Override
   public void processString(String string)
   {
      documentPart.append(string, style.createFlattenedStyle());
   }

   @Override
   public void processCommand(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      switch (command)
      {
         // Paragraph control
         case par:
            documentPart.nextParagraph(style.createFlattenedStyle());
            break;
         case line:
            documentPart.nextLine();
            break;

         // Special characters
         case chatn:
            // This denotes a special character that is associated with an
            // annotation. In most text processing applications, the text
            // cursor can be "before" and "after" an annotation within the text.
            // deleting this text position deletes the annotation.
            // Before \chatn, there should have been an annotation started by
            // \atnid and we have already appended an Annotation object to
            // the DocumentPart. At the latest, this will happen with the
            // next \annotation group.
            break;

         // Text styles
         case plain:
            style.resetToDefaults();
            break;
         case f:
            style.setFont(document.getFontTable().fontFor(parameter));
            break;
         case fs:
            style.setFontSize(fromHalfPoints(parameter));
            break;
         case b:
            if (!hasParameter)
               style.setBold(true);
            else
               style.setBold(parameter != 0);
            break;
         case i:
            if (!hasParameter)
               style.setItalic(true);
            else
               style.setItalic(parameter != 0);
            break;
         case caps:
            if (!hasParameter)
               style.setCaps(true);
            else
               style.setCaps(parameter != 0);
            break;
         case strike:
         case striked:
            if (!hasParameter)
               style.setStrikeOut(true);
            else
               style.setStrikeOut(parameter != 0);
            break;
         case ul:
            setUnderlined(UnderlineStyle.SINGLE, hasParameter, parameter);
            break;
         case uld:
            setUnderlined(UnderlineStyle.DOTTED, hasParameter, parameter);
            break;
         case uldash:
            setUnderlined(UnderlineStyle.DASHED, hasParameter, parameter);
            break;
         case uldashd:
            setUnderlined(UnderlineStyle.DASH_DOTTED, hasParameter, parameter);
            break;
         case uldashdd:
            setUnderlined(UnderlineStyle.DASH_DOT_DOTTED, hasParameter, parameter);
            break;
         case uldb:
            setUnderlined(UnderlineStyle.DOUBLE, hasParameter, parameter);
            break;
         case ulhwave:
            setUnderlined(UnderlineStyle.HEAVY_WAVE, hasParameter, parameter);
            break;
         case ulldash:
            setUnderlined(UnderlineStyle.LONG_DASHED, hasParameter, parameter);
            break;
         case ulnone:
            style.setUnderlined(UnderlineStyle.NONE);
            break;
         case ulth:
            setUnderlined(UnderlineStyle.THICK, hasParameter, parameter);
            break;
         case ulthd:
            setUnderlined(UnderlineStyle.THICK_DOTTED, hasParameter, parameter);
            break;
         case ulthdash:
            setUnderlined(UnderlineStyle.THICK_DASHED, hasParameter, parameter);
            break;
         case ulthdashd:
            setUnderlined(UnderlineStyle.THICK_DASH_DOTTED, hasParameter, parameter);
            break;
         case ulthdashdd:
            setUnderlined(UnderlineStyle.THICK_DASH_DOT_DOTTED, hasParameter, parameter);
            break;
         case ulthldash:
            setUnderlined(UnderlineStyle.THICK_LONG_DASHED, hasParameter, parameter);
            break;
         case ululdbwave:
            setUnderlined(UnderlineStyle.DOUBLE_WAVE, hasParameter, parameter);
            break;
         case ulw:
            setUnderlined(UnderlineStyle.WORD, hasParameter, parameter);
            break;
         case ulwave:
            setUnderlined(UnderlineStyle.WAVE, hasParameter, parameter);
            break;
         case cb:
            style.setBackgroundColor(document.getColorTable().colorAt(parameter));
            break;
         case cf:
            style.setForegroundColor(document.getColorTable().colorAt(parameter));
            break;

         // Alignment
         case qc:
            style.setAlignment(Alignment.CENTER);
            break;
         case qj:
            style.setAlignment(Alignment.JUSTIFIED);
            break;
         case ql:
            style.setAlignment(Alignment.LEFT);
            break;
         case qr:
            style.setAlignment(Alignment.RIGHT);
            break;
         case qd:
            style.setAlignment(Alignment.DISTRIBUTED);
            break;

         // Indents
         case fi:
            style.setFirstLineIndent(fromTwips(parameter));
            break;
         case li:
            style.setLeftIndent(fromTwips(parameter));
            break;
         case ri:
            style.setRightIndent(fromTwips(parameter));
            break;

         // spacing
         case sb:
            style.setSpacingTop(fromTwips(parameter));
            break;
         case sa:
            style.setSpacingBottom(fromTwips(parameter));
            break;
         case sl:
            style.setLineSpacing(fromTwips(parameter));
            break;

         default:
            //			System.out.println("DocumentPartContext.processCommand("
            //				+ command + ") not handled!");
            break;
      }
   }

   private void setUnderlined(UnderlineStyle underline, boolean hasParamter, int parameter)
   {
      if (!hasParamter || parameter == 0)
         style.setUnderlined(UnderlineStyle.NONE);
      else
         style.setUnderlined(underline);
   }

   private static float fromTwips(int value)
   {
      return (float) value / 20.0f;
   }

   private static float fromHalfPoints(int value)
   {
      return (float) value / 2.0f;
   }

   private String append(String string, String toAppend)
   {
      return string != null ? string + toAppend : toAppend;
   }

   void annotationFinished()
   {
      currentAnnotation = null;
   }

   private class AnnotationAuthorContext extends AbstractRtfContext
   {
      @Override
      public void processString(String string)
      {
         if (currentAnnotation != null)
         {
            currentAnnotation.setAuthor(append(currentAnnotation.getAuthor(), string));
         }
      }
   }

   private class AnnotationIdContext extends AbstractRtfContext
   {
      @Override
      public void processString(String string)
      {
         if (currentAnnotation != null)
         {
            currentAnnotation.setId(append(currentAnnotation.getId(), string));
         }
      }
   }

}
