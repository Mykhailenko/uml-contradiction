package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.BoundedPredicate;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.mapping.ClassObjectMapping;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPObjects;

public class MustExistClassCriterion extends Criterion {
	public MustExistClassCriterion() {
		super();
		
		Quantifier quantifier = new Quantifier();
		quantifier.setType(QuantifierType.ALL);
		quantifier.setBoundVariable(Variable.o);
		quantifier.setRightPart(new RPObjects());
		getQuantifiers().add(quantifier);
		
		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALONE);
		quantifier2.setBoundVariable(Variable.c);
		ComplexRightPart rightPart = new ComplexRightPart();
		rightPart.getBoundVariables().add(Variable.o);
		rightPart.getNestedMappings().add(new ClassObjectMapping());
		quantifier2.setRightPart(rightPart);
		getQuantifiers().add(quantifier2);
	}

	@Override
	public int getInternalID() {
		return -2;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_OBJECT;
	}
}
