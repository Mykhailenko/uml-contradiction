package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.cclass.Scope;
import com.uml.contradiction.model.sequence.Message;

public class MessageMethodStatic implements Predicate {
	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		assert params != null;
		assert params.size() == 2;
		Object first = params.get(0);
		if ((first instanceof Message) == false) {
			throw new PredicatException("Unexpected type: "
					+ first.getClass().toString());
		}
		Object second = params.get(1);
		if ((second instanceof MMethod) == false) {
			throw new PredicatException("Unexpected type: "
					+ second.getClass().toString());
		}
		Message message = (Message) first;
		MMethod method = (MMethod) second;
		if (message.getTarget().isClassLifeLine() == (method.getScope() == Scope.CLASSIFIER)) {
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
