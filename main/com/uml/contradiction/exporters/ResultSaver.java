package com.uml.contradiction.exporters;

import java.io.File;

public class ResultSaver {
	static public boolean save(String [] verRes, String filename, Exporter exporter){
		try {
			exporter.export(verRes);
		} catch (Exception e) {
		}
		File file = new File("result");
		boolean success = file.renameTo(new File(filename));
		return success;
	}
}
