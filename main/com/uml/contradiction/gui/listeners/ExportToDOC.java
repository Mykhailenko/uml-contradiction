package com.uml.contradiction.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.uml.contradiction.engine.model.GeneralResult;
import com.uml.contradiction.exporters.ResultSaver;
import com.uml.contradiction.exporters.doc.DocExporterJ2W;
import com.uml.contradiction.exporters.doc.DocExporterPOI;
import com.uml.contradiction.gui.controllers.PanelsController;

public class ExportToDOC implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String filename = File.separator + "result.doc";
		String [] results = new String[]{"gleb", "awesome"};
		ResultSaver.save(new GeneralResult(), filename, new DocExporterJ2W());
		
	}

}
