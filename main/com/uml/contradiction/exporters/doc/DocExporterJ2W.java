package com.uml.contradiction.exporters.doc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading3;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.Color;
import word.w2004.style.HeadingStyle.Align;
import word.w2004.style.ParagraphStyle.Indent;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.CriterionSuite;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.exporters.Exporter;
import com.uml.contradiction.gui.Properties;
import com.uml.contradiction.model.MetaData;

public class DocExporterJ2W implements Exporter {
	private IDocument myDoc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.uml.contradiction.exporters.Exporter#export(java.util.List)
	 */
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

		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
		String pmCreatedDateTime = null;
		try {
			calendar.setTimeInMillis(Long.parseLong(MetaData
					.getPmCreateDateTime()));
			pmCreatedDateTime = dateFormat.format(calendar.getTime());
		} catch (Exception e) {
		}
		addProjectInfo("Created Date: ", pmCreatedDateTime);

		String pmLastModified = null;
		try {
			calendar.setTimeInMillis(Long.parseLong(MetaData
					.getPmLastModified()));
			pmLastModified = dateFormat.format(calendar.getTime());
		} catch (Exception e) {
		}
		addProjectInfo("Last Modified Date: ", pmLastModified);

		for (int i = 0; i < verificationResults.size(); ++i) {
			VerificationResult vr = verificationResults.get(i);
			String criterionName = CriterionSuite.getNameOfCriterion(vr
					.getCriterion());
			String description = CriterionSuite.getDescripritionOfCriterion(vr
					.getCriterion());
			myDoc.getBody()
					.addEle(Heading3.with("Criterion " + (i + 1) + ": "
							+ criterionName));
			myDoc.getBody().addEle(
					Paragraph.with("Description: " + description + "."));
			if (vr.isGood()) {
				myDoc.getBody().addEle(
						Paragraph.withPieces(
								ParagraphPiece.with("Verification result: "),
								ParagraphPiece.with("everything is correct.")
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
				Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
				for (ResultTemplate rt : vr.getResultTemplate()) {
					List<String> list = map.get(rt.getDiagramsNames());
					if (list == null) {
						list = new LinkedList<String>();
						map.put(rt.getDiagramsNames(), list);
					}
					list.add(rt.getDescription());
				}
				Iterator<Map.Entry<String, List<String>>> it = map.entrySet()
						.iterator();
				while (it.hasNext()) {
					Map.Entry<String, List<String>> entry = it.next();
					myDoc.getBody().addEle(
							Paragraph
									.withPieces(
											ParagraphPiece.with(entry.getKey())
													.withStyle().italic()
													.create()).withStyle()
									.indent(Indent.ONE).create());
					for (String e : entry.getValue()) {
						String[] arre = escapeXML(e).split(
								ResultTemplate.ELEMENT_MARKER);
						ParagraphPiece[] pieces = new ParagraphPiece[arre.length + 1];
						boolean simple = true;
						for (int j = 0; j < arre.length; ++j, simple = !simple) {
							if (simple) {
								pieces[j] = ParagraphPiece.with(arre[j]);
							} else {
								pieces[j] = ParagraphPiece.with(arre[j])
										.withStyle().bold().create();
							}
						}
						pieces[arre.length] = ParagraphPiece.with(";");
						myDoc.getBody().addEle(
								Paragraph.withPieces(pieces).withStyle()
										.indent(Indent.TWO).create());
					}
				}
			}
		}
		addFooter();
		// //////////////////////////////////
		createLocalDoc(myDoc.getContent(), "result");
	}

	private void createLocalDoc(String content, String fileName) {
		File fileObj = new File(fileName);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileObj);
			writer.println(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			writer.close();
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
									ParagraphPiece.with(escapeXML(value))
											.withStyle().bold().create())
							.withStyle()
							.align(word.w2004.style.ParagraphStyle.Align.RIGHT)
							.create());
		}
	}

	public static String escapeXML(String xml) {
		xml = xml.replaceAll("&", "&amp;");
		xml = xml.replaceAll("<", "&lt;");
		xml = xml.replaceAll(">", "&gt;");
		xml = xml.replaceAll(" ", "&apos;");
		xml = xml.replaceAll("\"", "&quot;");
		return xml;
	}

}
