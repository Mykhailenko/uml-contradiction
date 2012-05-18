package com.uml.contradiction.engine.model.mapping;

import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.statemachine.Transition;

public class TriggersOfTransition implements Mapping {

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List list) throws MappingException {
		assert list != null;
		assert list.size() == 1;
		Object first = list.get(0);
		if (first instanceof Transition) {
			Transition transition = (Transition) first;
			return transition.getTriggers();
		}
		return null;
	}

}
