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

import com.rtfparserkit.document.Document;
import com.rtfparserkit.parser.IRtfListener;
import com.rtfparserkit.rtf.Command;
import com.rtfparserkit.rtf.CommandType;

/**
 * The main class used for building a Document model from parsing RTF events.
 * The intended use is to construct an instance of DocumentBuilder with an
 * instance of a Document, then pass the DocumentBuilder to
 * StandardParser.parse(RtfSource, RtfListener) as the RtfListener.
 *
 * @author stippi
 */
public class DocumentBuilder implements IRtfListener {

	private int level = 0;
	private boolean atGroupStart = false;
	private boolean debugEvents = false;

	private final RtfContextStack stack;
	
	public DocumentBuilder(Document document) {
		stack = new RtfContextStack(new RootContext(document));
	}
	
	public void setDebugEvents(boolean debug) {
		debugEvents = debug;
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
		if (debugEvents)
			System.out.println(getIndentation() + "processGroupEnd()");
		stack.getContext().processGroupEnd(stack);
		level--;
		atGroupStart = false;
	}

	public void processCharacterBytes(byte[] data) {
		handleDelayedGroupStart();
		if (debugEvents)
			System.out.println(getIndentation() + "processCharacterBytes()");
		stack.getContext().processCharacterBytes(data);
	}

	public void processBinaryBytes(byte[] data) {
		handleDelayedGroupStart();
		if (debugEvents)
			System.out.println(getIndentation() + "processBinaryBytes()");
		stack.getContext().processBinaryBytes(data);
	}

	public void processString(String string) {
		handleDelayedGroupStart();
		if (debugEvents)
			System.out.println(getIndentation() + "processString(" + string + ")");
		stack.getContext().processString(string);
	}

	public void processCommand(Command command, int parameter,
		boolean hasParameter, boolean optional) {
		if (atGroupStart) {
			// Handle delayed group start.
			if (command == Command.optionalcommand) {
				// Optional group with an unknown command, completely ignore
				// this.
				if (debugEvents) {
					System.out.println(getIndentation() + "processGroupStart("
						+ command + ")");
				}
				level++;
				stack.pushContext(new NullContext());
				// Do not handle this command as processCommand() a second time.
				groupStarted();
				return;
			} if (command.getCommandType() == CommandType.Destination) {
				if (debugEvents) {
					System.out.println(getIndentation() + "processGroupStart("
						+ command + ")");
				}
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
		
		if (debugEvents) {
			System.out.print(getIndentation() + "processCommand() " + command);
			if (hasParameter)
				System.out.print(", parameter: " + parameter);
			if (optional)
				System.out.print(" (optional)");
			System.out.println();
		}

		stack.getContext().processCommand(stack, command, parameter,
			hasParameter, optional);
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
			if (debugEvents)
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
