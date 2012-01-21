package com.uml.contradiction.engine.model;

import java.util.List;

public interface Predicate {

	/**
	 * 
	 * @param params list of real value of object . example 'isEqualName' have first argument Attribute, and
	 * second param attribute too but its belons to Object
	 * @return 
	 */
	boolean predict(List params);

}