/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document;

/**
 * Interface for adding a style definition to a global style sheet.
 * 
 * @author stippi
 */
public interface StyleSheet {

	public Style addStyle();

	public Style get(int index);

}
