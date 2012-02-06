package com.uml.contradiction.gui.panels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.gui.panels.diagramstypes.ViewedDiagramType;
import com.uml.contradiction.gui.vocabularies.english.DiagrPanelVoc;

public class DiagramsPanel extends JPanel {
	
	private List<DiagramForChoise> diagrams = new ArrayList();
	
	public DiagramsPanel() {
		super();
		createGUI();
	}
	
	private void createGUI() {
		final JButton nextBut = new JButton(DiagrPanelVoc.getBtnLabel("nextBut"));
		final JButton backBut = new JButton(DiagrPanelVoc.getBtnLabel("backBut"));
		final JButton addBut  = new JButton(DiagrPanelVoc.getBtnLabel("addBut"));
		final JButton addAllBut  = new JButton(DiagrPanelVoc.getBtnLabel("addAllBut"));
		final JButton removeBut = new JButton(DiagrPanelVoc.getBtnLabel("removeBut"));
		final JButton removeAllBut = new JButton(DiagrPanelVoc.getBtnLabel("removeAllBut"));
				
		final JComboBox combo;
		final JList fromList;
		final JList toList;
		
		List<Object> diagramsTypes = new ArrayList<Object>();
		diagramsTypes.add("All");
		diagramsTypes.add(new ViewedDiagramType(DiagramType.CLASS, "Class"));
		diagramsTypes.add(new ViewedDiagramType(DiagramType.OBJECT, "Object"));
		diagramsTypes.add(new ViewedDiagramType(DiagramType.SEQUENCE, "Sequence"));
		diagramsTypes.add(new ViewedDiagramType(DiagramType.STATE_MACHINE, "StateMaschene"));
	
		final DefaultComboBoxModel comboModel = new DefaultComboBoxModel(diagramsTypes.toArray());	
		combo = new JComboBox(comboModel);
		fromList = new JList(this.diagrams.toArray());
		toList = new JList();
		
		this.setLayout(null);
		combo.setBounds(10, 10, 150, 20);
		fromList.setBounds(10, 40, 150, 300);
		addBut.setBounds(10, 350, 70, 30);
		addAllBut.setBounds(90, 350, 70, 30);
		toList.setBounds(170, 40, 150, 300);
		removeBut.setBounds(170, 350, 70, 30);
		removeAllBut.setBounds(250, 350, 70, 30);
		
		this.add(combo);
		this.add(fromList);
		this.add(addBut);
		this.add(addAllBut);
		this.add(toList);
		this.add(removeBut);
		this.add(removeAllBut);
		
		this.updateUI();
		this.repaint();
	}
	
	public void setDiagrams(List<DiagramForChoise> newDiagrams) {
		this.diagrams.clear();
		this.diagrams.addAll(newDiagrams);
		this.updateUI();
		this.repaint();
	}
	public void createGUIForDiagrams(List<DiagramForChoise> lise){
		
	}
}