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
		loadXMI.addActionListener(new LoadXMIListener());
		fileMenu.add(loadXMI);
		
		JMenuItem save = new JMenuItem("Save");
		fileMenu.add(save);
		
		fileMenu.addSeparator();
		
		JMenuItem exit = new JMenuItem("Exit");
		fileMenu.add(exit);
		
		this.add(fileMenu);
		
		JMenu view = new JMenu("View");
		
		this.add(view);
		
		JMenu help = new JMenu("Help");
		
		JMenuItem about = new JMenuItem("About");
		help.add(about);
		
		JMenuItem helpContent = new JMenuItem("Help Content");
		help.add(helpContent);
		
		this.add(help);
	}
	
}
