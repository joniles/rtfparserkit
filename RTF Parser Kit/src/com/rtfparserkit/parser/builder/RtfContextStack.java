/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.parser.builder;

import java.util.Stack;

/**
 *
 * @author stippi
 */
class RtfContextStack {

	private final Stack<RtfContext> stack;
	private RtfContext currentContext;
	
	RtfContextStack(RtfContext initialContext) {
		if (initialContext == null) {
			throw new IllegalArgumentException(
				"Initial RTF context cannot be null.");
		}
		stack = new Stack<RtfContext>();
		currentContext = initialContext;
	}
	
	RtfContext getContext() {
		return currentContext;
	}
	
	void pushContext(RtfContext context) {
		stack.push(currentContext);
		currentContext = context;
	}
	
	void popContext() {
		if (stack.isEmpty())
			throw new IllegalStateException("RTF context stack is empty");
		currentContext = stack.pop();
	}
}
