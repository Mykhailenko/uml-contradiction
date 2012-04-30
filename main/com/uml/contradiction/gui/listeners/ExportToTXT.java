package com.uml.contradiction.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.uml.contradiction.exporters.ResultSaver;
import com.uml.contradiction.exporters.txt.TxtExporter;
import com.uml.contradiction.gui.Client;

public class ExportToTXT implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String filename = File.separator + "result.txt";
		ResultSaver.save(Client.getLastResults(), filename, new TxtExporter());
	}

}
