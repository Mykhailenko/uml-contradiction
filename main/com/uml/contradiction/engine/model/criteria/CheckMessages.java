package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.CheckMessagesTemplate;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.ClassOfLifeLine;
import com.uml.contradiction.engine.model.mapping.LifeLineFromInteraction;
import com.uml.contradiction.engine.model.mapping.MessagesToLifeLine;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.MessageBelongToClass;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPInteractions;

public class CheckMessages extends Criterion {
	public CheckMessages() {
		super();
		//////////// ололо проверка комментов
		Quantifier quantifier0 = new Quantifier();
		quantifier0.setType(QuantifierType.ALL);
		quantifier0.setBoundVariable(Variable.i);
		quantifier0.setRightPart(new RPInteractions());
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
		quantifier2.setType(QuantifierType.ALL);
		quantifier2.setBoundVariable(Variable.m);
		ComplexRightPart rightPart3 = new ComplexRightPart();
		rightPart3.getBoundVariables().add(Variable.l);
		rightPart3.getBoundVariables().add(Variable.i);
		rightPart3.getNestedMappings().add(new MessagesToLifeLine());
		quantifier2.setRightPart(rightPart3);
		getQuantifiers().add(quantifier2);

		Quantifier quantifier3 = new Quantifier();
		quantifier3.setType(QuantifierType.ALONE);
		quantifier3.setBoundVariable(Variable.c);
		ComplexRightPart rightPart2 = new ComplexRightPart();
		rightPart2.getBoundVariables().add(Variable.l);
		rightPart2.getNestedMappings().add(new ClassOfLifeLine());
		quantifier3.setRightPart(rightPart2);
		getQuantifiers().add(quantifier3);

		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.setPredicate(new MessageBelongToClass());
		boundedPredicate.getPermittedNullVars().add(Variable.m);
		boundedPredicate.getPermittedNullVars().add(Variable.c);
		boundedPredicate.getBoundVariable().add(Variable.m);
		boundedPredicate.getBoundVariable().add(Variable.c);
		setFormula(boundedPredicate);
		
	}

	@Override
	public int getInternalID() {
		return -4;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_SEQUENCE;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		return new CheckMessagesTemplate();
	}

	@Override
	public int trickyMethod() {
		return 3;
	}
}
