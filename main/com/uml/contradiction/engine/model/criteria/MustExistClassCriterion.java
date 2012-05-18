package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.MustExistClassTemplate;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.IsObjInsancetOfClass;
import com.uml.contradiction.engine.model.rightPart.simple.RPClasses;
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
		quantifier2.setRightPart(new RPClasses());
		getQuantifiers().add(quantifier2);

		BoundedPredicate pr = new BoundedPredicate();
		pr.setPredicate(new IsObjInsancetOfClass());
		pr.getBoundVariable().add(Variable.o);
		pr.getBoundVariable().add(Variable.e);

		this.setFormula(pr);
	}

	@Override
	public int getInternalID() {
		return -9;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_OBJECT;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		return new MustExistClassTemplate();
	}

	@Override
	public int trickyMethod() {
		return 1;
	}
}
