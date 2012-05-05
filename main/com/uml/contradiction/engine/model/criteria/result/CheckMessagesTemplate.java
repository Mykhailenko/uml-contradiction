package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.LifeLine;
import com.uml.contradiction.model.sequence.Message;

public class CheckMessagesTemplate extends ResultTemplate {

	@Override
	public void fill(HistoryPlainItem hpi) {
		String sep = ResultTemplate.ELEMENT_MARKER;
		Interaction interaction = (Interaction) hpi.getItems().get(0).value;
		LifeLine lifeLine = (LifeLine) hpi.getItems().get(1).value;
		Message message = (Message) hpi.getItems().get(2).value;
		description = "There is uncorrect message " + sep
				+ message.getOriginalString() + sep + " in " 
				+ sep + interaction.getName() + sep + " interaction to lifeline" 
				+ sep + lifeLine.getName() + sep;
		super.setDiagrams(hpi.getItems());
	}

	@Override
	public void setDiagrTypes() {
		diagrTypes.add(null);
		diagrTypes.add(DiagramType.SEQUENCE);
		diagrTypes.add(DiagramType.SEQUENCE);
	}

}
