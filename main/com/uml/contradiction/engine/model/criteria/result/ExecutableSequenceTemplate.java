package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.LifeLine;

public class ExecutableSequenceTemplate extends ResultTemplate {

	@Override
	public void fill(HistoryPlainItem hpi) {
		@SuppressWarnings("unused")
		Interaction interaction = (Interaction) hpi.getItems().get(0).value;
		LifeLine lifeLine = (LifeLine) hpi.getItems().get(1).value;
		this.description = "Trere is unexecutable sequence of messages to lifeline"
				+ ResultTemplate.ELEMENT_MARKER
				+ lifeLine.getName()
				+ ResultTemplate.ELEMENT_MARKER;
		super.setDiagrams(hpi.getItems());
	}

	@Override
	public void setDiagrTypes() {
		diagrTypes.add(null);
		diagrTypes.add(DiagramType.SEQUENCE);

	}

}
