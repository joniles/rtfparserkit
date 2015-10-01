/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.parser.builder;

import com.rtfparserkit.rtf.Command;

/**
 * RtfContext ignoring all events.
 *
 * @author stippi
 */
class NullContext implements RtfContext {

	public void processGroupStart(RtfContextStack stack) {
		stack.pushContext(new NullContext());
	}

	public void processGroupStart(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
		processGroupStart(stack);
	}

	public void processGroupEnd(RtfContextStack stack) {
		stack.popContext();
	}

	public void processCharacterBytes(byte[] data) {
		// Ignore
	}

	public void processBinaryBytes(byte[] data) {
		// Ignore
	}

	public void processString(String string) {
		// Ignore
	}

	public void processCommand(Command command, int parameter,
		boolean hasParameter, boolean optional) {
		// Ignore
	}
	
}