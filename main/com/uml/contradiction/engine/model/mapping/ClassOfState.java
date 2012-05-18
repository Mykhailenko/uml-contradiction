package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.statemachine.StateMachine;

public class ClassOfState implements Mapping {

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List list) throws MappingException {
		assert list != null;
		assert list.size() == 1;
		Object first = list.get(0);
		if (first instanceof StateMachine) {
			StateMachine stateMachine = (StateMachine) first;
			List<CClass> result = new LinkedList<CClass>();
			result.add(stateMachine.getcClass());
			return result;
		}
		return null;
	}

}
