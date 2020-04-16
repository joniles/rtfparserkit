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

import java.util.Stack;

/**
 * RtfContextStack manages a stack of RtfContext instances and has the notion
 * of the current RtfContext (not part of the stack storage).
 */
class RtfContextStack
{

   private final Stack<RtfContext> stack;
   private RtfContext currentContext;

   RtfContextStack(RtfContext initialContext)
   {
      if (initialContext == null)
      {
         throw new IllegalArgumentException("Initial RTF context cannot be null.");
      }
      stack = new Stack<RtfContext>();
      currentContext = initialContext;
   }

   RtfContext getContext()
   {
      return currentContext;
   }

   void pushContext(RtfContext context)
   {
      stack.push(currentContext);
      currentContext = context;
   }

   void popContext()
   {
      if (stack.isEmpty())
      {
         handleError("RTF context stack is empty");
         return;
      }
      currentContext = stack.pop();
   }

   void handleError(String error)
   {
      // TODO: Allow setting an error handler
      throw new IllegalStateException(error);
      //System.out.println(error);
   }
}
