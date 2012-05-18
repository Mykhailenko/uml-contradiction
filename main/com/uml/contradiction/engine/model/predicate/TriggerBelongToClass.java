package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.statemachine.Trigger;

public class TriggerBelongToClass implements Predicate {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List list) throws PredicatException {
		assert list != null;
		assert list.size() == 2;
		Object first = list.get(0);
		if ((first instanceof Trigger) == false) {
			throw new PredicatException("Unexpected type "
					+ first.getClass().toString());
		}
		Object second = list.get(1);
		if ((second instanceof CClass) == false) {
			throw new PredicatException("Unexpected type "
					+ second.getClass().toString());
		}
		Trigger trigger = (Trigger) first;
		CClass cClass = (CClass) second;
		for (MMethod method : cClass.getMethods()) {
			if (triggerBelongToMethod(trigger, method)) {
				return true;
			}
		}
		return false;
	}

	private boolean triggerBelongToMethod(Trigger trigger, MMethod method) {
		if (trigger.getMethodName().equals(method.getName())
				&& trigger.getParamCount() == method.getParameters().size()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getFailDescription() {
		return null;
	}

}
