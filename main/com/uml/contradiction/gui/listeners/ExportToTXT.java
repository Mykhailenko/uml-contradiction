package com.uml.contradiction.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.uml.contradiction.engine.model.GeneralResult;
import com.uml.contradiction.exporters.ResultSaver;
import com.uml.contradiction.exporters.doc.DocExporter;
import com.uml.contradiction.exporters.txt.TxtExporter;

public class ExportToTXT implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String filename = File.separator + "result.txt";
		String [] results = new String[]{"gleb", "awesome"};
		ResultSaver.save(new GeneralResult(), filename, new TxtExporter());
	}

}
