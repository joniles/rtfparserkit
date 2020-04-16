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

import com.rtfparserkit.rtf.Command;

/**
 * Interface for delegating the handling of RTF Parser events to an object which
 * knows how to handle the events based on the current "context" (location in
 * the file and group hierarchy).
 */
interface RtfContext
{

   public void processGroupStart(RtfContextStack stack);

   public void processGroupStart(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional);

   public void processGroupEnd(RtfContextStack stack);

   public void processCharacterBytes(byte[] data);

   public void processBinaryBytes(byte[] data);

   public void processString(String string);

   public void processCommand(RtfContextStack stack, Command command, int parameter, boolean hasParameter, boolean optional);

}
