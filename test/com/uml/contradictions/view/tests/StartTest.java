package com.uml.contradictions.view.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.gui.panels.DiagramsPanel;
import com.uml.contradiction.gui.windows.MainWindow;

public class StartTest {
	
	@Test
	public void createStartWindow() throws IOException {
		MainWindow mainWindow = new MainWindow();
		
		DiagramsPanel p = new DiagramsPanel();
		PanelsController.diagramsPanel = p;
		
		DiagramForChoise dfc1 = new DiagramForChoise();
		dfc1.setName("Class_1");
		dfc1.setType(DiagramType.CLASS);
		
		DiagramForChoise dfc2 = new DiagramForChoise();
		dfc2.setName("Object_1");
		dfc2.setType(DiagramType.OBJECT);
		
		List<DiagramForChoise> l =new ArrayList<DiagramForChoise>();
		l.add(dfc1);
		l.add(dfc2);
		
		mainWindow.setContentPane(p);
		p.setFromDiagrams(l);
		
		
		mainWindow.show();
		System.in.read();
	}
}
