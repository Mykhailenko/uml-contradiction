package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;

public interface Formula {
	public boolean predict(List<VariableValue> variableValues);
}
