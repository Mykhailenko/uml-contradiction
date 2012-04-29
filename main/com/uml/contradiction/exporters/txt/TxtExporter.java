package com.uml.contradiction.exporters.txt;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.uml.contradiction.exporters.Exporter;

public class TxtExporter implements Exporter{

	@Override
	public void export(String[] verificationResults) throws Exception {
		String filename = "result";
		File file = new File(filename);
		if(!file.exists()){
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file);
		PrintWriter out = new PrintWriter(fileWriter);
		for(String s : verificationResults){
			out.println(s);
		}
		out.flush();
		out.close();
	}

}
