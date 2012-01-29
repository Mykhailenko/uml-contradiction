package com.uml.contradiction.gui.controllers;

import javax.swing.JPanel;

import com.uml.contradiction.gui.panels.*;
import com.uml.contradiction.gui.windows.MainWindow;

public class PanelsController {
	public static MainWindow mainWindow; 
	public static StartPanel startPanel;
	public static ContradictionsPanel contradictionsPanel;
	public static DiagramsPanel diagramsPanel;
	public static VerificationResultsPanel resultsPanel;
	
	public static void showPanel(JPanel panel) {
		mainWindow.removeAll();
		mainWindow.setContentPane(panel);
		mainWindow.repaint();
		
	}
}
