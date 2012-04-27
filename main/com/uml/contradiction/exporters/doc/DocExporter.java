package com.uml.contradiction.exporters.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.uml.contradiction.exporters.Exporter;

public class DocExporter implements Exporter {

	@Override
	public void export(String[] results) throws Exception {
		File file = new File("resources"+File.separator+"sample.doc");
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		HWPFDocument doc = new HWPFDocument(fs);
		Range range = doc.getRange();
		StringBuffer res = new StringBuffer();
		for(String s : results){
			res.append(s);
		}
		CharacterRun run = range.insertAfter(res.toString());
		run.setFontSize(18);
		run.setBold(true);
		run.setItalic(true);
		run.setCapitalized(true);
		OutputStream out = new FileOutputStream(new File("new.doc"));
		doc.write(out);
		out.flush();
		out.close();
	}

}
