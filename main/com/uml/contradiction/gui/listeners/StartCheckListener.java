package com.uml.contradiction.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.uml.contradiction.gui.Client;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.panels.ContradictionsPanel;
import com.uml.contradiction.gui.sceneries.StartCheckScenery;

public class StartCheckListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("verirr");
	//	StartCheckScenery.run();
		ContradictionsPanel panel = PanelsController.contradictionsPanel;
		List<DefaultMutableTreeNode> nodes = panel.getSelectedNodes();
		if(nodes == null || nodes.size() == 0) {
			
		}
		else {
			StartCheckScenery.run(nodes);
			Client.getClient().verified();
		}
	}

}
