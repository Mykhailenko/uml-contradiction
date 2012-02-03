package com.uml.contradictions.view.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.gui.panels.DiagramsPanel;
import com.uml.contradiction.gui.windows.MainWindow;

public class StartTest {
	
	@Test
	public void createStartWindow() throws IOException {
		MainWindow mainWindow = new MainWindow();
		
		DiagramsPanel p = new DiagramsPanel();
		mainWindow.setContentPane(new DiagramsPanel());
		
		DiagramForChoise dfc1 = new DiagramForChoise();
		dfc1.setName("1");
		List<DiagramForChoise> l =new ArrayList<DiagramForChoise>();
		l.add(dfc1);
		
		p.setDiagrams(l);
		
		mainWindow.show();
		System.in.read();
	}
}
