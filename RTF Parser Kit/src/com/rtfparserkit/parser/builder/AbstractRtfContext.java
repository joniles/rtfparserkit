/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.parser.builder;

import com.rtfparserkit.rtf.Command;

abstract class AbstractRtfContext implements RtfContext {
	
	private final boolean throwExceptions;
	
	protected AbstractRtfContext() {
		this(true);
	}

	protected AbstractRtfContext(boolean throwExceptions) {
		this.throwExceptions = throwExceptions;
	}

	public void processGroupStart(RtfContextStack stack) {
		handleUnexpectedEvent("Unexpected anonymous group start");
		stack.pushContext(this);
	}

	public void processGroupStart(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
		handleUnexpectedEvent("Unexpected destination group start");
		stack.pushContext(this);
	}

	public void processGroupEnd(RtfContextStack stack) {
		stack.popContext();
	}

	public void processCharacterBytes(byte[] data) {
		handleUnexpectedEvent("Unexpected character bytes");
	}

	public void processBinaryBytes(byte[] data) {
		handleUnexpectedEvent("Unexpected binary bytes");
	}

	public void processString(String string) {
		handleUnexpectedEvent("Unexpected string '" + string + "'");
	}

	public void processCommand(Command command, int parameter,
		boolean hasParameter, boolean optional) {
		handleUnexpectedEvent("Unexpected command '" + command + "'");
	}

	private void handleUnexpectedEvent(String eventInfo) {
		if (throwExceptions)
			throw new IllegalStateException(eventInfo);
	}
}
