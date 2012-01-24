package com.uml.contradiction.engine.model.mapping;

import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;

public interface Mapping {

	/**
	 * 
	 * @param args
	 * @return 
	 * @throws MappingException 
	 */
	List map(List args) throws MappingException;

}