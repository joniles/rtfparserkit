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

import java.io.IOException;

/**
 * Classes implementing this interface can be used as the source of RTF data for the parser to consume.
 */
public interface IRtfSource
{
   /**
    * Read a single byte.
    */
   int read() throws IOException;

   /**
    * Push back a single byte to allow it to be read again by the parser.
    */
   void unread(int c) throws IOException;

   /**
    * Read enough bytes to fill the array.
    */
   int read(byte[] b) throws IOException;
}
