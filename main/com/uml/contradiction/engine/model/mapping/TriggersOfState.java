package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.statemachine.StateMachine;
import com.uml.contradiction.model.statemachine.Transition;
import com.uml.contradiction.model.statemachine.Trigger;

public class TriggersOfState implements Mapping {

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List list) throws MappingException {
		assert list != null;
		assert list.size() == 1;
		Object first = list.get(0);
		if (first instanceof StateMachine) {
			StateMachine stateMachine = (StateMachine) first;
			List<Trigger> result = new LinkedList<Trigger>();
			for (Transition transition : stateMachine.getTransitions()) {
				assert transition != null : "transition is null";
				System.out.println(transition);
				assert transition.getTriggers() != null : "triggers is null";
				result.addAll(transition.getTriggers());
			}
			return result;
		}
		return null;
	}

}
