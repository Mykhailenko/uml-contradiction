package com.uml.contradiction.engine.model;

public class VariableValue {
	public Variable variable;
	public Object value;

	@Override
	public boolean equals(Object obj) {
		VariableValue v = (VariableValue) obj;
		if (variable.equals(v.variable) && value == v.value) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return variable.hashCode() + ((value != null )? value.hashCode() : 0);
		// return variable.hashCode() + value.hashCode();
	}

}
