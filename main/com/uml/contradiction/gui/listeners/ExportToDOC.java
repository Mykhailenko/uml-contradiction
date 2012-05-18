package com.uml.contradiction.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.uml.contradiction.exporters.ResultSaver;
import com.uml.contradiction.exporters.doc.DocExporterJ2W;
import com.uml.contradiction.gui.Client;

public class ExportToDOC implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String filename = File.separator + "result.doc";
		ResultSaver.save(Client.getLastResults(), filename,
				new DocExporterJ2W());

	}

}
