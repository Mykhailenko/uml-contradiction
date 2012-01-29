package com.uml.contradiction.gui.windows;

import javax.swing.JFrame;

import com.uml.contradiction.gui.menu.MenuBar;

public class MainWindow extends JFrame {
	public MainWindow() {
		this.setTitle("UML contradictions");
		this.setSize(300, 350);
		this.setLocation(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new MenuBar());
	
	}
}
