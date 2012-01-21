package com.uml.contradiction.engine.model;
import java.util.*;

public class BoundedPredicate {

	private List<Variable> boundVariable;
	private boolean negative;
	private Predicate predicate;
	public boolean isNegative() {
		return this.negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}

	public List<Variable> getBoundVariable() {
		return boundVariable;
	}

	public void setBoundVariable(List<Variable> boundVariable) {
		this.boundVariable = boundVariable;
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
	
}