package com.rtfparserkit.parser.builder;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.rtfparserkit.document.Chunk;
import com.rtfparserkit.document.Document;
import com.rtfparserkit.document.Paragraph;
import com.rtfparserkit.document.Section;
import com.rtfparserkit.document.impl.DefaultDocument;
import com.rtfparserkit.parser.RtfStreamSource;
import com.rtfparserkit.parser.standard.StandardRtfParser;

public class BuilderParseTest {

	@Test
	public void testParagraphContents() {
		DefaultDocument document = new DefaultDocument();
		parseStream("lineSeparator", document);
	
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
	public void testStyles() {
		
		String[] files = {
			"variousStyles",
			"variousStylesPages",
			"variousStylesGrouped",
		};
		
		for (String file : files) {
			System.out.println("################################");

			DefaultDocument document = new DefaultDocument();
			parseStream(file, document);
		
			assertEquals(1, document.countSections());
			Section section = document.sectionAt(0);
			assertStyles(section);
		}
	}
	
	private void assertStyles(Section section) {
		int paragraphCount = section.countParagraphs();
		assertEquals(8, paragraphCount);

		for (int i = 0; i < paragraphCount; i++) {
			Paragraph paragraph = section.paragraphAt(i);
			String text = paragraph.getText();
			System.out.print("[" + i + "]: " + text);
			if (!text.endsWith("\n"));
				System.out.println();
			for (Chunk chunk : paragraph) {
				System.out.println("  '" + chunk.getString().replaceAll("\n",
					"") + "' " + chunk.getStyle());
			}
		}
	}

	private void parseStream(String fileName, Document document) {
		InputStream is = null;
		try {
			is = BuilderParseTest.class.getResourceAsStream("data/"
				+ fileName + ".rtf");
			StandardRtfParser parser = new StandardRtfParser();
			parser.parse(new RtfStreamSource(is),
				new DocumentBuilder(document));
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
