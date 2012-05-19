package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.predicate.Formula;

public abstract class Criterion {
	private List<Quantifier> quantifiers = new LinkedList<Quantifier>();
	private Formula formula;

	public abstract int getInternalID();

	public abstract  CriterionType getCriterionType();

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

	public abstract ResultTemplate getResultTemplate();

	@Override
	public boolean equals(Object obj) {
		return this.getInternalID() == ((Criterion) obj).getInternalID();
	}

	public int trickyMethod() {
		return getQuantifiers().size();
	}
}