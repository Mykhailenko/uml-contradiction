package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.NamedElement;

public class IsEqualName implements Predicate {
	private static final Logger LOGGER = Logger.getRootLogger();

	/**
	 * params - require a 2 element such of class Attribute (class or object)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		assert params != null;
		if (params.size() != 2) {
			LOGGER.error("require list with 2 element");
			throw new PredicatException(
					"PredicateIsEqualName require by list with 2 element");
		}
		if ((params.get(0) instanceof com.uml.contradiction.model.object.AttributeObj) == false
				&& (params.get(0) instanceof com.uml.contradiction.model.cclass.Attribute) == false) {
			LOGGER.error("first element must be a Attribute "
					+ params.get(0).getClass().toString());
			throw new PredicatException("first element must be a Attribute");
		}
		if ((params.get(1) instanceof com.uml.contradiction.model.object.AttributeObj) == false
				&& (params.get(1) instanceof com.uml.contradiction.model.cclass.Attribute) == false) {
			LOGGER.error("second element must be a Attribute"
					+ params.get(1).getClass().toString());
			throw new PredicatException("second element must be a Attribute");
		}
		NamedElement first = (NamedElement) params.get(0);
		NamedElement second = (NamedElement) params.get(1);
		if (first.getName().equals(second.getName())) {
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
