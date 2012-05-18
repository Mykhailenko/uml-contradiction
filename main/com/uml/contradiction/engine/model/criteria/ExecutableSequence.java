package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.ExecutableSequenceTemplate;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.LifeLineFromInteraction;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.IsExecutableLifeLine;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPInteractions;

public class ExecutableSequence extends Criterion {
	public ExecutableSequence() {
		super();
		Quantifier quantifier0 = new Quantifier();
		quantifier0.setType(QuantifierType.ALL);
		quantifier0.setBoundVariable(Variable.f);
		quantifier0.setRightPart(new RPInteractions());
		getQuantifiers().add(quantifier0);

		Quantifier quantifier1 = new Quantifier();
		quantifier1.setType(QuantifierType.ALL);
		quantifier1.setBoundVariable(Variable.l);
		ComplexRightPart rightPart = new ComplexRightPart();
		rightPart.getBoundVariables().add(Variable.f);
		rightPart.getNestedMappings().add(new LifeLineFromInteraction());
		quantifier1.setRightPart(rightPart);
		getQuantifiers().add(quantifier1);

		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.f);
		boundedPredicate.getBoundVariable().add(Variable.l);
		boundedPredicate.setPredicate(new IsExecutableLifeLine());
		setFormula(boundedPredicate);
	}

	@Override
	public int getInternalID() {
		return -8;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.SEQUENCE_STATE;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		return new ExecutableSequenceTemplate();
	}

}
