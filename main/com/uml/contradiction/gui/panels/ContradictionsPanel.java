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

import com.uml.contradiction.gui.components.CheckTreeManager;
import com.uml.contradiction.gui.models.DiagramForChoise;
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
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Cuntradictions");
		DefaultTreeModel model = new DefaultTreeModel(root);
		tree.setModel(model);
		CheckTreeManager checkTreeManager = new CheckTreeManager(tree);
		JScrollPane treePanel = new JScrollPane(tree);
		
		this.setLayout(null);
		treePanel.setBounds(0, 0, 200, 300);
		this.add(treePanel);
		
		this.updateUI();
		this.repaint();
	}
	
	
	
}
