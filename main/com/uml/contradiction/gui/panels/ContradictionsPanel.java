package com.uml.contradiction.gui.panels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.gui.vocabularies.english.DiagrPanelVoc;

public class ContradictionsPanel extends JPanel{
	private final JButton startBut = new JButton("Start");
	private final JButton backBut = new JButton("<< Back");
	private final JTree tree = new JTree();
	
	public ContradictionsPanel() {
		super();
		createGUI();
	}
	
	private void createGUI() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
	}
	
	
	
}
