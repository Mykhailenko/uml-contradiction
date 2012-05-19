package com.uml.contradiction.exporters.txt;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.CriterionSuite;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.exporters.Exporter;
import com.uml.contradiction.gui.Properties;
import com.uml.contradiction.model.MetaData;

public class TxtExporter implements Exporter {
	private PrintWriter out;

	@Override
	public void export(List<VerificationResult> verificationResults)
			throws Exception {
		String filename = "result";
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file);
		out = new PrintWriter(fileWriter);
		// //////////////////////////////////

		out.println(tab(2) + "Verification report of \""
				+ MetaData.getProjectName() + "\" project");
		out.println();
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

		out.println();
		for (int i = 0; i < verificationResults.size(); ++i) {
			VerificationResult vr = verificationResults.get(i);
			String criterionName = CriterionSuite.getNameOfCriterion(vr
					.getCriterion());
			String description = CriterionSuite.getDescripritionOfCriterion(vr
					.getCriterion());
			out.println(tab(1) + "Criterion " + (i + 1) + ": " + criterionName);
			out.println("Description: " + description + ".");
			if (vr.isGood()) {
				out.println("Verification result: everything is correct.");
			} else {
				out.println("Verification result: contradictions were detected in the following diagrams:");
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
					out.println(tab(1) + "- " + entry.getKey());
					for (String e : entry.getValue()) {
						e = e.replaceAll(ResultTemplate.ELEMENT_MARKER, "");
						out.println(tab(2) + e + ";");
					}
				}
			}
		}
		addFooter();

		// //////////////////////////////////
		fileWriter.close();
		out.flush();
		out.close();
		out = null;
	}

	private void addFooter() {
		int indent = 5;
		out.println();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		String currentDate = dateFormat.format(cal.getTime());
		out.println(tab(indent) + currentDate);
		String footer1 = "Created by " + Properties.getApplicationName() + " "
				+ Properties.getVersion() + " application";
		out.println(tab(indent) + footer1);
		String footer2 = "Please, reply to " + Properties.getEmail();
		out.println(tab(indent) + footer2);
	}

	private void addProjectInfo(String lbl, String value) {
		if (value != null && value.length() != 0) {
			out.println(tab(4) + lbl + tab(2) + value);
		}
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
}
