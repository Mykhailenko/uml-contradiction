package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;

public interface Predicate {

	/**
	 * 
	 * @param params
	 *            list of real value of object . example 'isEqualName' have
	 *            first argument Attribute, and second param attribute too but
	 *            its belons to Object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	boolean predict(List params) throws PredicatException;

	String getFailDescription();
}