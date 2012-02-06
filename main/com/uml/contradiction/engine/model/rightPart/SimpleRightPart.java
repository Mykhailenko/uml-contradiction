package com.uml.contradiction.engine.model.rightPart;

import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;


public abstract class SimpleRightPart implements QuantifierRightPart {
	
	@Override
	abstract public List getSet(List<VariableValue> params) throws MappingException;
}