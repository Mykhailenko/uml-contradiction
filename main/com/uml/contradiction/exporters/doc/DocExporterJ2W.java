package com.uml.contradiction.exporters.doc;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import word.api.interfaces.IDocument;
import word.utils.TestUtils;
import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.HeadingStyle.Align;

import com.uml.contradiction.engine.model.GeneralResult;
import com.uml.contradiction.exporters.Exporter;
import com.uml.contradiction.gui.Properties;
import com.uml.contradiction.model.MetaData;

public class DocExporterJ2W implements Exporter {
	@Override
	public void export(GeneralResult generalResult) throws Exception {
		IDocument myDoc = new Document2004();
		myDoc.encoding(Encoding.UTF_8); // or ISO8859-1. Default is UTF-8
		myDoc.addEle(Heading1.with("Verification report of \"" +MetaData.getProjectName()+ "\" project").withStyle()
				.align(Align.CENTER).create());

		myDoc.getHeader().addEle(
				Paragraph.withPieces(
						ParagraphPiece.with("Verification report of \""),
						ParagraphPiece.with(MetaData.getProjectName()).withStyle().bold()
								.create(), ParagraphPiece.with("\" project"))
								.create());
		DateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		String currentDate = dateFormat.format(cal.getTime());
		myDoc.getHeader().addEle(
				Paragraph.with(currentDate).withStyle().align(word.w2004.style.ParagraphStyle.Align.RIGHT).create());
		
		String footer1 = "Created by " + Properties.getApplicationName() + " " + Properties.getVersion() + " application";
		myDoc.getFooter().addEle(
                Paragraph.with(footer1).create());
		String footer2 = "Please, reply to " + Properties.getEmail();
		myDoc.getFooter().addEle(
                Paragraph.with(footer2).create());
		myDoc.getBody().addEle(
				Paragraph.withPieces(
						ParagraphPiece.with("Proejct Name: "),
						ParagraphPiece.with(MetaData.getProjectName()).withStyle().bold().create()						
						).withStyle().align(word.w2004.style.ParagraphStyle.Align.RIGHT).create());
		myDoc.getBody().addEle(
				Paragraph.withPieces(
						ParagraphPiece.with("Exporter: "),
						ParagraphPiece.with(MetaData.getExporter()).withStyle().bold().create()						
						).withStyle().align(word.w2004.style.ParagraphStyle.Align.RIGHT).create());
		myDoc.getBody().addEle(
				Paragraph.withPieces(
						ParagraphPiece.with("Author: "),
						ParagraphPiece.with(MetaData.getAuthor()).withStyle().bold().create()						
						).withStyle().align(word.w2004.style.ParagraphStyle.Align.RIGHT).create());
		myDoc.getBody().addEle(
				Paragraph.withPieces(
						ParagraphPiece.with("Company: "),
						ParagraphPiece.with(MetaData.getCompany()).withStyle().bold().create()						
						).withStyle().align(word.w2004.style.ParagraphStyle.Align.RIGHT).create());
		myDoc.getBody().addEle(
				Paragraph.withPieces(
						ParagraphPiece.with("Description: "),
						ParagraphPiece.with(MetaData.getDescription()).withStyle().bold().create()						
						).withStyle().align(word.w2004.style.ParagraphStyle.Align.RIGHT).create());
		myDoc.getBody().addEle(
				Paragraph.withPieces(
						ParagraphPiece.with("Created Date: "),
						ParagraphPiece.with(MetaData.getPmCreateDateTime()).withStyle().bold().create()						
						).withStyle().align(word.w2004.style.ParagraphStyle.Align.RIGHT).create());
		myDoc.getBody().addEle(
				Paragraph.withPieces(
						ParagraphPiece.with("Last Modified Date: "),
						ParagraphPiece.with(MetaData.getPmLastModified()).withStyle().bold().create()						
						).withStyle().align(word.w2004.style.ParagraphStyle.Align.RIGHT).create());
		
		myDoc.getBody().addEle(
				Heading3.with("Criterion 1: MustExistMethodCriterion"));
		myDoc.getBody().addEle(
				Paragraph.with("Description:sadasdfsafsdf"));
		myDoc.getBody().addEle(
				Paragraph.with("Verification result: Everything is correct"));
		
		TestUtils.createLocalDoc(myDoc.getContent());
		File file = new File("./Java2word_allInOne.doc");

		file.renameTo(new File("result"));
	}

}
