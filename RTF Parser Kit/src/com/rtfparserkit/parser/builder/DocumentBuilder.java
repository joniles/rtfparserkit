package com.rtfparserkit.parser.builder;

import com.rtfparserkit.document.Document;
import com.rtfparserkit.parser.IRtfListener;
import com.rtfparserkit.rtf.Command;
import com.rtfparserkit.rtf.CommandType;

public class DocumentBuilder implements IRtfListener {

	private int level = 0;
	private boolean atGroupStart = false;

	private final RtfContextStack stack;
	
	public DocumentBuilder(Document document) {
		stack = new RtfContextStack(new RootContext(document));
	}
	
	public void processDocumentStart() {
	}

	public void processDocumentEnd() {
	}

	public void processGroupStart() {
		atGroupStart = true;
	}

	public void processGroupEnd() {
		handleDelayedGroupStart();
		System.out.println(getIndentation() + "processGroupEnd()");
		stack.getContext().processGroupEnd(stack);
		level--;
		atGroupStart = false;
	}

	public void processCharacterBytes(byte[] data) {
		handleDelayedGroupStart();
		System.out.println(getIndentation() + "processCharacterBytes()");
		stack.getContext().processCharacterBytes(data);
	}

	public void processBinaryBytes(byte[] data) {
		handleDelayedGroupStart();
		System.out.println(getIndentation() + "processBinaryBytes()");
		stack.getContext().processBinaryBytes(data);
	}

	public void processString(String string) {
		handleDelayedGroupStart();
		System.out.println(getIndentation() + "processString(" + string + ")");
		stack.getContext().processString(string);
	}

	public void processCommand(Command command, int parameter,
		boolean hasParameter, boolean optional) {
		if (atGroupStart) {
			// Handle delayed group start.
			if (command.getCommandType() == CommandType.Destination) {
				System.out.println(getIndentation() + "processGroupStart("
					+ command + ")");
				level++;
				stack.getContext().processGroupStart(stack, command, parameter,
					hasParameter, optional);
				// Do not handle this command as processCommand() a second time.
				groupStarted();
				return;
			} else {
				handleDelayedGroupStart();
			}
		}
		
		System.out.print(getIndentation() + "processCommand() " + command);
		if (hasParameter)
			System.out.print(", parameter: " + parameter);
		if (optional)
			System.out.print(" (optional)");
		System.out.println();

		stack.getContext().processCommand(command, parameter, hasParameter,
			optional);
	}

	private String getIndentation() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < level; i++)
			builder.append("  ");
		builder.append(stack.getContext().getClass().getSimpleName());
		builder.append('.');
		return builder.toString();
	}
	
	private void handleDelayedGroupStart() {
		if (atGroupStart) {
			System.out.println(getIndentation() + "processGroupStart()");
			level++;
			stack.getContext().processGroupStart(stack);
			groupStarted();
		}
	}
	
	private void groupStarted() {
		atGroupStart = false;
	}
}
