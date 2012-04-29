package com.uml.contradiction.tests;

import org.junit.Test;

import com.uml.contradiction.exporters.Exporter;
import com.uml.contradiction.exporters.doc.DocExporterPOI;

public class POITest {
	@Test
	public void t(){
		Exporter exporter = new DocExporterPOI();
		try {
			exporter.export(new String[]{"gleb", " awesome", " !!!"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
