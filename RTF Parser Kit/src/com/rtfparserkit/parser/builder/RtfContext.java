package com.rtfparserkit.parser.builder;

import com.rtfparserkit.rtf.Command;

/**
 * Interface for delegating the handling of RTF Parser events to an object which
 * knows how to handle the events based on the current "context" (location in
 * the file and group hierarchy).
 *
 * @author stippi
 */
interface RtfContext {

	public void processGroupStart(RtfContextStack stack);

	public void processGroupStart(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional);

	public void processGroupEnd(RtfContextStack stack);

	public void processCharacterBytes(byte[] data);

	public void processBinaryBytes(byte[] data);

	public void processString(String string);

	public void processCommand(Command command, int parameter,
		boolean hasParameter, boolean optional);

}
