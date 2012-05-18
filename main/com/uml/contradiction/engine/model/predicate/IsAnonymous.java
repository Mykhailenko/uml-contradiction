package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.object.OObject;

public class IsAnonymous implements Predicate {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		OObject ob = (OObject) (params.get(0));
		if (ob.getName() == null) {
			return true;
		}
		if (ob.getName().length() == 0) {
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
