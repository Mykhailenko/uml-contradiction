package com.uml.contradiction.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.uml.contradiction.gui.GUIState;
import com.uml.contradiction.gui.HotKeyBinder;
import com.uml.contradiction.gui.components.ProjectInfoDialog;
import com.uml.contradiction.gui.listeners.AboutListener;
import com.uml.contradiction.gui.listeners.ExitListener;
import com.uml.contradiction.gui.listeners.ExportToDOC;
import com.uml.contradiction.gui.listeners.ExportToTXT;
import com.uml.contradiction.gui.listeners.LoadXMIListener;
import com.uml.contradiction.gui.listeners.ResetListener;

public class MyMenuBar extends JMenuBar implements GUIState {
	private static final long serialVersionUID = -8023112033523408083L;
	private JMenu fileMenu;
	private JMenuItem loadXMI;
	private JMenu export;
	private JMenuItem exportToTXT;
	private JMenuItem exportToDOC;
	private JMenuItem reset;
	private JMenuItem exit;
//	private JMenu view;
	private JMenu help;
	private JMenuItem about;
	private JMenuItem helpContent;
	private JMenuItem project;
	private JMenuItem settings;

	public MyMenuBar() {
		super();
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		loadXMI = new JMenuItem("Load XMI (Ctrl + O)");
		loadXMI.addActionListener(new LoadXMIListener());
		loadXMI.setMnemonic('o');
		fileMenu.add(loadXMI);

		export = new JMenu("Export");
		exportToTXT = new JMenuItem("to TXT");
		exportToTXT.addActionListener(new ExportToTXT());
		export.add(exportToTXT);

		exportToDOC = new JMenuItem("to DOC");
		exportToDOC.addActionListener(new ExportToDOC());
		export.add(exportToDOC);

		fileMenu.add(export);

		project = new JMenuItem("Project");
		project.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				ProjectInfoDialog dialog = new ProjectInfoDialog();
				dialog.setVisible(true);
			}
		});
		project.setMnemonic(KeyEvent.VK_P);
		fileMenu.add(project);

		settings = new JMenuItem("Settings");
		settings.setEnabled(false);
		fileMenu.add(settings);

		fileMenu.addSeparator();

		reset = new JMenuItem("Reset");
		reset.addActionListener(new ResetListener());
		reset.setMnemonic(KeyEvent.VK_R);
		fileMenu.add(reset);

		fileMenu.addSeparator();

		exit = new JMenuItem("Exit (Ctrl + X)");
		exit.addActionListener(new ExitListener());
		exit.setMnemonic(KeyEvent.VK_X);
		fileMenu.add(exit);

		this.add(fileMenu);

		help = new JMenu("Help");

		about = new JMenuItem("About");
		about.addActionListener(new AboutListener());
		help.add(about);

		helpContent = new JMenuItem("Help Content");
		helpContent.setEnabled(false);
		help.add(helpContent);

		this.add(help);

		HotKeyBinder.addComponent(this);
	}

	public JMenuItem getLoadXMI() {
		return loadXMI;
	}

	public JMenuItem getExportToTXT() {
		return exportToTXT;
	}

	public JMenuItem getExportToDOC() {
		return exportToDOC;
	}

	public JMenuItem getReset() {
		return reset;
	}

	public JMenuItem getExit() {
		return exit;
	}

	public JMenuItem getAbout() {
		return about;
	}

	public JMenuItem getHelpContent() {
		return helpContent;
	}

	public JMenuItem getProject() {
		return project;
	}

	public JMenuItem getSettings() {
		return settings;
	}

	@Override
	public void started() {
		loadXMI.setEnabled(true);
		export.setEnabled(false);
		reset.setEnabled(false);
		project.setEnabled(false);
	}

	@Override
	public void loadedNoOneSelected() {
		loadXMI.setEnabled(false);
		export.setEnabled(false);
		reset.setEnabled(true);
		project.setEnabled(true);
	}

	@Override
	public void loadedOneSelected() {
		loadXMI.setEnabled(false);
		export.setEnabled(false);
		reset.setEnabled(true);
		project.setEnabled(true);
	}

	@Override
	public void verified() {
		loadXMI.setEnabled(false);
		export.setEnabled(true);
		reset.setEnabled(true);
		project.setEnabled(true);
	}

}
