package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;

public class MustExistMethodCriterion extends Criterion {
	public MustExistMethodCriterion() {
		super();
		Quantifier quantifier0 = new Quantifier();
		quantifier0.setType(QuantifierType.ALL);
		quantifier0.setBoundVariable(Variable.m);
		
		
	}
	
	
	@Override
	public int getInternalID() {
		return -2;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_SEQUENCE;
	}

}
