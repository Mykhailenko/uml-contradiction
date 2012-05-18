package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.CompositeEndAssociation;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.CorrectCompositeMultiplicity;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPCompositions;

public class PartOfSingleComposite extends Criterion {

	public PartOfSingleComposite() {
		super();

		Quantifier quantifier = new Quantifier();
		quantifier.setType(QuantifierType.ALL);
		quantifier.setBoundVariable(Variable.a);
		quantifier.setRightPart(new RPCompositions());
		getQuantifiers().add(quantifier);

		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALL);
		quantifier2.setBoundVariable(Variable.e);
		ComplexRightPart rightPart = new ComplexRightPart();
		rightPart.getBoundVariables().add(Variable.a);
		rightPart.getNestedMappings().add(new CompositeEndAssociation());
		quantifier2.setRightPart(rightPart);
		getQuantifiers().add(quantifier2);

		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.e);
		boundedPredicate.setNegative(false);
		boundedPredicate.setPredicate(new CorrectCompositeMultiplicity());
		setFormula(boundedPredicate);

	}

	@Override
	public int getInternalID() {
		// TODO Auto-generated method stub
		return -13;
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
