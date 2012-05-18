package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.statemachine.StateMachine;
import com.uml.contradiction.model.statemachine.Transition;

public class TransitionOfStateMachine implements Mapping {

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List list) throws MappingException {
		assert list != null;
		assert list.size() == 1;
		Object first = list.get(0);
		if (first instanceof StateMachine) {
			StateMachine stateMachine = (StateMachine) first;
			List<Transition> result = new LinkedList<Transition>();
			for (Transition transition : stateMachine.getTransitions()) {
				assert transition != null : "transition is null";
				result.add(transition);
			}
			System.out.println("transition count " + result.size());
			return result;
		}
		return null;
	}
}
