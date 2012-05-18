package com.uml.contradiction.gui.sceneries;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.uml.contradiction.engine.RunCriterions;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.gui.Client;
import com.uml.contradiction.gui.components.ProgressDialog;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.models.DisplayedCriterion;
import com.uml.contradiction.gui.panels.VerificationResultsPanel;

public class StartCheckScenery {

	public static void run(final List<DefaultMutableTreeNode> nodes) {
		final JDialog dialog = new ProgressDialog("verifying...");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					VerificationResultsPanel resPanel = PanelsController.resultsPanel;
					DefaultMutableTreeNode newRoot = getSelectedTree(nodes);
					List<Criterion> selectedCriterions = getSelectedCriterions(newRoot);
					Map<Criterion, VerificationResult> results = new HashMap<Criterion, VerificationResult>();
					List<VerificationResult> ver = StartCheckScenery
							.verifyCriterions(selectedCriterions);
					for (VerificationResult vr : ver) {
						results.put(vr.getCriterion(), vr);
					}
					resPanel.setResults(results);
					resPanel.setSelectedDiagrams(newRoot);
					PanelsController.showPanel(resPanel);
				} finally {
					dialog.setVisible(false);

				}
			}
		}).start();
		dialog.setVisible(true);
		return;
	}

	public static List<VerificationResult> verifyCriterions(
			List<Criterion> criterions) {
		RunCriterions runCriterion = new RunCriterions();
		List<VerificationResult> ver = runCriterion.run(criterions);
		Client.setLastResults(ver);
		return ver;
	}

	public static int countOfSelectedCriterions(
			List<DefaultMutableTreeNode> nodes) {
		return getSelectedCriterions(getSelectedTree(nodes)).size();
	}

	public static DefaultMutableTreeNode getSelectedTree(
			List<DefaultMutableTreeNode> selectedNodes) {
		DefaultMutableTreeNode basicRoot = (DefaultMutableTreeNode) PanelsController.contradictionsPanel
				.getTree().getModel().getRoot();
		if (selectedNodes.contains(basicRoot)) {
			return basicRoot;
		}

		DefaultMutableTreeNode newRoot = (DefaultMutableTreeNode) basicRoot
				.clone();
		for (int i = 0; i < basicRoot.getChildCount(); i++) {
			DefaultMutableTreeNode type = (DefaultMutableTreeNode) basicRoot
					.getChildAt(i);

			if (selectedNodes.contains(type)) {
				DefaultMutableTreeNode newType = (DefaultMutableTreeNode) type
						.clone();
				newRoot.add(newType);
				for (int j = 0; j < type.getChildCount(); j++) {
					DefaultMutableTreeNode creterion = (DefaultMutableTreeNode) type
							.getChildAt(j);
					newType.add((DefaultMutableTreeNode) creterion.clone());
				}
			}

			else {
				DefaultMutableTreeNode newType = (DefaultMutableTreeNode) type
						.clone();

				boolean isTypeAdded = false;
				for (int j = 0; j < type.getChildCount(); j++) {
					DefaultMutableTreeNode creterion = (DefaultMutableTreeNode) type
							.getChildAt(j);

					if (selectedNodes.contains(creterion)) {
						if (!isTypeAdded) {
							newRoot.add(newType);
							isTypeAdded = true;
						}
						newType.add((MutableTreeNode) creterion.clone());
					}
				}
			}
		}

		return newRoot;
	}

	public static List<Criterion> getSelectedCriterions(
			DefaultMutableTreeNode root) {
		List<Criterion> res = new LinkedList<Criterion>();

		DefaultMutableTreeNode leaf = root.getFirstLeaf();
		do {
			res.add(((DisplayedCriterion) leaf.getUserObject()).getCriterion());
			leaf = leaf.getNextLeaf();
		} while (leaf != null);

		return res;
	}
}
