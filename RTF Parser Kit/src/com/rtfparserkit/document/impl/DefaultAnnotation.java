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

import java.util.Calendar;
import java.util.Date;

import com.rtfparserkit.document.Annotation;

/**
 * Default implementation for Annotation.
 */
public class DefaultAnnotation extends ParagraphList implements Annotation
{
   private String id = "";
   private String author = "";
   private long date = Calendar.getInstance().getTimeInMillis();

   public DefaultAnnotation()
   {
   }

   @Override
   public void setId(String id)
   {
      this.id = id;
   }

   @Override
   public String getId()
   {
      return id;
   }

   @Override
   public void setAuthor(String author)
   {
      this.author = author;
   }

   @Override
   public String getAuthor()
   {
      return author;
   }

   @Override
   public void setDate(Date date)
   {
      this.date = date.getTime();
   }

   @Override
   public Date getDate()
   {
      return new Date(date);
   }
}
