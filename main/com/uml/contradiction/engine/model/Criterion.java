package com.uml.contradiction.engine.model;
import java.util.*;

public class Criterion {
	private String name;
	private Collection<Quantifier> quantifiers;
	private Collection<BoundedPredicate> boundedPredicates;
	private boolean negative;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Quantifier> getQuantifiers() {
		return quantifiers;
	}
	public void setQuantifiers(Collection<Quantifier> quantifiers) {
		this.quantifiers = quantifiers;
	}
	public Collection<BoundedPredicate> getBoundedPredicates() {
		return boundedPredicates;
	}
	public void setBoundedPredicates(Collection<BoundedPredicate> boundedPredicates) {
		this.boundedPredicates = boundedPredicates;
	}
	public boolean isNegative() {
		return negative;
	}
	public void setNegative(boolean negative) {
		this.negative = negative;
	}
	
}