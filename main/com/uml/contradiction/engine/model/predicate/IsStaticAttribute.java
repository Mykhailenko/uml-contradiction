package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.Scope;

public class IsStaticAttribute implements Predicate {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		if (params.get(0) == null) {
			return false;
		}

		Attribute attr = (Attribute) (params.get(0));
		if (attr.getScope() == Scope.CLASSIFIER) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getFailDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}