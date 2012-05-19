package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.statemachine.Transition;
import com.uml.contradiction.model.statemachine.Trigger;

public class TransitionBelongToClass implements Predicate {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List list) throws PredicatException {
		assert list != null;
		assert list.size() == 2;
		Object first = list.get(0);
		if ((first instanceof Transition) == false) {
			throw new PredicatException("Unexpected type "
					+ first.getClass().toString());
		}
		Object second = list.get(1);
		if ((second instanceof CClass) == false) {
			throw new PredicatException("Unexpected type "
					+ second.getClass().toString());
		}
		Transition transition = (Transition) first;
		CClass cClass = (CClass) second;
		for (MMethod method : cClass.getMethods()) {
			if (transitionBelongToMethod(transition, method)) {
				System.out.println(transition.getName() + " belong");
				return true;
			} else {
				System.out.println("bad " + method);
			}
		}
		System.out.println(transition.getName() + " not belong ");
		return false;
	}

	private boolean transitionBelongToMethod(Transition transition,
			MMethod method) {
		if (transition.getShortName().equals(method.getName())
				&& TransitionBelongToClass.paramsCount(transition.getName()) == method
						.getParameters().size()) {
			return true;
		} else {
			return false;
		}
	}

	private static int paramsCount(String str) {
		return Trigger.getNameAndParamCount(str).b;
//		if (str.contains("(")) { // first or second case
//			String params = str.substring(str.indexOf('('), str.indexOf(')'));
//			params = params.replaceAll(" ", "");
//			if (params.length() > 2) {// first case
//				return params.split(",").length;
//			} else {
//				if (str.contains("()")) {
//					return 0;
//				} else {
//					return 1;
//				}
//			}
//		} else {
//			return 0;
//		}
	}

	@Override
	public String getFailDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
