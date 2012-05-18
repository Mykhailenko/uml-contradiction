package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.OObject;

public class IsObjInsancetOfClass implements Predicate {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		OObject ob = (OObject) (params.get(0));
		CClass cl = (CClass) (params.get(1));
		if (ob.getClasses() == cl) {
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
