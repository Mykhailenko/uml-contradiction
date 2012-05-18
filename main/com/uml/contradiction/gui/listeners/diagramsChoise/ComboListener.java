package com.uml.contradiction.gui.listeners.diagramsChoise;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.gui.panels.DiagramsPanel;
import com.uml.contradiction.gui.panels.diagramstypes.ViewedDiagramType;

public class ComboListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DiagramsPanel panel = PanelsController.diagramsPanel;
		JComboBox combo = (JComboBox) arg0.getSource();
		Object selected = combo.getSelectedItem();

		List<DiagramForChoise> fromDiagrams = panel.getDiagrams();
		List<DiagramForChoise> newFromDiagrams = new LinkedList<DiagramForChoise>();
		List<DiagramForChoise> toDiagrams = panel.getToDiagrams();
		List<DiagramForChoise> newToDiagrams = new LinkedList<DiagramForChoise>();

		if (selected instanceof String) {
			newFromDiagrams.addAll(fromDiagrams);
			newToDiagrams.addAll(toDiagrams);
		} else {
			DiagramType type = ((ViewedDiagramType) selected).getType();
			for (int i = 0; i < fromDiagrams.size(); i++) {
				if (fromDiagrams.get(i).getType() == type) {
					newFromDiagrams.add(fromDiagrams.get(i));
				}
			}
			for (int i = 0; i < toDiagrams.size(); i++) {
				if (toDiagrams.get(i).getType() == type) {
					newToDiagrams.add(toDiagrams.get(i));
				}
			}
		}

		panel.setViewedFromDiagrams(newFromDiagrams);
		panel.setViewedToDiagrams(newToDiagrams);
	}
}
