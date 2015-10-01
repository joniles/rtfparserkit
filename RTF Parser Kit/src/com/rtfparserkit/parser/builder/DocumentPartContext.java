/**
 * Copyright 2015 DramaQueen GmbH. All rights reserved.
 */
package com.rtfparserkit.parser.builder;

import com.rtfparserkit.document.ColorTable;
import com.rtfparserkit.document.Document;
import com.rtfparserkit.document.DocumentPart;
import com.rtfparserkit.document.FontTable;
import com.rtfparserkit.document.Style;
import com.rtfparserkit.document.Style.Alignment;
import com.rtfparserkit.document.Style.UnderlineStyle;
import com.rtfparserkit.document.StyleSheet;
import com.rtfparserkit.rtf.Command;

/**
 *
 * @author stippi
 */
class DocumentPartContext extends AbstractRtfContext {

	private final DocumentPart documentPart;
	private final ColorTable colorTable;
	private final FontTable fontTable;
	private final StyleSheet styleSheet;
	private final Style style;
	
	DocumentPartContext(DocumentPart part, Document document) {
		documentPart = part;
		this.colorTable = document.getColorTable();
		this.fontTable = document.getFontTable();
		this.styleSheet = document.getStyleSheet();
		style = part.createDefaultStyle();
	}

	DocumentPartContext(DocumentPartContext parent) {
		documentPart = parent.documentPart;
		colorTable = parent.colorTable;
		fontTable = parent.fontTable;
		styleSheet = parent.styleSheet;
		style = parent.style.createDerivedStyle();
	}

	@Override
	public void processGroupStart(RtfContextStack stack) {
		// TODO: Push new style context
		stack.pushContext(new DocumentPartContext(this));
	}

	@Override
	public void processGroupStart(RtfContextStack stack, Command command,
		int parameter, boolean hasParameter, boolean optional) {
		// TODO: Push new style context
		stack.pushContext(new DocumentPartContext(this));
	}

	@Override
	public void processCharacterBytes(byte[] data) {
		// Ignore
	}

	@Override
	public void processBinaryBytes(byte[] data) {
		// Ignore
	}

	@Override
	public void processString(String string) {
		documentPart.append(string, style.createFlattenedStyle());
	}

	@Override
	public void processCommand(Command command, int parameter,
		boolean hasParameter, boolean optional) {
		switch (command) {
		case par:
			documentPart.nextParagraph(style.createFlattenedStyle());
			break;
		case line:
			documentPart.nextLine();
			break;
		
		// Text styles
		case plain:
			style.resetFontToDefaults();
			break;	
		case f:
			style.setFont(fontTable.getFont(parameter));
			break;
		case fs:
			style.setFontSize(fromHalfPoints(parameter));
			break;
		case b:
			if (!hasParameter)
				style.setBold(true);
			else
				style.setBold(parameter != 0);
			break;
		case i:
			if (!hasParameter)
				style.setItalic(true);
			else
				style.setItalic(parameter != 0);
			break;
		case caps:
			if (!hasParameter)
				style.setCaps(true);
			else
				style.setCaps(parameter != 0);
			break;
		case strike:
		case striked:
			if (!hasParameter)
				style.setStrikeOut(true);
			else
				style.setStrikeOut(parameter != 0);
			break;
		case ul:
			setUnderlined(UnderlineStyle.SINGLE, hasParameter, parameter);
			break;
		case uld:
			setUnderlined(UnderlineStyle.DOTTED, hasParameter, parameter);
			break;
		case uldash:
			setUnderlined(UnderlineStyle.DASHED, hasParameter, parameter);
			break;
		case uldashd:
			setUnderlined(UnderlineStyle.DASH_DOTTED, hasParameter, parameter);
			break;
		case uldashdd:
			setUnderlined(UnderlineStyle.DASH_DOT_DOTTED, hasParameter,
				parameter);
			break;
		case uldb:
			setUnderlined(UnderlineStyle.DOUBLE, hasParameter, parameter);
			break;
		case ulhwave:
			setUnderlined(UnderlineStyle.HEAVY_WAVE, hasParameter, parameter);
			break;
		case ulldash:
			setUnderlined(UnderlineStyle.LONG_DASHED, hasParameter, parameter);
			break;
		case ulnone:
			style.setUnderlined(UnderlineStyle.NONE);
			break;
		case ulth:
			setUnderlined(UnderlineStyle.THICK, hasParameter, parameter);
			break;
		case ulthd:
			setUnderlined(UnderlineStyle.THICK_DOTTED, hasParameter, parameter);
			break;
		case ulthdash:
			setUnderlined(UnderlineStyle.THICK_DASHED, hasParameter, parameter);
			break;
		case ulthdashd:
			setUnderlined(UnderlineStyle.THICK_DASH_DOTTED, hasParameter,
				parameter);
			break;
		case ulthdashdd:
			setUnderlined(UnderlineStyle.THICK_DASH_DOT_DOTTED, hasParameter,
				parameter);
			break;
		case ulthldash:
			setUnderlined(UnderlineStyle.THICK_LONG_DASHED, hasParameter,
				parameter);
			break;
		case ululdbwave:
			setUnderlined(UnderlineStyle.DOUBLE_WAVE, hasParameter, parameter);
			break;
		case ulw:
			setUnderlined(UnderlineStyle.WORD, hasParameter,
				parameter);
			break;
		case ulwave:
			setUnderlined(UnderlineStyle.WAVE, hasParameter, parameter);
			break;
		case cb:
			style.setBackgroundColor(colorTable.colorAt(parameter));
			break;
		case cf:
			style.setForegroundColor(colorTable.colorAt(parameter));
			break;
		
		// Alignment
		case qc:
			style.setAlignment(Alignment.CENTER);
			break;
		case qj:
			style.setAlignment(Alignment.JUSTIFIED);
			break;
		case ql:
			style.setAlignment(Alignment.LEFT);
			break;
		case qr:
			style.setAlignment(Alignment.RIGHT);
			break;
		case qd:
			style.setAlignment(Alignment.DISTRIBUTED);
			break;

		// Indents
		case fi:
			style.setFirstLineIndent(fromTwips(parameter));
			break;
		case li:
			style.setLeftIndent(fromTwips(parameter));
			break;
		case ri:
			style.setRightIndent(fromTwips(parameter));
			break;

		// spacing
		case sb:
			style.setSpacingTop(fromTwips(parameter));
			break;
		case sa:
			style.setSpacingBottom(fromTwips(parameter));
			break;
		case sl:
			style.setLineSpacing(fromTwips(parameter));
			break;
			
		default:
//			System.out.println("DocumentPartContext.processCommand("
//				+ command + ") not handled!");
			break;
		}
	}
	
	private void setUnderlined(UnderlineStyle underline, boolean hasParamter,
		int parameter) {
		if (!hasParamter || parameter == 0)
			style.setUnderlined(UnderlineStyle.NONE);
		else
			style.setUnderlined(underline);
	}

	private static float fromTwips(int value) {
		return (float) value / 20.0f;
	}

	private static float fromHalfPoints(int value) {
		return (float) value / 2.0f;
	}
}
