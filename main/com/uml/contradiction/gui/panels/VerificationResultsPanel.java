package com.uml.contradiction.gui.panels;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.gui.GUIState;
import com.uml.contradiction.gui.components.CheckTreeManager;
import com.uml.contradiction.gui.components.ResCellRenderer;
import com.uml.contradiction.gui.listeners.FailDiagrListListener;
import com.uml.contradiction.gui.listeners.ResPanelBackListener;
import com.uml.contradiction.gui.listeners.ResultsTreeListener;
import com.uml.contradiction.gui.models.DisplayedCriterion;
import com.uml.contradiction.gui.models.DisplayedCriterionType;

public class VerificationResultsPanel extends JPanel implements GUIState {
	private final JButton printBut = new JButton("Print");
	private final JButton backBut = new JButton("<< Back");
	private final JTree tree = new JTree();
	private JTextArea description = new JTextArea();
	private final DefaultListModel listModel = new DefaultListModel();
	private Map<Criterion, VerificationResult> results;
	private Map<String, List<String> > descrByDiagrams = new HashMap<String, List<String>>();
	private final JList diagrams = new JList(listModel);
	
//	private Map<Criterion, List<ResultTemplate> > resultsDescr = new  HashMap<Criterion, List<ResultTemplate> >();
	
	public VerificationResultsPanel() {
		super();
		createGUI();
	}
	
	private void createGUI() {
		this.setLayout(null);
		
		JScrollPane treePanel = new JScrollPane(this.tree);
		JScrollPane descriptionPanel = new JScrollPane(description);
		JScrollPane listPanel = new JScrollPane(diagrams);		
		
		this.tree.addTreeSelectionListener(new ResultsTreeListener());
		this.tree.setCellRenderer(new ResCellRenderer());
		
		this.backBut.addActionListener(new ResPanelBackListener());
		
		this.diagrams.addListSelectionListener(new FailDiagrListListener());
		
		treePanel.setBounds(10, 20, 360, 480);
		descriptionPanel.setBounds(380, 20, 400, 120);
		backBut.setBounds(10, 510, 100, 25);
		listPanel.setBounds(380, 160, 400, 340);
		
		this.add(listPanel);
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
		this.listModel.removeAllElements();
		this.descrByDiagrams.clear();
		
		if(!node.isLeaf()) {
			this.description.setText("");
			
		}
		else {
			System.out.println("Showing result");
			
			Criterion crit = (((DisplayedCriterion)node.getUserObject()).getCriterion());
			VerificationResult res = results.get(crit);
			
			if(res.isGood()) {
				this.description.setText("Everything is correct");
			}
			else {
				
				List<ResultTemplate> descriptions = res.getResultTemplate();
				
				for(ResultTemplate rt : descriptions) {
					String diagrams = rt.getDiagramsNames();
					String description = rt.getDescription();
					
					if(!this.descrByDiagrams.containsKey(diagrams)){
						System.out.println("Adding element " + diagrams);
						List<String> descr= new LinkedList<String>();
						System.out.println(descr);
						descr.add(description);
						System.out.println("PUTTED: " + diagrams + " " + description);
						this.descrByDiagrams.put(diagrams, descr);
						
						this.listModel.addElement(diagrams);
					}
					else {
						List<String> descr = this.descrByDiagrams.get(diagrams);
						descr.add(description);
					}
				}
			}
		}
		this.diagrams.updateUI();
		this.diagrams.repaint();
		this.updateUI();
		this.repaint();
		
		return;
	}
	
	public void showDescription(List<String> descriptions) {
		String text = new String();
		
		for(int i = 0; i < descriptions.size(); i++) {
			text = text + "Contradiction " + String.valueOf(i+1) + ":\n";
			text = text + descriptions.get(i);
			text = text + "\n";
		}
		
		this.description.setText(text);
		this.updateUI();
		this.repaint();
	}
	
	public VerificationResult getResult(Criterion crit) {
		return this.results.get(crit);
	}

	public Map<String, List<String>> getDescrByDiagrams() {
		return descrByDiagrams;
	}

	public void setDescrByDiagrams(Map<String, List<String>> descrByDiagrams) {
		this.descrByDiagrams = descrByDiagrams;
	}

	public JList getDiagrams() {
		return diagrams;
	}

	@Override
	public void started() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadedNoOneSelected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadedOneSelected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verified() {
		// TODO Auto-generated method stub
		
	}
	
}
