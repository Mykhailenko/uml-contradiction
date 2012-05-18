package com.uml.contradiction.gui.listeners;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.uml.contradiction.gui.controllers.PanelsController;

public class ContrTreeListener implements TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) (e.getPath()
				.getLastPathComponent());
		PanelsController.contradictionsPanel.showDescription(node
				.getUserObject());
	}

}
