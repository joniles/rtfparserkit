/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document.impl;

import com.rtfparserkit.document.Color;

/**
 *
 * @author stippi
 */
public class DefaultColor implements Color {

	private final int red;
	private final int green;
	private final int blue;

	static final DefaultColor BLACK = new DefaultColor(0, 0, 0);
	static final DefaultColor WHITE = new DefaultColor(255, 255, 255);
	
	public DefaultColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public DefaultColor(Color other) {
		this.red = other.getRed();
		this.green = other.getGreen();
		this.blue = other.getBlue();
	}
	
	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}
	
	@Override
	public String toString() {
		return "(r" + red + "g" + green + "b" + blue + ")";
	}
}
