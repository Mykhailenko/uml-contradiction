package com.uml.contradiction.gui.panels;



import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.CriterionSuite;
import com.uml.contradiction.gui.components.CheckTreeManager;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.gui.models.DisplayedCriterion;
import com.uml.contradiction.gui.vocabularies.english.DiagrPanelVoc;

public class ContradictionsPanel extends JPanel{
	private final JButton startBut = new JButton("Start");
	private final JButton backBut = new JButton("<< Back");
	private final JTree tree = new JTree();
	private final JTextArea description = new JTextArea();
	
	public ContradictionsPanel() {
		super();
		createGUI();
	}
	
	private void createGUI() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Criterions");
		DefaultTreeModel model = new DefaultTreeModel(root);
		
		DefaultMutableTreeNode classClass = new DefaultMutableTreeNode("Class-Class");
		DefaultMutableTreeNode classObj = new DefaultMutableTreeNode("Class-Object");
		DefaultMutableTreeNode classSeq = new DefaultMutableTreeNode("Class-Sequence");
		DefaultMutableTreeNode classState = new DefaultMutableTreeNode("Class-State_Machine");
		DefaultMutableTreeNode stateSeq = new DefaultMutableTreeNode("Sequence-State_Machine");
		List<DisplayedCriterion> criterions = CriterionSuite.getDisplayedCriterions();
		
		for(DisplayedCriterion crit:criterions) {
			DefaultMutableTreeNode nodeToAdd = new DefaultMutableTreeNode(crit);
			
			switch(crit.getType()) {
			case CLASS_CLASS:
				classClass.add(nodeToAdd);
				break;
			case CLASS_OBJECT:
				classObj.add(nodeToAdd);
				break;
			case CLASS_SEQUENCE:
				classSeq.add(nodeToAdd);
				break;
			case CLASS_STATE:
				classState.add(nodeToAdd);
				break;
			case SEQUENCE_STATE:
				stateSeq.add(nodeToAdd);
				break;
			default:
				break;
			}
		}
			
		root.add(classClass);
		root.add(classObj);
		root.add(classSeq);
		root.add(classState);
		root.add(stateSeq);
		
		tree.setModel(model);
		CheckTreeManager checkTreeManager = new CheckTreeManager(tree);
		JScrollPane treePanel = new JScrollPane(tree);
		
		
		
		
		
		this.setLayout(null);
		treePanel.setBounds(0, 0, 400, 300);
		this.add(treePanel);
		
		this.updateUI();
		this.repaint();
	}
	
	
	
}
