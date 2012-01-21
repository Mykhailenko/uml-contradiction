package com.uml.contradiction.engine.model;
import java.util.*;

public class BoundedPredicate {

	Collection<Variable> boundVariable;
	private boolean negative;
	private Predicate predicate;
	public boolean isNegative() {
		return this.negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}
	
}