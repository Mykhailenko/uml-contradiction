package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.sequence.Message;

public class MessageBelongToClass implements Predicate {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List list) throws PredicatException {
		assert list != null;
		assert list.size() == 2;
		Object first = list.get(0);
		if ((first instanceof Message) == false) {
			throw new PredicatException("Unexpected type: "
					+ first.getClass().toString());
		}
		Object second = list.get(1);
		if ((second instanceof CClass) == false) {
			throw new PredicatException("Unexpected type: "
					+ second.getClass().toString());
		}
		Message message = (Message) first;
		CClass cClass = (CClass) second;
		for (MMethod method : cClass.getMethods()) {
			if (messageBelongToMethod(message, method)) {
				return true;
			}
		}
		return false;
	}

	private static boolean messageBelongToMethod(Message message, MMethod method) {
		if (message.getMethodName().equals(method.getName())
				&& message.getParamCount() == method.getParameters().size()) {
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
