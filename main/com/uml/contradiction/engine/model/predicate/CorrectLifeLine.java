package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.sequence.LifeLine;

public class CorrectLifeLine implements Predicate {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		LifeLine lifeLine = (LifeLine) params.get(0);
		if ((lifeLine.isClassLifeLine() && lifeLine.getcClass() != null)
				|| (!lifeLine.isClassLifeLine() && lifeLine.getoObject() != null)) {
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
