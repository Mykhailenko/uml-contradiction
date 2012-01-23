package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.BoundedPredicate;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.mapping.AttributeClassMapping;
import com.uml.contradiction.engine.model.mapping.AttributeObjectMapping;
import com.uml.contradiction.engine.model.mapping.ClassObjectMapping;
import com.uml.contradiction.engine.model.predicate.PredicateIsEqualName;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.RPObjects;

public class SimpleCriterion extends Criterion {
	public SimpleCriterion() {
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
		complexRightPart1.setBoundVariable(Variable.o);
		complexRightPart1.getNestedMappings().add(new AttributeObjectMapping());
		quantifier1.setRightPart(complexRightPart1);
		getQuantifiers().add(quantifier1);
		
		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALONE);
		quantifier2.setBoundVariable(Variable.c);
		ComplexRightPart complexRightPart2 = new ComplexRightPart();
		complexRightPart2.setBoundVariable(Variable.o);
		complexRightPart2.getNestedMappings().add(new AttributeClassMapping());
		complexRightPart2.getNestedMappings().add(new ClassObjectMapping());
		quantifier2.setRightPart(complexRightPart2);
		getQuantifiers().add(quantifier2);
		
		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.a);
		boundedPredicate.getBoundVariable().add(Variable.c);
		boundedPredicate.setNegative(false);
		boundedPredicate.setPredicate(new PredicateIsEqualName());
		getBoundedPredicates().add(boundedPredicate);
		
	}
}

