package com.uml.contradiction.gui.panels;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.gui.components.CheckTreeManager;
import com.uml.contradiction.gui.components.ResCellRenderer;
import com.uml.contradiction.gui.listeners.ResPanelBackListener;
import com.uml.contradiction.gui.listeners.ResultsTreeListener;
import com.uml.contradiction.gui.models.DisplayedCriterion;
import com.uml.contradiction.gui.models.DisplayedCriterionType;

public class VerificationResultsPanel extends JPanel {
	private final JButton printBut = new JButton("Print");
	private final JButton backBut = new JButton("<< Back");
	private final JTree tree = new JTree();
	private JTextArea description = new JTextArea();
	private Map<Criterion, VerificationResult> results;
	
	public VerificationResultsPanel() {
		super();
		createGUI();
	}
	
	private void createGUI() {
		this.setLayout(null);
		
		JScrollPane treePanel = new JScrollPane(this.tree);
		JScrollPane descriptionPanel = new JScrollPane(description);
		
		this.tree.addTreeSelectionListener(new ResultsTreeListener());
		this.tree.setCellRenderer(new ResCellRenderer());
		
		this.backBut.addActionListener(new ResPanelBackListener());
		
		treePanel.setBounds(0, 0, 300, 300);
		descriptionPanel.setBounds(310, 0, 300, 300);
		backBut.setBounds(10, 310, 150, 20);
		
		this.add(treePanel);
		this.add(descriptionPanel);
		this.add(backBut);
	}
	
	public void setSelectedDiagrams(DefaultMutableTreeNode root) {
		this.tree.setModel(new DefaultTreeModel(root));
		this.tree.updateUI();
		this.tree.repaint();
		this.updateUI();
		this.repaint();
		
		return;
	}

	public Map<Criterion, VerificationResult> getResults() {
		return results;
	}

	public void setResults(Map<Criterion, VerificationResult> results) {
		this.results = results;
	}
	
	public void showResult(DefaultMutableTreeNode node) {
		System.out.println("Showing result");
		if(!node.isLeaf()) {
			this.description.setText("");
			return;
		}
		System.out.println("Showing result");
		Criterion crit = (((DisplayedCriterion)node.getUserObject()).getCriterion());
		VerificationResult res = results.get(crit);
		
		showResultDescription(res);
		
	}
	
	private void showResultDescription(VerificationResult res) {
		this.description.setText(res.toString());
	}
	
	public VerificationResult getResult(Criterion crit) {
		return this.results.get(crit);
	}
}
