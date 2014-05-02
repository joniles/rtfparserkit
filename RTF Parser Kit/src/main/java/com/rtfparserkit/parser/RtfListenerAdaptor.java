/*
 * Copyright 2013 Jon Iles
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

package com.rtfparserkit.parser;

import com.rtfparserkit.rtf.Command;

/** 
 * This adaptor class is provided as a convenience for users of the IRtfListener
 * interface. Subclass this class to provide an implementation of IRtfListener
 * and override just the methods you need.
 */
public class RtfListenerAdaptor implements IRtfListener
{
   @Override
   public void processDocumentStart()
   {

   }

   @Override
   public void processDocumentEnd()
   {

   }

   @Override
   public void processGroupStart()
   {

   }

   @Override
   public void processGroupEnd()
   {

   }

   @Override
   public void processCharacterBytes(byte[] data)
   {

   }

   @Override
   public void processBinaryBytes(byte[] data)
   {

   }

   @Override
   public void processString(String string)
   {

   }

   @Override
   public void processCommand(Command command, int parameter, boolean hasParameter, boolean optional)
   {

   }
}
