package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;

public class IsNull implements Predicate {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		if (params.get(0) == null) {
			return true;
		}
		return false;
	}

	@Override
	public String getFailDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
