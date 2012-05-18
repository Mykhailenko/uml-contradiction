package com.uml.contradiction.gui.windows;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.uml.contradiction.gui.GUIState;
import com.uml.contradiction.gui.Properties;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.listeners.ExitListener;
import com.uml.contradiction.gui.menu.MyMenuBar;

public class MainWindow extends JFrame implements GUIState {
	private static final long serialVersionUID = 2504948215343067408L;
	private MyMenuBar menuBar;

	public MainWindow() {
		this.setTitle(Properties.getApplicationName() + " "
				+ Properties.getVersion());
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		// this.setLocation(100, 300);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ExitListener exitListener = new ExitListener();
				exitListener.doit();
			}

		});

		menuBar = new MyMenuBar();
		this.setJMenuBar(menuBar);
	}

	public MyMenuBar getMyMenuBar() {
		return menuBar;
	}

	@Override
	public void started() {
		menuBar.started();
		PanelsController.showPanel(PanelsController.contradictionsPanel);
	}

	@Override
	public void loadedNoOneSelected() {
		menuBar.loadedNoOneSelected();
	}

	@Override
	public void loadedOneSelected() {
		menuBar.loadedOneSelected();
	}

	@Override
	public void verified() {
		menuBar.verified();
	}
}
