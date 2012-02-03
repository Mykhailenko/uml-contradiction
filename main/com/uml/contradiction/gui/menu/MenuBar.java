package com.uml.contradiction.gui.menu;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.uml.contradiction.gui.listeners.LoadXMIListener;

public class MenuBar extends JMenuBar {
	
	public MenuBar() {
		super();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadXMI = new JMenuItem("Load XMI");
		fileMenu.add(loadXMI);
		loadXMI.addActionListener(new LoadXMIListener());
		this.add(fileMenu);
		
	}
	
}
