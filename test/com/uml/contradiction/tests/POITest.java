package com.uml.contradiction.tests;

import org.junit.Test;

import com.uml.contradiction.exporters.Exporter;
import com.uml.contradiction.exporters.doc.DocExporter;

public class POITest {
	@Test
	public void t(){
		Exporter exporter = new DocExporter();
		try {
			exporter.export(new String[]{"gleb", " awesome", " !!!"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
