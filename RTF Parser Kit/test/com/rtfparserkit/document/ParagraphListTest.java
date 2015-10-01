/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rtfparserkit.document.impl.ParagraphList;

/**
 *
 * @author stippi
 */
public class ParagraphListTest {

	@Test
	public void testEmptyParagraphList() {
		ParagraphList list = new ParagraphList();
		assertEquals(1, list.countParagraphs());
		assertEquals("", list.getText());
	}

	@Test
	public void testAppend() {
		ParagraphList list = new ParagraphList();
		list.append("test1");
		list.append("test2");
		list.append("test3");
		assertEquals(1, list.countParagraphs());
		assertEquals("test1test2test3", list.getText());
	}
	
	@Test
	public void testDelimiter() {
		ParagraphList list = new ParagraphList();
		list.append("\n");
		assertEquals(2, list.countParagraphs());
		assertEquals("\n", list.getText());
		assertEquals("\n", list.paragraphAt(0).getText());
		assertEquals("", list.paragraphAt(1).getText());

		list.append("\n\n");
		assertEquals(4, list.countParagraphs());
		assertEquals("\n\n\n", list.getText());
		assertEquals("\n", list.paragraphAt(2).getText());
		assertEquals("", list.paragraphAt(3).getText());
		
		list = new ParagraphList();
		list.append("test1");
		list.append("test2\ntest3\n");
		assertEquals(3, list.countParagraphs());
		assertEquals("test1test2\ntest3\n", list.getText());
		assertEquals("test1test2\n", list.paragraphAt(0).getText());
		assertEquals("test3\n", list.paragraphAt(1).getText());
		assertEquals("", list.paragraphAt(2).getText());
	}
}
