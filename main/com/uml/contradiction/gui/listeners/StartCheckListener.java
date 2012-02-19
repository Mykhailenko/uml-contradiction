package com.uml.contradiction.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.panels.ContradictionsPanel;
import com.uml.contradiction.gui.sceneries.StartCheckScenery;

public class StartCheckListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	//	StartCheckScenery.run();
		ContradictionsPanel panel = PanelsController.contradictionsPanel;
		panel.getSelectedCriterions();
	}

}
