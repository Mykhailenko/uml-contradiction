package com.uml.contradiction.engine.model.criteria.result;

import java.util.List;

import com.uml.contradiction.engine.model.Variable;

public interface ResultTemplate {
	
	public void fill(List<Variable> vars);
	public List<Diagram> getDiagrams();
}
