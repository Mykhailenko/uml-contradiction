package com.uml.contradiction.gui.sceneries;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.models.DiagramForChoise;

public class LoadXMIScenery {
	private static final Logger LOGGER = Logger.getRootLogger();
	public static void run() {
		JFileChooser chooser = new JFileChooser("Load XMI");
		XMIFileFilter xmiFileFilter = new XMIFileFilter();
		chooser.setFileFilter(xmiFileFilter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(false);
		int returnValue = chooser.showOpenDialog(PanelsController.mainWindow);
		if(returnValue == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			LOGGER.info("we choosed " + file.getName());
			try {
				XMIConverter.setFile(file);
				List<DiagramForChoise> availableDiagram = XMIConverter.getAvailableDiagram();
				PanelsController.diagramsPanel.createGUIForDiagrams(availableDiagram);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			
		}
	}
}
