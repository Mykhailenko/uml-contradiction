package com.uml.contradiction.gui;

import javax.swing.JFrame;

import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.panels.ContradictionsPanel;
import com.uml.contradiction.gui.panels.VerificationResultsPanel;
import com.uml.contradiction.gui.windows.MainWindow;

public class Client {
	private static boolean xmiLoaded = false;
	private static MainWindow mainWindow;
	public static void main(String [] args){
		mainWindow = new MainWindow();
		ContradictionsPanel p = new ContradictionsPanel();
		VerificationResultsPanel pp = new VerificationResultsPanel();
		PanelsController.contradictionsPanel = p;
		PanelsController.resultsPanel = pp;
		PanelsController.mainWindow = mainWindow;
		PanelsController.showPanel(p);
		mainWindow.setVisible(true);
	}
	public static MainWindow getMainWindow() {
		return mainWindow;
	}
	public static boolean isXmiLoaded() {
		return xmiLoaded;
	}
	public static void setXmiLoaded(boolean xmiLoaded) {
		Client.xmiLoaded = xmiLoaded;
	}
	
}
