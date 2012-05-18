package com.uml.contradiction.gui.components;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.gui.Images;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.models.DisplayedCriterion;

public class ResCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4780322878232029660L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		try {
			if (!leaf) {
				if (hasFailedCriterions(value)) {
					setIcon(Images.failIcon);
				} else {
					setIcon(Images.goodIcon);
				}
			} else {
				if (isFail(value)) {
					setIcon(Images.failIcon);
				} else {
					setIcon(Images.goodIcon);
				}
			}
		} catch (Exception e) {

		}

		return this;
	}

	protected boolean isFail(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Criterion crit = (((DisplayedCriterion) node.getUserObject())
				.getCriterion());
		VerificationResult res = PanelsController.resultsPanel.getResult(crit);
		return res.isFail();
	}

	protected boolean hasFailedCriterions(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		DefaultMutableTreeNode child = node.getFirstLeaf();
		boolean isFail = false;

		while (child != null && !isFail) {
			Criterion crit = (((DisplayedCriterion) child.getUserObject())
					.getCriterion());
			VerificationResult res = PanelsController.resultsPanel
					.getResult(crit);
			if (res.isFail()) {
				isFail = true;
			}
			child = child.getNextLeaf();
		}

		return isFail;
	}
}
