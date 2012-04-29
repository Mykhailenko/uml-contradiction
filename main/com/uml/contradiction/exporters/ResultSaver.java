package com.uml.contradiction.exporters;

import java.io.File;

import javax.swing.JFileChooser;

import com.uml.contradiction.engine.model.GeneralResult;
import com.uml.contradiction.exporters.doc.DocExporter;
import com.uml.contradiction.gui.controllers.PanelsController;

public class ResultSaver {
	public static boolean save(GeneralResult generalResult, String filename, Exporter exporter){
		JFileChooser chooser = new JFileChooser(new File(filename));
		chooser.setSelectedFile(new File(filename));
		int returnValue = chooser.showSaveDialog(PanelsController.mainWindow);
		if(returnValue == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			String [] results = new String[]{"gleb", "awesome"};
			return ResultSaver.savep(generalResult, file.getPath(), exporter);
		}else{
			return false;
		}
		
	}
	private static boolean savep(GeneralResult generalResult, String filename, Exporter exporter){
		try {
			exporter.export(generalResult);
		} catch (Exception e) {
		}
		File file = new File("result");
		boolean success = file.renameTo(new File(filename));
		return success;
	}
	
}