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

import java.util.Calendar;
import java.util.Date;

import com.rtfparserkit.rtf.Command;

/**
 * Context for parsing a time. When the current group is ended, it informs
 * a DateListener provided at construction time about the Date that has been
 * parsed.
 */
class TimeContext extends AbstractRtfContext {
	
	public interface DateListener {
		public void setDate(Date date);
	}

	private Calendar calendar = Calendar.getInstance();

	private final DateListener dateListener;
	
	TimeContext(DateListener listener) {
		dateListener = listener;
	}
	
	@Override
	public void processGroupEnd(RtfContextStack stack) {
		dateListener.setDate(calendar.getTime());
		super.processGroupEnd(stack);
	}

	@Override
	public void processString(String string) {
		try {
			long time = Long.parseLong(string);
			calendar.setTimeInMillis(time * 1000);
		} catch (Exception e) {
			// Ignore
		}
	}

	@Override
	public void processCommand(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
		switch (command) {
		case yr:
			calendar.set(Calendar.YEAR, parameter);
			break;
		case mo:
			calendar.set(Calendar.MONTH, parameter);
			break;
		case dy:
			calendar.set(Calendar.DAY_OF_MONTH, parameter);
			break;
		case hr:
			calendar.set(Calendar.HOUR, parameter);
			break;
		case min:
			calendar.set(Calendar.MINUTE, parameter);
			break;
		case sec:
			calendar.set(Calendar.SECOND, parameter);
			break;

		default:
			super.processCommand(stack, command, parameter, hasParameter,
				optional);
		}
	}
}
