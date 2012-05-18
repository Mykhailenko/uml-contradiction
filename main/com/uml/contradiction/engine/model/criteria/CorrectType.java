package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.CorrectTypeTemplate;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.AttributeClass;
import com.uml.contradiction.engine.model.mapping.AttributeObject;
import com.uml.contradiction.engine.model.mapping.ClassObject;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.Brackets;
import com.uml.contradiction.engine.model.predicate.Brackets.OperationType;
import com.uml.contradiction.engine.model.predicate.IsEqualName;
import com.uml.contradiction.engine.model.predicate.ValueCorrectType;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPObjects;

public class CorrectType extends Criterion {
	public CorrectType() {
		super();
		Quantifier quantifier0 = new Quantifier();
		quantifier0.setType(QuantifierType.ALL);
		quantifier0.setBoundVariable(Variable.o);
		quantifier0.setRightPart(new RPObjects());
		getQuantifiers().add(quantifier0);

		Quantifier quantifier1 = new Quantifier();
		quantifier1.setType(QuantifierType.ALL);
		quantifier1.setBoundVariable(Variable.a);
		ComplexRightPart complexRightPart1 = new ComplexRightPart();
		complexRightPart1.getBoundVariables().add(Variable.o);
		complexRightPart1.getNestedMappings().add(new AttributeObject());
		quantifier1.setRightPart(complexRightPart1);
		getQuantifiers().add(quantifier1);

		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALONE);
		quantifier2.setBoundVariable(Variable.c);
		ComplexRightPart complexRightPart2 = new ComplexRightPart();
		complexRightPart2.getBoundVariables().add(Variable.o);
		complexRightPart2.getNestedMappings().add(new AttributeClass());
		complexRightPart2.getNestedMappings().add(new ClassObject());
		quantifier2.setRightPart(complexRightPart2);
		getQuantifiers().add(quantifier2);
		Brackets brackets = new Brackets();
		brackets.setType(OperationType.AND);

		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.a);
		boundedPredicate.getBoundVariable().add(Variable.c);
		boundedPredicate.setPredicate(new IsEqualName());
		brackets.getFormulas().add(boundedPredicate);

		BoundedPredicate boundedPredicate2 = new BoundedPredicate();
		boundedPredicate2.getBoundVariable().add(Variable.a);
		boundedPredicate2.getBoundVariable().add(Variable.c);
		boundedPredicate2.setPredicate(new ValueCorrectType());
		brackets.getFormulas().add(boundedPredicate2);
		setFormula(brackets);
	}

	@Override
	public int getInternalID() {
		return -7;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_OBJECT;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		return new CorrectTypeTemplate();
	}

	@Override
	public int trickyMethod() {
		return 2;
	}
}
