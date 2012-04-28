package com.uml.contradiction.gui.menu;

import java.awt.MenuBar;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.uml.contradiction.gui.listeners.AboutListener;
import com.uml.contradiction.gui.listeners.ExitListener;
import com.uml.contradiction.gui.listeners.ExportToDOC;
import com.uml.contradiction.gui.listeners.LoadXMIListener;
import com.uml.contradiction.gui.listeners.ResetListener;

public class MyMenuBar extends JMenuBar {
	
	public MyMenuBar() {
		super();
		JMenu fileMenu = new JMenu("File");

		JMenuItem loadXMI = new JMenuItem("Load XMI");
		loadXMI.addActionListener(new LoadXMIListener());
		fileMenu.add(loadXMI);
		
		JMenu export = new JMenu("Export");
		JMenuItem exportToTXT = new JMenuItem("to TXT");
		exportToTXT.setEnabled(false);
		export.add(exportToTXT);
		
		JMenuItem exportToDOC = new JMenuItem("to DOC");
		exportToDOC.addActionListener(new ExportToDOC());
		export.add(exportToDOC);
		
		fileMenu.add(export);
		
		fileMenu.addSeparator();
		
		JMenuItem reset = new JMenuItem("Reset");
		reset.addActionListener(new ResetListener());
		fileMenu.add(reset);
		
		fileMenu.addSeparator();
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());
		fileMenu.add(exit);
		
		this.add(fileMenu);
		
		JMenu view = new JMenu("View");
		view.setEnabled(false);
		
		this.add(view);
		
		JMenu help = new JMenu("Help");
		
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new AboutListener());
		help.add(about);
		
		JMenuItem helpContent = new JMenuItem("Help Content");
		helpContent.setEnabled(false);
		help.add(helpContent);
		
		this.add(help);
	}
	
}
