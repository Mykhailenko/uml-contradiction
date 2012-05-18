package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.sequence.Message;

public class MessageBelongToMethod implements Predicate {
	private static final Logger LOGGER = Logger.getRootLogger();

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		assert params != null;
		assert params.size() == 2;
		Object first = params.get(0);
		if ((first instanceof Message) == false) {
			LOGGER.error("Unexpected type : " + first.getClass().toString());
			throw new PredicatException("Unexpected type : "
					+ first.getClass().toString());
		}
		Object second = params.get(1);
		if ((second instanceof MMethod) == false) {
			LOGGER.error("Unexpected type : " + second.getClass().toString());
			throw new PredicatException("Unexpected type : "
					+ second.getClass().toString());
		}
		Message message = (Message) first;
		MMethod method = (MMethod) second;
		if (method.getName().equals(message.getMethodName())) {
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
