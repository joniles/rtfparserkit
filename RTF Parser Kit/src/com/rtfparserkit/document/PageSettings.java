/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document;

/**
 * Interface for controlling page settings
 * 
 * @author stippi
 */
public interface PageSettings {

	public void setPageMarginLeft(int value);
	
	public void setPageMarginRight(int value);
	
	public void setPageMarginTop(int value);
	
	public void setPageMarginBottom(int value);
	
	public void setPageWidth(int value);
	
	public void setPageHeight(int value);
}
