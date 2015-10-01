/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document;

/**
 * Simple interface for storing font information.
 *
 * @author stippi
 */
public interface Font {

	public void setName(String name);

	public String getName();

	public boolean equals(Font other);
}
