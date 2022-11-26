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

/**
 * Represents a handler which will consume events raised by the parser and handle them
 * appropriately. By default this will typically mean passing them to the listener,
 * but there may be cases where we may wish to implement something like a state machine
 * to consume a set of related events, then take some action based on the complete set
 * of events read, rather than reacting to events one at a time.
 * 
 * This interface allows this functionality to be switched in and out as required.
 */
interface IParserEventHandler
{
   /**
    * The parser informs the handler of an event. 
    */
   public void handleEvent(IParserEvent event);

   /**
    * Retrieve the last event seen by the handler.
    */
   public IParserEvent getLastEvent();

   /**
    * Assumes the handler is buffering events, and removes the last event from this buffer.
    */
   public void removeLastEvent();

   /**
    * Returns false if this handler is OK to receive further events, or true
    * if this handler is complete, and the previous handler should be used again.
    * This assumes that the parser is keeping a stack of handlers and popping the
    * last handler from the stack when the current handler has consumed all the events
    * it can.
    */
   public boolean isComplete();
}
