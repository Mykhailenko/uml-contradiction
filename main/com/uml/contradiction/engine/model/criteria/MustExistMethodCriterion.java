package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.BoundedPredicate;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.mapping.Mapping;
import com.uml.contradiction.engine.model.mapping.MethodClassMapping;
import com.uml.contradiction.engine.model.mapping.TargetMessageMapping;
import com.uml.contradiction.engine.model.predicate.MessageBelongToMethod;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.QuantifierRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPMessages;

public class MustExistMethodCriterion extends Criterion {
	public MustExistMethodCriterion() {
		super();
		Quantifier quantifier0 = new Quantifier();
		quantifier0.setType(QuantifierType.ALL);
		quantifier0.setBoundVariable(Variable.m);
		quantifier0.setRightPart(new RPMessages());
		getQuantifiers().add(quantifier0);
		
		Quantifier quantifier1 = new Quantifier();
		quantifier1.setType(QuantifierType.ALONE);
		quantifier1.setBoundVariable(Variable.o);
		ComplexRightPart rightPart = new ComplexRightPart();
		rightPart.getBoundVariables().add(Variable.m);
		List<Mapping> nestedMappings = new LinkedList<Mapping>();
		nestedMappings.add(new MethodClassMapping());
		nestedMappings.add(new TargetMessageMapping());
		rightPart.setNestedMappings(nestedMappings);
		quantifier1.setRightPart(rightPart);
		
		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.m);
		boundedPredicate.getBoundVariable().add(Variable.o);
		boundedPredicate.setPredicate(new MessageBelongToMethod());
		getBoundedPredicates().add(boundedPredicate);
	}
	
	
	@Override
	public int getInternalID() {
		return -2;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_SEQUENCE;
	}

}
