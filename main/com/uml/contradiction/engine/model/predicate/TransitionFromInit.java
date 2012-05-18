package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.statemachine.State;
import com.uml.contradiction.model.statemachine.Transition;

public class TransitionFromInit implements Predicate {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List list) throws PredicatException {
		assert list != null;
		assert list.size() == 1;
		Object first = list.get(0);
		if ((first instanceof Transition) == false) {
			throw new PredicatException("Unexpected type "
					+ first.getClass().toString());
		}
		Transition transition = (Transition) first;
		State source = (State) transition.getSource();
		return source.getName().equals("Initial");
	}

	@Override
	public String getFailDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
