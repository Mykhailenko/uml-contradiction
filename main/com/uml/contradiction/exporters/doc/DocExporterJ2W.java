package com.uml.contradiction.exporters.doc;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import word.api.interfaces.IDocument;
import word.utils.TestUtils;
import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading3;
import word.w2004.elements.Paragraph;
import word.w2004.elements.Paragraph.TabAlign;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.Color;
import word.w2004.style.HeadingStyle.Align;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.CriterionSuite;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.exporters.Exporter;
import com.uml.contradiction.gui.Properties;
import com.uml.contradiction.model.MetaData;

public class DocExporterJ2W implements Exporter {
	private IDocument myDoc;

	@Override
	public void export(List<VerificationResult> verificationResults)
			throws Exception {
		System.out.println("inside exporter");
		myDoc = new Document2004();
		myDoc.encoding(Encoding.UTF_8); // or ISO8859-1. Default is UTF-8
		myDoc.addEle(Heading1
				.with("Verification report of \"" + MetaData.getProjectName()
						+ "\" project").withStyle().align(Align.CENTER)
				.create());
		// //////////////////////
		addHeader();
		addProjectInfo("Project Name: ", MetaData.getProjectName());
		addProjectInfo("Exporter: ", MetaData.getExporter());
		addProjectInfo("Author: ", MetaData.getAuthor());
		addProjectInfo("Company: ", MetaData.getCompany());
		addProjectInfo("Description: ", MetaData.getDescription());
		addProjectInfo("Create Date: ", MetaData.getPmCreateDateTime());
		addProjectInfo("Last Modified Date: ", MetaData.getPmLastModified());

		for (int i = 0; i < verificationResults.size(); ++i) {
			VerificationResult vr = verificationResults.get(i);
			String criterionName = CriterionSuite.getNameOfCriterion(vr
					.getCriterion());
			String description = CriterionSuite.getDescrOfCriterion(vr
					.getCriterion());
			System.out.println("cri " + criterionName + " des " + description);
			myDoc.getBody()
					.addEle(Heading3.with("Criterion " + (i + 1) + ": "
							+ criterionName));
			myDoc.getBody().addEle(
					Paragraph.with("Description: " + description));
			if (vr.isGood()) {
				myDoc.getBody().addEle(
						Paragraph.withPieces(
								ParagraphPiece.with("Verification result: "),
								ParagraphPiece.with("everything is correct")
										.withStyle().textColor(Color.GREEN)
										.create()));
			} else {
				myDoc.getBody()
						.addEle(Paragraph.withPieces(
								ParagraphPiece.with("Verification result: "),
								ParagraphPiece
										.with("contradictions were detected in the following diagrams:")
										.withStyle().textColor(Color.RED)
										.create()));
				for (ResultTemplate rt : vr.getResultTemplate()) {
					myDoc.getBody().addEle(
							Paragraph.with(tab(1) + rt.getDiagramsNames()));
					myDoc.getBody().addEle(
							Paragraph.with(tab(2) + rt.getDescription()));
				}
			}
		}
		addFooter();

		// //////////////////////////////////
		TestUtils.createLocalDoc(myDoc.getContent());
		File file = new File("./Java2word_allInOne.doc");
		file.renameTo(new File("result"));
	}

	private static String tab(int n) {
		String tab = "    ";
		if (n <= 0) {
			return tab;
		} else {
			String s = "";
			for (int i = 0; i < n; ++i) {
				s += tab;
			}
			return s;
		}
	}

	private void addFooter() {
		String footer1 = "Created by " + Properties.getApplicationName() + " "
				+ Properties.getVersion() + " application";
		myDoc.getFooter().addEle(Paragraph.with(footer1).create());
		String footer2 = "Please, reply to " + Properties.getEmail();
		myDoc.getFooter().addEle(Paragraph.with(footer2).create());
	}

	private void addHeader() {
		myDoc.getHeader().addEle(
				Paragraph.withPieces(
						ParagraphPiece.with("Verification report of \""),
						ParagraphPiece.with(MetaData.getProjectName())
								.withStyle().bold().create(),
						ParagraphPiece.with("\" project")).create());
		DateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		String currentDate = dateFormat.format(cal.getTime());
		myDoc.getHeader().addEle(
				Paragraph.with(currentDate).withStyle()
						.align(word.w2004.style.ParagraphStyle.Align.RIGHT)
						.create());
	}

	private void addProjectInfo(String lbl, String value) {
		if (value != null && value.length() != 0) {
			myDoc.getBody().addEle(
					Paragraph
							.withPieces(
									ParagraphPiece.with(lbl),
									ParagraphPiece.with(value).withStyle()
											.bold().create()).withStyle()
							.align(word.w2004.style.ParagraphStyle.Align.RIGHT)
							.create());
		}
	}

}
