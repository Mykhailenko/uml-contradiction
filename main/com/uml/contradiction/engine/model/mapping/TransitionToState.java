package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.statemachine.State;
import com.uml.contradiction.model.statemachine.Transition;

public class TransitionToState implements Mapping {

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List list) throws MappingException {
		assert list != null;
		assert list.size() == 1;
		Object first = list.get(0);
		if ((first instanceof State) == false) {
			throw new MappingException("Unexpectred type: "
					+ first.getClass().toString());
		}
		State state = (State) first;
		List<Transition> result = new LinkedList<Transition>();
		for (Transition tr : state.getStateMachine().getTransitions()) {
			if (tr.getTarget().equals(state)) {
				result.add(tr);
			}
		}
		return result;
	}

}
