/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.document;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rtfparserkit.document.impl.DefaultAnnotation;
import com.rtfparserkit.document.impl.DefaultParagraph;

/**
 *
 * @author stippi
 */
public class ParagraphTest {

	@Test
	public void testEmptyParagraph() {
		DefaultParagraph p = new DefaultParagraph();
		assertEquals("", p.getText());
	}

	@Test
	public void testAppend() {
		DefaultParagraph p = new DefaultParagraph();
		p.append("test1");
		p.append("test2");
		p.append("test3");
		assertEquals("test1test2test3", p.getText());
	}
	
	@Test
	public void testDelimiter() {
		DefaultParagraph p = new DefaultParagraph();
		p.end();
		assertEquals("\n", p.getText());

		p = new DefaultParagraph();
		p.append("test");
		assertEquals("test", p.getText());
		
		p.end();
		assertEquals("test\n", p.getText());
	}

	@Test
	public void testOnlyOneDelimiter() {
		DefaultParagraph p = new DefaultParagraph();
		p.end();
		expectException(p, "\n", true);
		assertEquals("\n", p.getText());
		
		p = new DefaultParagraph();
		p.append("test");
		expectException(p, "\n", false);
		// Paragraph already delimited
		expectException(p, "\n", true);
		// Paragraph must not have changed
		assertEquals("test\n", p.getText());

		p = new DefaultParagraph();
		p.append("test");
		// Appended text may end with a delimiter
		expectException(p, "test\n", false);
		assertEquals("testtest\n", p.getText());
		// Paragraph already delimited, various cases of the text containing
		// a delimiter should all raise an exception
		expectException(p, "test\n", true);
		expectException(p, "\n", true);
		expectException(p, "\ntest", true);
		expectException(p, "test\ntest", true);
		// Paragraph must not have changed
		assertEquals("testtest\n", p.getText());

		p = new DefaultParagraph();
		p.append("test");
		// Appended text may only /end/ with a delimiter
		expectException(p, "test\ntest", true);
		expectException(p, "\ntest", true);
		// Paragraph must not have changed
		assertEquals("test", p.getText());
	}
	
	@Test
	public void testAnnotation() {
		DefaultParagraph p = new DefaultParagraph();
		p.append("test");
		p.append(new DefaultAnnotation());
		p.append("test");
		
		assertEquals(3, p.countElements());

		p = new DefaultParagraph();
		p.append("test");
		p.append(new DefaultAnnotation());
		p.end();

		assertEquals(3, p.countElements());
	}

	private void expectException(DefaultParagraph defaultParagraph, String toAppend,
		boolean exceptionExpected) {
		boolean exceptionRaised = false;
		try {
			defaultParagraph.append(toAppend);
		} catch (Exception e) {
			exceptionRaised = true;
		}
		assertEquals(exceptionExpected, exceptionRaised);
	}
}
