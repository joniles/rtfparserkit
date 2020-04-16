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
 * Interface for storing paragraph style parameters.
 */
public interface ParagraphStyle extends CharacterStyle
{
   public enum Alignment
   {
      LEFT, RIGHT, CENTER, JUSTIFIED, DISTRIBUTED,
   }

   public enum TabAlignment
   {
      LEFT, CENTER, RIGHT, DECIMAL,
   }

   @Override
   public ParagraphStyle createDerivedStyle();

   public CharacterStyle createDerivedCharacterStyle();

   @Override
   public ParagraphStyle createFlattenedStyle();

   @Override
   public ParagraphStyle getParent();

   public void setAlignment(Alignment alignment);

   public Alignment getAlignment();

   public void setSpacingTop(float value);

   public float getSpacingTop();

   public void setSpacingBottom(float value);

   public float getSpacingBottom();

   public void setFirstLineIndent(float value);

   public float getFirstLineIndent();

   public void setLeftIndent(float value);

   public float getLeftIndent();

   public void setRightIndent(float value);

   public float getRightIndent();

   public void setLineSpacing(float value);

   public float getLineSpacing();

   public void addTab(float position, TabAlignment aligment);
}
