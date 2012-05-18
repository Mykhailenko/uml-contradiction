package com.uml.contradiction.model.statemachine;

public class Guard {
	private String constraint;

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	@Override
	public String toString() {
		return "Guard [constraint=" + constraint + "]";
	}

}
