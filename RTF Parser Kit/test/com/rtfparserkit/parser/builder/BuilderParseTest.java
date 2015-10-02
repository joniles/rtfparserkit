package com.rtfparserkit.parser.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.rtfparserkit.document.Annotation;
import com.rtfparserkit.document.Chunk;
import com.rtfparserkit.document.Document;
import com.rtfparserkit.document.Element;
import com.rtfparserkit.document.Paragraph;
import com.rtfparserkit.document.Section;
import com.rtfparserkit.document.impl.DefaultDocument;
import com.rtfparserkit.parser.RtfStreamSource;
import com.rtfparserkit.parser.standard.StandardRtfParser;

public class BuilderParseTest {

	@Test
	public void testParagraphContentsIText() {
		DefaultDocument document = new DefaultDocument();
		parseStream("lineSeparator", document, false);
	
		Section section = document.getLastSection();
		
		int paragraphCount = section.countParagraphs();
		assertEquals(5, paragraphCount);
		
		assertEquals("INNEN. KÜCHE - TAG\n",
			section.paragraphAt(0).getText());
		assertEquals("Ein Absatz mit Line-Separator:\u2028"
			+ "Der geht hier auf einer neuen Zeile weiter.\n",
			section.paragraphAt(1).getText());
		assertEquals("INNEN. KÜCHE - TAG\n",
			section.paragraphAt(2).getText());
		assertEquals("Hier ist die zweite Szene.\n",
			section.paragraphAt(3).getText());
		assertEquals("",
			section.paragraphAt(4).getText());
	
		assertEquals(12242, document.getPageSize().width);
		assertEquals(15842, document.getPageSize().height);

		assertEquals(1425, document.getPageMargins().left);
		assertEquals(360, document.getPageMargins().right);
		assertEquals(950, document.getPageMargins().top);
		assertEquals(1425, document.getPageMargins().bottom);
	
		assertEquals(2, document.getColorTable().countColors());
	}
	
	@Test
	public void testParagraphContentsLibreOffice() {
		DefaultDocument document = new DefaultDocument();
		parseStream("annotationLibreOffice", document, true);
	
		Section section = document.getLastSection();
		
		int paragraphCount = section.countParagraphs();
		assertEquals(2, paragraphCount);
		
		assertEquals("This word is commented.\n",
			section.paragraphAt(0).getText());
	}
	
	@Test
	public void testStyles() {
		
		String[] files = {
			"variousStyles",
			"variousStylesPages",
			"variousStylesGrouped",
		};
		
		for (String file : files) {
			System.out.println("################################");

			DefaultDocument document = new DefaultDocument();
			parseStream(file, document, false);
		
			assertEquals(1, document.countSections());
			Section section = document.sectionAt(0);
			assertStyles(section);
		}
	}

	@Test
	public void testAnnotations() {
		DefaultDocument document = new DefaultDocument();
		parseStream("annotationSpec", document, false);
		
		Section section = document.sectionAt(0);
		
		assertEquals(1, section.countParagraphs());
		
		Paragraph paragraph = section.paragraphAt(0);
		
		assertEquals(3, paragraph.countElements());
		
		Element element = paragraph.elementAt(0);
		assertTrue(element instanceof Chunk);
		Chunk chunk = (Chunk) element;
		assertEquals("An example of a paradigm might be Darwinian biology.",
			chunk.getText());
	
		element = paragraph.elementAt(1);
		assertTrue(element instanceof Annotation);
		Annotation annotation = (Annotation) element;
		assertEquals("How about some examples that deal with social science? "
			+ "That is what this paper is about.", annotation.getText());
		assertEquals("JD", annotation.getId());
		assertEquals("John Doe", annotation.getAuthor());

		element = paragraph.elementAt(2);
		assertTrue(element instanceof Chunk);
		chunk = (Chunk) element;
		assertEquals(" ", chunk.getText());
	}
	
	private void assertStyles(Section section) {
		int paragraphCount = section.countParagraphs();
		assertEquals(8, paragraphCount);

		for (int i = 0; i < paragraphCount; i++) {
			Paragraph paragraph = section.paragraphAt(i);
			
			if (i == 0)
				assertNormalBoldItalic(paragraph);
//			else {
				String text = paragraph.getText();
				System.out.print("[" + i + "]: " + text);
				if (!text.endsWith("\n"))
					System.out.println();
				for (Element element : paragraph) {
					if (element instanceof Chunk) {
						Chunk chunk = (Chunk) element;
						System.out.println("  '"
							+ chunk.getText().replaceAll("\n",
							"") + "' " + chunk.getStyle());
					}
				}
			}
//		}
	}
	
	private void assertNormalBoldItalic(Paragraph paragraph) {
		int chunks = paragraph.countElements();
		assertEquals(5, chunks);

		Chunk chunk = (Chunk) paragraph.elementAt(0);
		assertEquals("Normal ", chunk.getText());
		assertFalse(chunk.getStyle().getBold());
		assertFalse(chunk.getStyle().getItalic());

		chunk = (Chunk) paragraph.elementAt(1);
		assertEquals("Bold ", chunk.getText());
		assertTrue(chunk.getStyle().getBold());
		assertFalse(chunk.getStyle().getItalic());

		chunk = (Chunk) paragraph.elementAt(2);
		assertEquals("Bold/Italic", chunk.getText());
		assertTrue(chunk.getStyle().getBold());
		assertTrue(chunk.getStyle().getItalic());

		chunk = (Chunk) paragraph.elementAt(3);
		assertEquals(" Italic", chunk.getText());
		assertFalse(chunk.getStyle().getBold());
		assertTrue(chunk.getStyle().getItalic());

		chunk = (Chunk) paragraph.elementAt(4);
		assertEquals(" Normal.\n", chunk.getText());
		assertFalse(chunk.getStyle().getBold());
		assertFalse(chunk.getStyle().getItalic());
	}

	private void parseStream(String fileName, Document document,
		boolean debug) {
		InputStream is = null;
		try {
			is = BuilderParseTest.class.getResourceAsStream("data/"
				+ fileName + ".rtf");
			StandardRtfParser parser = new StandardRtfParser();
			DocumentBuilder builder = new DocumentBuilder(document);
			builder.setDebugEvents(debug);
			parser.parse(new RtfStreamSource(is), builder);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					// Ignore
				}
			}
		}
	}
}
