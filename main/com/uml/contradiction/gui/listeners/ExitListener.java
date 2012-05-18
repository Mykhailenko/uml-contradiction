package com.uml.contradiction.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExitListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		doit();
	}

	public void doit() {
		JOptionPane pane = new JOptionPane(null, JOptionPane.CLOSED_OPTION);
		Object[] options = new String[] { "Yes", "No" };
		pane.setOptions(options);
		JDialog dialog = pane.createDialog(new JFrame(), "Do you want exit?");
		dialog.setVisible(true);
		Object obj = pane.getValue();
		if (obj.equals(options[0])) {
			System.exit(0);
		}
	}
}
