package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.mapping.ClassOfLifeLine;
import com.uml.contradiction.engine.model.mapping.LifeLineFromInteraction;
import com.uml.contradiction.engine.model.mapping.MessagesOfLifeLineInteraction;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.MessageBelongToClass;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPInteraction;

public class CheckSequenceCriterion extends Criterion {
	public CheckSequenceCriterion() {
		super();
	
		Quantifier quantifier0 = new Quantifier();
		quantifier0.setType(QuantifierType.ALL);
		quantifier0.setBoundVariable(Variable.i);
		quantifier0.setRightPart(new RPInteraction());
		getQuantifiers().add(quantifier0);
		
		Quantifier quantifier1 = new Quantifier();
		quantifier1.setType(QuantifierType.ALL);
		quantifier1.setBoundVariable(Variable.l);
		ComplexRightPart rightPart1 = new ComplexRightPart();
		rightPart1.getBoundVariables().add(Variable.i);
		rightPart1.getNestedMappings().add(new LifeLineFromInteraction());
		quantifier1.setRightPart(rightPart1);
		getQuantifiers().add(quantifier1);
		
		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALONE);
		quantifier2.setBoundVariable(Variable.c);
		ComplexRightPart rightPart2 = new ComplexRightPart();
		rightPart2.getBoundVariables().add(Variable.l);
		rightPart2.getNestedMappings().add(new ClassOfLifeLine());
		quantifier2.setRightPart(rightPart2);
		getQuantifiers().add(quantifier2);
		
		Quantifier quantifier3 = new Quantifier();
		quantifier3.setType(QuantifierType.ALL);
		quantifier3.setBoundVariable(Variable.m);
		ComplexRightPart rightPart3 = new ComplexRightPart();
		rightPart3.getBoundVariables().add(Variable.l);
		rightPart3.getBoundVariables().add(Variable.i);
		rightPart3.getNestedMappings().add(new MessagesOfLifeLineInteraction());
		quantifier3.setRightPart(rightPart3);
		getQuantifiers().add(quantifier3);
		
		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.setPredicate(new MessageBelongToClass());
		boundedPredicate.getBoundVariable().add(Variable.m);
		boundedPredicate.getBoundVariable().add(Variable.c);
		setFormula(boundedPredicate);
		
	}
		
	@Override
	public int getInternalID() {
		return -2;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.OBJECT_SEQUENCE;
	}

}
