package com.uml.contradiction.gui.panels;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.CriterionSuite;
import com.uml.contradiction.engine.model.criteria.CriterionTypeSuite;
import com.uml.contradiction.gui.components.CheckTreeManager;
import com.uml.contradiction.gui.listeners.ContrTreeListener;
import com.uml.contradiction.gui.listeners.StartCheckListener;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.gui.models.DisplayedCriterion;
import com.uml.contradiction.gui.models.DisplayedCriterionType;
import com.uml.contradiction.gui.vocabularies.english.DiagrPanelVoc;

public class ContradictionsPanel extends JPanel{
	private final JButton startBut = new JButton("Start");
	private final JButton backBut = new JButton("<< Back");
	private final JTree tree = new JTree();
	private final JTextArea description = new JTextArea();
	private CheckTreeManager checkTreeManager;
	
	public ContradictionsPanel() {
		super();
		createGUI();
	}
	
	private void createGUI() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Criterions");
		DefaultTreeModel model = new DefaultTreeModel(root);
		
		List<DisplayedCriterion> criterions = CriterionSuite.getDisplayedCriterions();
		List<DisplayedCriterionType> types = CriterionTypeSuite.getAllDisplayedCriterions();
		
		for(DisplayedCriterionType type:types) {
			DefaultMutableTreeNode typeNode = new DefaultMutableTreeNode(type);
			root.add(typeNode);

			for(DisplayedCriterion crit:criterions){
				if(crit.getType().equals(type.getType())){
					typeNode.add(new DefaultMutableTreeNode(crit));
				}
			}
		}
		
		tree.addTreeSelectionListener(new ContrTreeListener());
		
		tree.setModel(model);
		this.checkTreeManager = new CheckTreeManager(tree);
		JScrollPane treePanel = new JScrollPane(tree);
		JScrollPane descriptionPanel = new JScrollPane(description);
		description.setEditable(false);
		
		startBut.addActionListener(new StartCheckListener());
		
		this.setLayout(null);
		treePanel.setBounds(0, 0, 300, 300);
		descriptionPanel.setBounds(310, 0, 300, 300);
		startBut.setBounds(10, 310, 100, 25);
		this.add(treePanel);
		this.add(descriptionPanel);
		this.add(startBut);
		
		this.updateUI();
		this.repaint();
	}
	
	public void showDescription(Object object) {
		
		if(object instanceof DisplayedCriterion) {
			this.description.setText(((DisplayedCriterion)object).getDesctiption());
			this.description.repaint();
			this.description.updateUI();
			this.updateUI();
		}
		else {
			this.description.setText("");
			this.description.repaint();
			this.description.updateUI();
			this.updateUI();			
		}
	}
	
	public List<Criterion> getSelectedCriterions() {
		List<Criterion> res = new LinkedList<Criterion>();
		
		TreePath[] paths = checkTreeManager.getSelectionModel().getSelectionPaths(); 
		for(TreePath path:paths) {
			System.out.println(((DefaultMutableTreeNode)path.getLastPathComponent()).getUserObject().toString());
			System.out.println(path.getClass());
		}
		
		return res;
	}
	
	public JTree getTree() {
		return tree;
	}
	
}
