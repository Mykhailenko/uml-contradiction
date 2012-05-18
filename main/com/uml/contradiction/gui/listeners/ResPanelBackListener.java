package com.uml.contradiction.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.uml.contradiction.gui.Client;
import com.uml.contradiction.gui.controllers.PanelsController;

public class ResPanelBackListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		PanelsController.showPanel(PanelsController.contradictionsPanel);
		Client.getClient().loadedOneSelected();
	}

}
