/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document;

/**
 * Interface for adding a color to the global color table.
 * 
 * @author stippi
 */
public interface ColorTable {

	public void addColor(int red, int green, int blue);
	
	public int countColors();
	
	public Color colorAt(int index);
}
