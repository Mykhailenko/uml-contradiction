package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.CorrectLifeLinesTemplate;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.LifeLineFromInteraction;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.CorrectLifeLine;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPInteractions;

public class CorrectLifeLines extends Criterion {
	public CorrectLifeLines() {
		super();

		getQuantifiers().add(
				new Quantifier().setType(QuantifierType.ALL)
						.setBoundVariable(Variable.i)
						.setRightPart(new RPInteractions()));

		ComplexRightPart rightPart = new ComplexRightPart();
		rightPart.setBoundVariables(Variable.i);
		rightPart.getNestedMappings().add(new LifeLineFromInteraction());
		getQuantifiers().add(
				new Quantifier().setType(QuantifierType.ALL)
						.setBoundVariable(Variable.l).setRightPart(rightPart));

		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.setBoundVariable(Variable.l);
		boundedPredicate.setPredicate(new CorrectLifeLine());
		setFormula(boundedPredicate);
	}

	@Override
	public int getInternalID() {
		return -17;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_SEQUENCE;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		return new CorrectLifeLinesTemplate();
	}

}
