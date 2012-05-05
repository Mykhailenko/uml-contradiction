package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.CheckTriggersTemplate;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.ClassOfState;
import com.uml.contradiction.engine.model.mapping.TransitionOfStateMachine;
import com.uml.contradiction.engine.model.mapping.TriggersOfState;
import com.uml.contradiction.engine.model.mapping.TriggersOfTransition;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.Brackets;
import com.uml.contradiction.engine.model.predicate.TransitionBelongToClass;
import com.uml.contradiction.engine.model.predicate.TransitionFromInit;
import com.uml.contradiction.engine.model.predicate.TriggerBelongToClass;
import com.uml.contradiction.engine.model.predicate.Brackets.OperationType;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPStateMachines;

public class CheckTriggers extends Criterion{
	public CheckTriggers() {
		super();
		
		Quantifier quantifier0 = new Quantifier();
		quantifier0.setType(QuantifierType.ALL);
		quantifier0.setBoundVariable(Variable.s);
		quantifier0.setRightPart(new RPStateMachines());
		getQuantifiers().add(quantifier0);
		
		Quantifier quantifier1 = new Quantifier();
		quantifier1.setType(QuantifierType.ALONE);
		quantifier1.setBoundVariable(Variable.c);
		ComplexRightPart rightPart1 = new ComplexRightPart();
		rightPart1.getBoundVariables().add(Variable.s);
		rightPart1.getNestedMappings().add(new ClassOfState());
		quantifier1.setRightPart(rightPart1);
		getQuantifiers().add(quantifier1);
		
		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALL);
		quantifier2.setBoundVariable(Variable.l);
		ComplexRightPart rightPart2 = new ComplexRightPart();
		rightPart2.getBoundVariables().add(Variable.s);
		rightPart2.getNestedMappings().add(new TransitionOfStateMachine());
		quantifier2.setRightPart(rightPart2);
		getQuantifiers().add(quantifier2);
		
		Quantifier quantifier3 = new Quantifier();
		quantifier3.setType(QuantifierType.ALL);
		quantifier3.setBoundVariable(Variable.t);
		ComplexRightPart rightPart3 = new ComplexRightPart();
		rightPart3.getBoundVariables().add(Variable.l);
		rightPart3.getNestedMappings().add(new TriggersOfTransition());
		quantifier3.setRightPart(rightPart3);
		getQuantifiers().add(quantifier3);
		
		Brackets brackets = new Brackets();
		brackets.setType(OperationType.OR);
		
		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.t);
		boundedPredicate.getBoundVariable().add(Variable.c);
		boundedPredicate.setPredicate(new TriggerBelongToClass());
		brackets.getFormulas().add(boundedPredicate);
		
		BoundedPredicate boundedPredicate2 = new BoundedPredicate();
		boundedPredicate2.getBoundVariable().add(Variable.l);
		boundedPredicate2.getBoundVariable().add(Variable.c);
		boundedPredicate2.setPredicate(new TransitionBelongToClass());
		brackets.getFormulas().add(boundedPredicate2);
		
		BoundedPredicate boundedPredicate3 = new BoundedPredicate();
		boundedPredicate3.getBoundVariable().add(Variable.l);
		boundedPredicate3.setPredicate(new TransitionFromInit());
		brackets.getFormulas().add(boundedPredicate3);
		setFormula(brackets);
	}
	
	
	@Override
	public int getInternalID() {
		return -5;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_STATE;
	}


	@Override
	public ResultTemplate getResultTemplate() {
		return new CheckTriggersTemplate();
	}
	
}
