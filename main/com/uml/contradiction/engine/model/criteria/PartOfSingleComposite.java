package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.BoundedPredicate;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.mapping.ClassObjectMapping;
import com.uml.contradiction.engine.model.mapping.CompositeEndAssociationMapping;
import com.uml.contradiction.engine.model.predicate.PredicateCorrectCompositeMultiplicity;
import com.uml.contradiction.engine.model.predicate.PredicateIsEqualName;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPCompositions;
import com.uml.contradiction.engine.model.rightPart.simple.RPObjects;

public class PartOfSingleComposite extends Criterion {
	private List<Quantifier> quantifiers = new LinkedList<Quantifier>();
	private List<BoundedPredicate> boundedPredicates = new LinkedList<BoundedPredicate>();
	
	public PartOfSingleComposite() {
		super();
		
		Quantifier quantifier = new Quantifier();
		quantifier.setType(QuantifierType.ALL);
		quantifier.setBoundVariable(Variable.a);
		quantifier.setRightPart(new RPCompositions());
		getQuantifiers().add(quantifier);
		
		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.ALL);
		quantifier2.setBoundVariable(Variable.e);
		ComplexRightPart rightPart = new ComplexRightPart();
		rightPart.getBoundVariables().add(Variable.a);
		rightPart.getNestedMappings().add(new CompositeEndAssociationMapping());
		quantifier2.setRightPart(rightPart);
		getQuantifiers().add(quantifier2);		
	
		BoundedPredicate boundedPredicate = new BoundedPredicate();
		boundedPredicate.getBoundVariable().add(Variable.e);
		boundedPredicate.setNegative(false);
		boundedPredicate.setPredicate(new PredicateCorrectCompositeMultiplicity());
		getBoundedPredicates().add(boundedPredicate);	
	
	}
	
	@Override
	public int getInternalID() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public CriterionType getCriterionType() {
		// TODO Auto-generated method stub
		return CriterionType.CLASS_CLASS;
	}

}
