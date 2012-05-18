package com.uml.contradiction.gui.components;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import com.uml.contradiction.gui.controllers.PanelsController;

public class ProgressDialog extends JDialog {
	private static final long serialVersionUID = -6073999744772527959L;

	public ProgressDialog(String s) {
		this(PanelsController.mainWindow, s);
	}

	public ProgressDialog(JFrame owner, String s) {
		super(owner, s, true);
		setSize(300, 65);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setVisible(true);
		progressBar.setIndeterminate(true);
		progressBar.setSize(250, 25);
		add(BorderLayout.CENTER, progressBar);
	}
}
