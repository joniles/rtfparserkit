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

import com.rtfparserkit.document.Footer;
import com.rtfparserkit.document.Header;
import com.rtfparserkit.document.PageSettings;
import com.rtfparserkit.document.Section;

/**
 * Default Section implementation
 */
public class DefaultSection extends ParagraphList implements Section
{

   private DefaultHeader header;
   private DefaultFooter footer;
   private final PageMargins pageMargins = new PageMargins();
   private final PageSize pageSize = new PageSize();

   @Override
   public Header createHeader()
   {
      if (header == null)
         header = new DefaultHeader();
      return header;
   }

   @Override
   public Header getHeader()
   {
      return header;
   }

   @Override
   public Footer createFooter()
   {
      if (footer == null)
         footer = new DefaultFooter();
      return footer;
   }

   @Override
   public Footer getFooter()
   {
      return footer;
   }

   public PageMargins getPageMargins()
   {
      return pageMargins;
   }

   public PageSize getPageSize()
   {
      return pageSize;
   }

   @Override
   public PageSettings getPageSettings()
   {
      return new PageSettings()
      {

         @Override
         public void setPageMarginLeft(int value)
         {
            pageMargins.left = value;
         }

         @Override
         public void setPageMarginRight(int value)
         {
            pageMargins.right = value;
         }

         @Override
         public void setPageMarginTop(int value)
         {
            pageMargins.top = value;
         }

         @Override
         public void setPageMarginBottom(int value)
         {
            pageMargins.bottom = value;
         }

         @Override
         public void setPageWidth(int value)
         {
            pageSize.width = value;
         }

         @Override
         public void setPageHeight(int value)
         {
            pageSize.height = value;
         }
      };
   }

}
