package com.uml.contradiction.exporters;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.gui.controllers.PanelsController;

public class ResultSaver {
	public static boolean save(List<VerificationResult> verificationResults,
			String filename, Exporter exporter) {
		JFileChooser chooser = new JFileChooser(new File(filename));
		chooser.setSelectedFile(new File(filename));
		int returnValue = chooser.showSaveDialog(PanelsController.mainWindow);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			return ResultSaver.savep(verificationResults, file.getPath(),
					exporter);
		} else {
			return false;
		}

	}

	public static boolean savep(List<VerificationResult> verificationResults,
			String filename, Exporter exporter) {
		try {
			exporter.export(verificationResults);
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file = new File("result");
		File e = new File(filename);
		if (e.exists() && e.isFile()) {
			e.delete();
		}
		return file.renameTo(new File(filename));
	}

}
