package com.uml.contradiction.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.uml.contradiction.gui.sceneries.LoadXMIScenery;

public class LoadXMIListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			LoadXMIScenery.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
