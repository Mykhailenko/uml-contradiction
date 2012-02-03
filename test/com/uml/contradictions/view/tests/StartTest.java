package com.uml.contradictions.view.tests;

import java.io.IOException;

import org.junit.Test;

import com.uml.contradiction.gui.windows.MainWindow;

public class StartTest {
	
	@Test
	public void createStartWindow() throws IOException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.show();
		System.in.read();
	}
}
