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
	
	private List<DiagramForChoise> diagrams;
	
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
		
	
		
	}
	
	

}
