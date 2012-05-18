package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.statemachine.StateMachine;
import com.uml.contradiction.model.statemachine.Transition;
import com.uml.contradiction.model.statemachine.Trigger;

public class CheckTriggersTemplate extends ResultTemplate {

	@Override
	public void fill(HistoryPlainItem hpi) {
		String sep = ResultTemplate.ELEMENT_MARKER;
		StateMachine stateMachine = (StateMachine) hpi.getItems().get(0).value;
		Transition transition = (Transition) hpi.getItems().get(1).value;
		Trigger trigger = (Trigger) hpi.getItems().get(2).value;
		if (trigger != null) {
			description = "There is an uncorrect trigger" + sep
					+ trigger.getMethodName() + sep;
		} else if (transition != null) {
			description = "There is an uncorrect transition" + sep
					+ transition.getName() + sep;
		} else if (stateMachine != null) {
			description = "There is an uncorrect state machine " + sep
					+ stateMachine.getName() + sep;
		} else {
			description = "can't say where it is";
		}
		super.setDiagrams(hpi.getItems());
	}

	@Override
	public void setDiagrTypes() {
		diagrTypes.add(null);
		diagrTypes.add(DiagramType.STATE_MACHINE);
		diagrTypes.add(null);

	}

}
