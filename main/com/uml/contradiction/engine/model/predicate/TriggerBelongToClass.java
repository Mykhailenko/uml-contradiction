package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.statemachine.Trigger;

public class TriggerBelongToClass implements Predicate {
	private static final Logger LOGGER = Logger.getRootLogger();
	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List list) throws PredicatException {
		LOGGER.debug("at lesat we are there");
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
		LOGGER.debug(trigger.toString() + " m:" + method.toString());
		if (trigger.getMethodName().equals(method.getName())
				&& trigger.getParamCount() == method.getParameters().size()) {
			LOGGER.debug("there is equal");
			return true;
		} else {
			LOGGER.debug("there is UNequal");
			return false;
		}
	}

	@Override
	public String getFailDescription() {
		return null;
	}

}
