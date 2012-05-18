package com.uml.contradiction.gui.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.uml.contradiction.gui.Client;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.panels.ContradictionsPanel;

public class CheckTreeManager extends MouseAdapter implements
		TreeSelectionListener {
	private CheckTreeSelectionModel selectionModel;
	private JTree tree = new JTree();
	int hotspot = new JCheckBox().getPreferredSize().width;

	public CheckTreeManager(JTree tree) {
		this.tree = tree;
		selectionModel = new CheckTreeSelectionModel(tree.getModel());
		tree.setCellRenderer(new CheckTreeCellRenderer(tree.getCellRenderer(),
				selectionModel));
		tree.addMouseListener(this);
		selectionModel.addTreeSelectionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		TreePath path = tree.getPathForLocation(me.getX(), me.getY());
		if (path == null) {
			return;
		}
		if (me.getX() > tree.getPathBounds(path).x + hotspot) {
			return;
		}

		boolean selected = selectionModel.isPathSelected(path, true);
		selectionModel.removeTreeSelectionListener(this);

		try {
			if (selected) {
				selectionModel.removeSelectionPath(path);
			} else {
				selectionModel.addSelectionPath(path);
			}
		} finally {
			selectionModel.addTreeSelectionListener(this);
			tree.treeDidChange();
			checkState();
			PanelsController.contradictionsPanel.updateState();
		}
	}

	public static void checkState() {
		if (Client.getClient().isLoadedNoOne()
				|| Client.getClient().isLoadedOne()) {
			ContradictionsPanel panel = PanelsController.contradictionsPanel;
			List<DefaultMutableTreeNode> nodes = panel.getSelectedNodes();
			if (nodes == null || nodes.size() == 0) {
				Client.getClient().loadedNoOneSelected();
			} else {
				Client.getClient().loadedOneSelected();
			}
		}
	}

	public CheckTreeSelectionModel getSelectionModel() {
		return selectionModel;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		tree.treeDidChange();
	}
}
