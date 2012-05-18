package com.uml.contradiction.gui.listeners;

import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.uml.contradiction.gui.controllers.PanelsController;

public class FailDiagrListListener implements ListSelectionListener {

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String diagrams = (String) (PanelsController.resultsPanel.getDiagrams()
				.getSelectedValue());
		List<String> descriptions = PanelsController.resultsPanel
				.getDescrByDiagrams().get(diagrams);
		if (descriptions != null) {
			PanelsController.resultsPanel.showDescription(descriptions);
		}
	}
	// PanelsController.resultsPanel
}
