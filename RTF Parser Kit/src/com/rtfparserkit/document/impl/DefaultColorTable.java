package com.rtfparserkit.document.impl;

import java.util.ArrayList;
import java.util.List;

import com.rtfparserkit.document.Color;
import com.rtfparserkit.document.ColorTable;

public class DefaultColorTable implements ColorTable {

	private final List<DefaultColor> colors;
	
	public DefaultColorTable() {
		colors = new ArrayList<DefaultColor>();
	}
	
	public void addColor(int red, int green, int blue) {
		colors.add(new DefaultColor(red, green, blue));
	}

	public int countColors() {
		return colors.size();
	}
	
	public Color colorAt(int index) {
		return colors.get(index);
	}
}
