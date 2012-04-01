package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.Formula;

public abstract class Criterion {
	private String name;
	private List<Quantifier> quantifiers = new LinkedList<Quantifier>();
	private Formula formula;
	abstract public int getInternalID();
	abstract public CriterionType getCriterionType();
	public List<Quantifier> getQuantifiers() {
		return quantifiers;
	}
	public void setQuantifiers(List<Quantifier> quantifiers) {
		this.quantifiers = quantifiers;
	}
	public Formula getFormula() {
		return formula;
	}
	public void setFormula(Formula formula) {
		this.formula = formula;
	}
	
	
}