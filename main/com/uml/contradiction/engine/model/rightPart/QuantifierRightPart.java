package com.uml.contradiction.engine.model.rightPart;

import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;

public interface QuantifierRightPart {

	/**
	 * 
	 * @param params
	 * @return 
	 */
	List getSet(List<VariableValue> params);

}