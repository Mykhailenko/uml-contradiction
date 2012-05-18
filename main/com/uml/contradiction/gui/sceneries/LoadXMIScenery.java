package com.uml.contradiction.gui.sceneries;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import org.apache.log4j.Logger;

import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.gui.Client;
import com.uml.contradiction.gui.components.CheckTreeManager;
import com.uml.contradiction.gui.components.ProgressDialog;
import com.uml.contradiction.gui.components.XMIFileFilter;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.models.DiagramForChoise;

public class LoadXMIScenery {
	private static final Logger LOGGER = Logger.getRootLogger();

	public static void run() throws IOException {
		JFileChooser chooser = new JFileChooser("Load XMI");
		XMIFileFilter xmiFileFilter = new XMIFileFilter();
		chooser.setFileFilter(xmiFileFilter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(false);
		int returnValue = chooser.showOpenDialog(PanelsController.mainWindow);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			final File file = chooser.getSelectedFile();
			LOGGER.info("we choosed " + file.getName());
			try {
				final JDialog dialog = new ProgressDialog("loading xmi...");
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							XMIConverter.setFileAndParse(file);
							List<DiagramForChoise> availableDiagram = XMIConverter
									.getAvailableDiagram();
							PanelsController.diagramsPanel
									.setFromDiagrams(availableDiagram);
						} catch (Exception e) {
							e.printStackTrace();
						}
						Client.getClient().loadedNoOneSelected();
						CheckTreeManager.checkState();
						dialog.setVisible(false);
					}
				}).start();
				dialog.setVisible(true);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

}
