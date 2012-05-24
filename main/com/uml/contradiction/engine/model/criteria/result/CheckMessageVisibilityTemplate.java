package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.LifeLine;
import com.uml.contradiction.model.sequence.Message;

public class CheckMessageVisibilityTemplate extends ResultTemplate {

	@Override
	public void fill(HistoryPlainItem hpi) {
		String sep = ResultTemplate.ELEMENT_MARKER;
		Interaction interaction = (Interaction) hpi.getItems().get(0).value;
		LifeLine lifeLine = (LifeLine) hpi.getItems().get(1).value;
		Message message = (Message) hpi.getItems().get(2).value;
		if (message != null && interaction != null & lifeLine != null) {
			description = "There is unvisible message " + sep
					+ m(message.getOriginalString()) + sep + " in " + sep
					+ m(interaction.getName()) + sep
					+ " interaction to lifeline" + sep + m(lifeLine.getName())
					+ sep;
		} else if (lifeLine != null) {
			description = "There is unvisible message to lifeline " + sep
					+ m(lifeLine.getName()) + sep;
		}
		super.setDiagrams(hpi.getItems());
	}

	private static String m(String n) {
		if (n == null) {
			return "";
		} else {
			return n;
		}
	}

	@Override
	public void setDiagrTypes() {
		diagrTypes.add(null);
		diagrTypes.add(DiagramType.SEQUENCE);
		diagrTypes.add(DiagramType.SEQUENCE);
	}
}
