package com.uml.contradiction.exporters.doc;

import java.io.File;

import word.api.interfaces.IDocument;
import word.utils.TestUtils;
import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.HeadingStyle.Align;

import com.uml.contradiction.engine.model.GeneralResult;
import com.uml.contradiction.exporters.Exporter;

public class DocExporterJ2W implements Exporter {
	@Override
	public void export(GeneralResult generalResult) throws Exception {
		IDocument myDoc = new Document2004();
		myDoc.encoding(Encoding.UTF_8); // or ISO8859-1. Default is UTF-8
		myDoc.addEle(Heading1.with("Verification report").withStyle()
				.align(Align.CENTER).create());

		myDoc.getHeader().addEle(
				Paragraph.withPieces(
						ParagraphPiece.with("Report on the verification of the project "),
						ParagraphPiece.with("PROJECT_NAME").withStyle().bold()
								.create())
						.create());

//		myDoc.getFooter().addEle(
//				Paragraph.with("created by uml-contradiction 1.0 tool").create());
//		myDoc.getFooter().showPageNumber(true);
		myDoc.getFooter().addEle(
                Paragraph.with("I am in the Footer of all pages").create());

		TestUtils.createLocalDoc(myDoc.getContent());
		File file = new File("./Java2word_allInOne.doc");

		file.renameTo(new File("result"));
	}

}
