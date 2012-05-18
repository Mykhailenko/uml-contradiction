package com.uml.contradiction.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.uml.contradiction.gui.Client;

public class ResetListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		doit();
	}

	public void doit() {
		JOptionPane pane = new JOptionPane(null, JOptionPane.OK_CANCEL_OPTION);
		Object[] options = new String[] { "Yes", "No" };
		pane.setOptions(options);
		JDialog dialog = pane.createDialog(new JFrame(), "Do you want reset?");
		dialog.setVisible(true);
		Object obj = pane.getValue();
		if (obj.equals(options[0])) {
			// //do something painful
			Client.getClient().started();
		}
	}
}
