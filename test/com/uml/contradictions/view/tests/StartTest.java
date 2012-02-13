package com.uml.contradictions.view.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.gui.panels.ContradictionsPanel;
import com.uml.contradiction.gui.panels.DiagramsPanel;
import com.uml.contradiction.gui.windows.MainWindow;

public class StartTest {
	
	@Test
	public void createStartWindow() throws IOException {
		MainWindow mainWindow = new MainWindow();
	
		ContradictionsPanel p = new ContradictionsPanel();
		PanelsController.contradictionsPanel = p;
		mainWindow.setContentPane(p);
		
		mainWindow.show();
		System.in.read();
	}
}
