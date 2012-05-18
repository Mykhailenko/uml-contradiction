package com.uml.contradiction.engine.model.rightPart.simple;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.engine.model.rightPart.QuantifierRightPart;
import com.uml.contradiction.model.statemachine.State;
import com.uml.contradiction.model.statemachine.StateMachine;
import com.uml.contradiction.model.statemachine.StateMachineGraph;

public class StatesWithEntryRP implements QuantifierRightPart {

	@Override
	@SuppressWarnings("rawtypes")
	public List getSet(List<VariableValue> params) throws MappingException {
		List<State> result = new LinkedList<State>();
		for (StateMachine sm : StateMachineGraph.getStateMachines()) {
			for (State s : sm.getStates()) {
				if (s.getEntry() != null) {
					result.add(s);
				}
			}
		}
		return result;
	}

}
