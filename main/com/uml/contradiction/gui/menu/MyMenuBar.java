package com.uml.contradiction.gui.menu;

import java.awt.MenuBar;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.uml.contradiction.gui.GUIState;
import com.uml.contradiction.gui.listeners.AboutListener;
import com.uml.contradiction.gui.listeners.ExitListener;
import com.uml.contradiction.gui.listeners.ExportToDOC;
import com.uml.contradiction.gui.listeners.ExportToTXT;
import com.uml.contradiction.gui.listeners.LoadXMIListener;
import com.uml.contradiction.gui.listeners.ResetListener;

public class MyMenuBar extends JMenuBar implements GUIState{
	JMenu fileMenu;
	JMenuItem loadXMI;
	JMenu export;
	JMenuItem exportToTXT;
	JMenuItem exportToDOC;
	JMenuItem reset;
	JMenuItem exit;
	JMenu view;
	JMenu help;
	JMenuItem about;
	JMenuItem helpContent;
	public MyMenuBar() {
		super();
		fileMenu = new JMenu("File");

		loadXMI = new JMenuItem("Load XMI");
		loadXMI.addActionListener(new LoadXMIListener());
		fileMenu.add(loadXMI);
		
		export = new JMenu("Export");
		exportToTXT = new JMenuItem("to TXT");
		exportToTXT.addActionListener(new ExportToTXT());
		export.add(exportToTXT);
		
		exportToDOC = new JMenuItem("to DOC");
		exportToDOC.addActionListener(new ExportToDOC());
		export.add(exportToDOC);
		
		fileMenu.add(export);
		
		fileMenu.addSeparator();
		
		reset = new JMenuItem("Reset");
		reset.addActionListener(new ResetListener());
		fileMenu.add(reset);
		
		fileMenu.addSeparator();
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());
		fileMenu.add(exit);
		
		this.add(fileMenu);
		
		view = new JMenu("View");
		view.setEnabled(false);
		
		this.add(view);
		
		help = new JMenu("Help");
		
		about = new JMenuItem("About");
		about.addActionListener(new AboutListener());
		help.add(about);
		
		helpContent = new JMenuItem("Help Content");
		helpContent.setEnabled(false);
		help.add(helpContent);
		
		this.add(help);
	}

	@Override
	public void started() {
		loadXMI.setEnabled(true);
		export.setEnabled(false);
		reset.setEnabled(false);
	}

	@Override
	public void loadedNoOneSelected() {
		loadXMI.setEnabled(false);
		export.setEnabled(false);
		reset.setEnabled(true);
	}

	@Override
	public void loadedOneSelected() {
		loadXMI.setEnabled(false);
		export.setEnabled(false);
		reset.setEnabled(true);
	}

	@Override
	public void verified() {
		loadXMI.setEnabled(false);
		export.setEnabled(true);
		reset.setEnabled(true);
	}
	
}
