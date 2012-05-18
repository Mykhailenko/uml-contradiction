package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.cclass.Visibility;
import com.uml.contradiction.model.sequence.Message;

public class MessageMethodVisibility implements Predicate {

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
		CClass cClass0 = message.getSource().getcClass();
		CClass cClass1 = message.getTarget().getcClass();
		if (cClass1.getVisibility().equals(Visibility.PRIVATE)
				|| cClass1.getVisibility().equals(Visibility.PROTECTED)) {
			return false;
		}
		if (cClass1.getVisibility().equals(Visibility.PACKAGE)) {
			return cClass0.getPackageName().equals(cClass1.getPackageName());
		}
		if (method.getVisibility().equals(Visibility.PUBLIC) == false) {
			return false;
		}
		return false;
	}

	@Override
	public String getFailDescription() {
		return null;
	}

}
