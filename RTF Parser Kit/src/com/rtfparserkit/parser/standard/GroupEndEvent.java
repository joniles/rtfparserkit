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

package com.rtfparserkit.parser.standard;

import com.rtfparserkit.parser.IRtfListener;

/**
 * Event represents the end of a group.
 */
class GroupEndEvent implements IParserEvent
{
   /**
    * Pass the event to the listener.
    */
   @Override
   public void fire(IRtfListener listener)
   {
      listener.processGroupEnd();
   }

   /**
    * Retrieve the event type.
    */
   @Override
   public ParserEventType getType()
   {
      return ParserEventType.GROUP_END_EVENT;
   }

   @Override
   public String toString()
   {
      return "[GroupEndEvent]";
   }
}
