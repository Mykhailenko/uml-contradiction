package com.uml.contradiction.engine.model.mapping;

import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;

public interface Mapping {

	/**
	 * 
	 * @param element
	 * @return 
	 * @throws MappingException 
	 */
	List map(Object element) throws MappingException;

}