package com.rtfparserkit.document.impl;

import java.util.ArrayList;
import java.util.List;

import com.rtfparserkit.document.StyleSheet;

public class DefaultStyleSheet implements StyleSheet {

	private final List<DefaultStyle> styles;
	
	public DefaultStyleSheet() {
		styles = new ArrayList<DefaultStyle>();
	}
	
	public DefaultStyle addStyle() {
		DefaultStyle style = new DefaultStyle();
		styles.add(style);
		return style;
	}

	public int countStyles() {
		return styles.size();
	}
	
	public DefaultStyle get(int index) {
		return styles.get(index);
	}
}
