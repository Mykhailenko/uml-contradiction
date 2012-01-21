package com.uml.contradiction.engine.model;

import java.util.List;

public interface Predicate {

	/**
	 * 
	 * @param params
	 * @return 
	 */
	boolean predict(List params);

}