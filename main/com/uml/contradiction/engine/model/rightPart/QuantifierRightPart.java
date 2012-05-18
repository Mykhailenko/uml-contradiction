package com.uml.contradiction.engine.model.rightPart;

import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;

public interface QuantifierRightPart {

	/**
	 * 
	 * @param params
	 * @return
	 * @throws MappingException
	 */
	@SuppressWarnings("rawtypes")
	List getSet(List<VariableValue> params) throws MappingException;

}