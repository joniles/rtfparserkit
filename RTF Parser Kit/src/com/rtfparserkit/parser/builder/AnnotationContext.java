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

import java.util.Date;

import com.rtfparserkit.document.Annotation;
import com.rtfparserkit.parser.builder.TimeContext.DateListener;
import com.rtfparserkit.rtf.Command;

/**
 * Parses the body of an Annotation.
 */
class AnnotationContext extends DocumentPartContext
{

   private final Annotation annotation;
   private final DocumentPartContext parent;

   AnnotationContext(Annotation annotation, DocumentPartContext parent)
   {
      super(annotation, parent.document);
      this.annotation = annotation;
      this.parent = parent;
   }

   @Override
   public void processGroupStart(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
   {
      switch (command)
      {
         case atntime:
         case atndate:
            stack.pushContext(new TimeContext(new DateListener()
            {
               public void setDate(Date date)
               {
                  annotation.setDate(date);
               }
            }));
            break;
         case atnref:
            // TODO: Handle annotation references. The reference is in
            // the parameter.
            stack.pushContext(new AnnotationReferenceContext());
            break;
         case atnparent:
            stack.pushContext(new AnnotationParentContext());
            break;
         case atrfstart:
            stack.pushContext(new BookmarkStartContext());
            break;
         case atrfend:
            stack.pushContext(new BookmarkEndContext());
            break;
         default:
            super.processGroupStart(stack, command, parameter, hasParameter, optional);
      }
   }

   @Override
   public void processGroupEnd(RtfContextStack stack)
   {
      // Inform the parent context that parsing the annotation body has
      // finished.
      parent.annotationFinished();
      super.processGroupEnd(stack);
   }

   private class AnnotationParentContext extends AbstractRtfContext
   {
      @Override
      public void processGroupStart(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional)
      {
         switch (command)
         {
            case atnid:
               stack.pushContext(new AnnotationIdContext());
               break;
            default:
               super.processGroupStart(stack, command, parameter, hasParameter, optional);
         }
      }
   }

   private class AnnotationIdContext extends AbstractRtfContext
   {
      @Override
      public void processString(String string)
      {
         // TODO: The string is the ID of a parent Annotation. Do something
         // with it.
      }
   }

   private class AnnotationReferenceContext extends AbstractRtfContext
   {
   }

   private class BookmarkStartContext extends AbstractRtfContext
   {
   }

   private class BookmarkEndContext extends AbstractRtfContext
   {
   }

}
