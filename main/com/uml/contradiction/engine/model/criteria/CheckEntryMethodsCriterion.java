package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.MessagesForTransition;
import com.uml.contradiction.engine.model.mapping.TransitionToState;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.WeirdPredicate;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.StatesWithEntryRP;

public class CheckEntryMethodsCriterion extends Criterion {
	public CheckEntryMethodsCriterion() {
		Quantifier quantifier0 = new Quantifier();
		quantifier0.setType(QuantifierType.ALL);
		quantifier0.setBoundVariable(Variable.s);
		quantifier0.setRightPart(new StatesWithEntryRP());
		getQuantifiers().add(quantifier0);

		Quantifier quantifier1 = new Quantifier();
		quantifier1.setType(QuantifierType.ALL);
		quantifier1.setBoundVariable(Variable.t);
		ComplexRightPart rightPart0 = new ComplexRightPart();
		rightPart0.getBoundVariables().add(Variable.s);
		rightPart0.getNestedMappings().add(new TransitionToState());
		quantifier1.setRightPart(rightPart0);
		getQuantifiers().add(quantifier1);

		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALL);
		quantifier2.setBoundVariable(Variable.m);
		ComplexRightPart rightPart1 = new ComplexRightPart();
		rightPart1.getBoundVariables().add(Variable.t);
		rightPart1.getNestedMappings().add(new MessagesForTransition());
		quantifier2.setRightPart(rightPart1);
		getQuantifiers().add(quantifier2);

		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.s);
		boundedPredicate.getBoundVariable().add(Variable.m);
		boundedPredicate.setPredicate(new WeirdPredicate());
		setFormula(boundedPredicate);
	}

	@Override
	public int getInternalID() {
		return -2;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.SEQUENCE_STATE;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

}
