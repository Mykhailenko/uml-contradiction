package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;

public class IsComposition implements Predicate {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		Association as = (Association) (params.get(0));
		if ((as.getEnd1().getAggregationKind() != null && as.getEnd1()
				.getAggregationKind() == AggregationKind.COMPOSITE)
				|| (as.getEnd2().getAggregationKind() != null && as.getEnd2()
						.getAggregationKind() == AggregationKind.COMPOSITE)) {
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