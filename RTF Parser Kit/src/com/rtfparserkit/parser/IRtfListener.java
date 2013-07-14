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
 * Listener interface implemented by classes to receive event from an RTF parser.
 */
public interface IRtfListener
{
   /**
    * Event raised when the parser starts to read the document.
    */
   public void processDocumentStart();

   /**
    * Event raised when the parser reaches the end of the document.
    */
   public void processDocumentEnd();

   /**
    * Event raised when the stat of a group is encountered.
    */
   public void processGroupStart();

   /**
    * Event raised when the end of a group is encountered.
    */
   public void processGroupEnd();

   /**
    * Event raised by the raw RTF parser to pass bytes representing characters.
    * Note that these bytes will be "as read" from the RTF file, and have not been
    * processed to account for the current encoding.
    */
   public void processCharacterBytes(byte[] data);

   /**
    * Event raised by an RTF parser to pass binary bytes. 
    */
   public void processBinaryBytes(byte[] data);

   /**
    * Event raised by the RTF parser to pass string data. Note that the string
    * has been created with the appropriate encoding and no further processing will
    * be required.
    */
   public void processString(String string);

   /**
    * Event raised by the RTF parser detailing a command read from the file.
    * The parameter argument details the optional integer parameter associated with the
    * command. If the hasParameter flag is false, the parameter argument can be ignored.
    * If the optional flag is set, this is a command which RTF readers can choose not
    * to implement.
    */
   public void processCommand(Command command, int parameter, boolean hasParameter, boolean optional);
}
