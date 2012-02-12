package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.BoundedPredicate;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.mapping.ClassOfStateMachine;
import com.uml.contradiction.engine.model.mapping.TriggersOfStateMachine;
import com.uml.contradiction.engine.model.predicate.TriggerBelongToClass;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPStateMachines;

public class CheckStateMachineCriterion extends Criterion{
	public CheckStateMachineCriterion() {
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
		rightPart1.getNestedMappings().add(new ClassOfStateMachine());
		quantifier1.setRightPart(rightPart1);
		getQuantifiers().add(quantifier1);
		
		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALL);
		quantifier2.setBoundVariable(Variable.t);
		ComplexRightPart rightPart2 = new ComplexRightPart();
		rightPart2.getBoundVariables().add(Variable.s);
		rightPart2.getNestedMappings().add(new TriggersOfStateMachine());
		quantifier2.setRightPart(rightPart2);
		getQuantifiers().add(quantifier2);
		
		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.t);
		boundedPredicate.getBoundVariable().add(Variable.c);
		boundedPredicate.setPredicate(new TriggerBelongToClass());
		getBoundedPredicates().add(boundedPredicate);
	}
	
	
	@Override
	public int getInternalID() {
		return -2;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_STATE;
	}
	
}
