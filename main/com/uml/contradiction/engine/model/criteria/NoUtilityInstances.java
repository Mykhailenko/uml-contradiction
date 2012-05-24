package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.NoUtilityInstanceTemplate;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.ClassObject;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.Brackets;
import com.uml.contradiction.engine.model.predicate.Brackets.OperationType;
import com.uml.contradiction.engine.model.predicate.Formula;
import com.uml.contradiction.engine.model.predicate.IsUtility;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPObjects;

public class NoUtilityInstances extends Criterion {

	public NoUtilityInstances() {
		super();

		Quantifier quantifier = new Quantifier();
		quantifier.setType(QuantifierType.ALL);
		quantifier.setBoundVariable(Variable.o);
		quantifier.setRightPart(new RPObjects());
		getQuantifiers().add(quantifier);

		Quantifier quantifier1 = new Quantifier();
		quantifier1.setType(QuantifierType.ALL);
		quantifier1.setBoundVariable(Variable.c);
		ComplexRightPart rightPart = new ComplexRightPart();
		rightPart.getBoundVariables().add(Variable.o);
		rightPart.getNestedMappings().add(new ClassObject());
		quantifier1.setRightPart(rightPart);
		getQuantifiers().add(quantifier1);

		Brackets br = new Brackets();
		br.setType(OperationType.OR);

		BoundedPredicate pr1 = new BoundedPredicate();
		pr1.setPredicate(new IsUtility());
		pr1.getBoundVariable().add(Variable.c);
		pr1.setNegative(true);

		List<Formula> predicates = new LinkedList<Formula>();
		predicates.add(pr1);
		br.setFormulas(predicates);

		this.setFormula(br);
	}

	@Override
	public int getInternalID() {
		return -12;
	}

	@Override
	public CriterionType getCriterionType() {
		// TODO Auto-generated method stub
		return CriterionType.CLASS_OBJECT;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		// TODO Auto-generated method stub
		return new NoUtilityInstanceTemplate();
	}

}
