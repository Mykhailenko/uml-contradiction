package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.BoundedPredicate;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.mapping.ClassAssociationMapping;
import com.uml.contradiction.engine.model.mapping.CompositeEndAssociationMapping;
import com.uml.contradiction.engine.model.mapping.EndAssociationMapping;
import com.uml.contradiction.engine.model.predicate.PredicateCorrectCompositeMultiplicity;
import com.uml.contradiction.engine.model.predicate.PredicateNoAggregation;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPCompositions;
import com.uml.contradiction.engine.model.rightPart.simple.RPNaries;

public class NaryNoAggregationOnly extends Criterion {

	public NaryNoAggregationOnly() {
		super();
		
		Quantifier quantifier = new Quantifier();
		quantifier.setType(QuantifierType.ALL);
		quantifier.setBoundVariable(Variable.c);
		quantifier.setRightPart(new RPNaries());
		getQuantifiers().add(quantifier);
		
		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALL);
		quantifier2.setBoundVariable(Variable.a);
		ComplexRightPart rightPart = new ComplexRightPart();
		rightPart.getBoundVariables().add(Variable.c);
		rightPart.getNestedMappings().add(new ClassAssociationMapping());
		quantifier2.setRightPart(rightPart);
		getQuantifiers().add(quantifier2);		

		Quantifier quantifier3 = new Quantifier();
		quantifier3.setType(QuantifierType.ALL);
		quantifier3.setBoundVariable(Variable.e);
		ComplexRightPart rightPart3 = new ComplexRightPart();
		rightPart3.getBoundVariables().add(Variable.a);
		rightPart3.getNestedMappings().add(new EndAssociationMapping());
		quantifier3.setRightPart(rightPart3);
		getQuantifiers().add(quantifier3);		
		
		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.e);
		boundedPredicate.setNegative(false);
		boundedPredicate.setPredicate(new PredicateNoAggregation());
		getBoundedPredicates().add(boundedPredicate);	
	
	}	
	
	
	
	@Override
	public int getInternalID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CriterionType getCriterionType() {
		// TODO Auto-generated method stub
		return CriterionType.CLASS_CLASS;
	}

}
