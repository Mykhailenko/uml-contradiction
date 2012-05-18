package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.Mapping;
import com.uml.contradiction.engine.model.mapping.MethodClass;
import com.uml.contradiction.engine.model.mapping.TargetMessage;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.Brackets;
import com.uml.contradiction.engine.model.predicate.Brackets.OperationType;
import com.uml.contradiction.engine.model.predicate.MessageMethodEqualName;
import com.uml.contradiction.engine.model.predicate.MessageMethodVisibility;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPMessages;

public class CheckVisibilityMethod extends Criterion {
	public CheckVisibilityMethod() {
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
		nestedMappings.add(new MethodClass());
		nestedMappings.add(new TargetMessage());
		rightPart.setNestedMappings(nestedMappings);
		quantifier1.setRightPart(rightPart);
		getQuantifiers().add(quantifier1);
		Brackets brackets = new Brackets();
		brackets.setType(OperationType.AND);
		BoundedPredicate boundedPredicate0 = new BoundedPredicate();
		boundedPredicate0.getBoundVariable().add(Variable.m);
		boundedPredicate0.getBoundVariable().add(Variable.o);
		boundedPredicate0.setPredicate(new MessageMethodEqualName());
		brackets.getFormulas().add(boundedPredicate0);

		BoundedPredicate boundedPredicate1 = new BoundedPredicate();
		boundedPredicate1.getBoundVariable().add(Variable.m);
		boundedPredicate1.getBoundVariable().add(Variable.o);
		boundedPredicate1.setPredicate(new MessageMethodVisibility());
		brackets.getFormulas().add(boundedPredicate1);
		setFormula(brackets);
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_SEQUENCE;
	}

	@Override
	public int getInternalID() {
		return -6;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		// TODO Auto-generated method stub
		return null;
	}
}
