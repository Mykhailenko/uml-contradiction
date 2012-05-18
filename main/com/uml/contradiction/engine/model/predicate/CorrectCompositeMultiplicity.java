package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.AssociationEnd;
import com.uml.contradiction.model.cclass.Multiplicity;

public class CorrectCompositeMultiplicity implements Predicate {

	private static final Logger LOGGER = Logger.getRootLogger();

	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		assert params != null;
		if (params.size() != 1) {
			LOGGER.error("require list with 1 element");
			throw new PredicatException(
					"PredicateCorrectCompositeMultiplicity require by list with 1 element");
		}
		if ((params.get(0) instanceof com.uml.contradiction.model.cclass.AssociationEnd) == false) {
			LOGGER.error("element must be an AssociationEnd "
					+ params.get(0).getClass().toString());
			throw new PredicatException(
					"first element must be an AssociationEnd");
		}

		AssociationEnd end = (AssociationEnd) params.get(0);
		Multiplicity mult = end.getMultiplicity();

		if (mult == null) {
			return true;
		}
		if (mult.getUpperBound() > 1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getFailDescription() {
		return null;
	}
}
