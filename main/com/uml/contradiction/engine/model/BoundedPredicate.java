package com.uml.contradiction.engine.model;
import java.util.*;

import com.uml.contradiction.engine.model.predicate.Predicate;

public class BoundedPredicate {

	private List<Variable> boundVariable = new LinkedList<Variable>();
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