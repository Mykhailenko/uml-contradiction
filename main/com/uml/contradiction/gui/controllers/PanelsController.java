package com.uml.contradiction.gui.controllers;

import javax.swing.JPanel;

import com.uml.contradiction.gui.panels.ContradictionsPanel;
import com.uml.contradiction.gui.panels.DiagramsPanel;
import com.uml.contradiction.gui.panels.StartPanel;
import com.uml.contradiction.gui.panels.VerificationResultsPanel;
import com.uml.contradiction.gui.windows.MainWindow;

public class PanelsController {
	public static MainWindow mainWindow = new MainWindow();
	public static StartPanel startPanel = new StartPanel();
	public static ContradictionsPanel contradictionsPanel = new ContradictionsPanel();
	public static DiagramsPanel diagramsPanel = new DiagramsPanel();
	public static VerificationResultsPanel resultsPanel = new VerificationResultsPanel();

	public static void showPanel(JPanel panel) {
		mainWindow.remove(mainWindow.getContentPane());
		mainWindow.setContentPane(panel);
		mainWindow.repaint();
		mainWindow.getContentPane().repaint();
		((JPanel) mainWindow.getContentPane()).updateUI();
		return;
	}
}
