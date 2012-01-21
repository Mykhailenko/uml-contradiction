package com.uml.contradiction.engine.model;

import java.util.List;

public class Criterion {
	private String name;
	private List<Quantifier> quantifiers;
	private List<BoundedPredicate> boundedPredicates;
	private boolean negative;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Quantifier> getQuantifiers() {
		return quantifiers;
	}
	public void setQuantifiers(List<Quantifier> quantifiers) {
		this.quantifiers = quantifiers;
	}
	public List<BoundedPredicate> getBoundedPredicates() {
		return boundedPredicates;
	}
	public void setBoundedPredicates(List<BoundedPredicate> boundedPredicates) {
		this.boundedPredicates = boundedPredicates;
	}
	public boolean isNegative() {
		return negative;
	}
	public void setNegative(boolean negative) {
		this.negative = negative;
	}
	
}