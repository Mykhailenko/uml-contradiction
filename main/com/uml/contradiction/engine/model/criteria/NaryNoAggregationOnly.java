package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.ClassAssociation;
import com.uml.contradiction.engine.model.mapping.EndAssociation;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.NoAggregation;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPNaries;

public class NaryNoAggregationOnly extends Criterion {

	public NaryNoAggregationOnly() {
		super();

		Quantifier quantifier = new Quantifier();
		quantifier.setType(QuantifierType.ALL);
		quantifier.setBoundVariable(Variable.c);
		quantifier.setRightPart(new RPNaries());
		getQuantifiers().add(quantifier);

		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALL);
		quantifier2.setBoundVariable(Variable.a);
		ComplexRightPart rightPart = new ComplexRightPart();
		rightPart.getBoundVariables().add(Variable.c);
		rightPart.getNestedMappings().add(new ClassAssociation());
		quantifier2.setRightPart(rightPart);
		getQuantifiers().add(quantifier2);

		Quantifier quantifier3 = new Quantifier();
		quantifier3.setType(QuantifierType.ALL);
		quantifier3.setBoundVariable(Variable.e);
		ComplexRightPart rightPart3 = new ComplexRightPart();
		rightPart3.getBoundVariables().add(Variable.a);
		rightPart3.getNestedMappings().add(new EndAssociation());
		quantifier3.setRightPart(rightPart3);
		getQuantifiers().add(quantifier3);

		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.e);
		boundedPredicate.setNegative(false);
		boundedPredicate.setPredicate(new NoAggregation());
		setFormula(boundedPredicate);

	}

	@Override
	public int getInternalID() {
		// TODO Auto-generated method stub
		return -11;
	}

	@Override
	public CriterionType getCriterionType() {
		// TODO Auto-generated method stub
		return CriterionType.CLASS_CLASS;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

}
