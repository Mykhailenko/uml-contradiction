package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.criteria.result.UtilStaticAttrTemplate;
import com.uml.contradiction.engine.model.mapping.AttributeClass;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.Brackets;
import com.uml.contradiction.engine.model.predicate.Brackets.OperationType;
import com.uml.contradiction.engine.model.predicate.Formula;
import com.uml.contradiction.engine.model.predicate.IsStaticAttribute;
import com.uml.contradiction.engine.model.predicate.IsUtility;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPClasses;

public class UtilityStaticAttrOnly extends Criterion {
	public UtilityStaticAttrOnly() {
		super();

		Quantifier quantifier = new Quantifier();
		quantifier.setType(QuantifierType.ALL);
		quantifier.setBoundVariable(Variable.c);
		quantifier.setRightPart(new RPClasses());
		getQuantifiers().add(quantifier);

		Quantifier quantifier1 = new Quantifier();
		quantifier1.setType(QuantifierType.ALL);
		quantifier1.setBoundVariable(Variable.a);
		ComplexRightPart rightPart = new ComplexRightPart();
		rightPart.getBoundVariables().add(Variable.c);
		rightPart.getNestedMappings().add(new AttributeClass());
		quantifier1.setRightPart(rightPart);
		getQuantifiers().add(quantifier1);

		Brackets mainBr = new Brackets();
		mainBr.setType(OperationType.OR);

		BoundedPredicate pr1 = new BoundedPredicate();
		pr1.setPredicate(new IsUtility());
		pr1.getBoundVariable().add(Variable.c);
		pr1.setNegative(true);

		BoundedPredicate pr2 = new BoundedPredicate();
		pr2.setPredicate(new IsStaticAttribute());
		pr2.getBoundVariable().add(Variable.a);
		pr2.getPermittedNullVars().add(Variable.a);

		List<Formula> formulas = new LinkedList<Formula>();
		formulas.add(pr1);
		formulas.add(pr2);
		mainBr.setFormulas(formulas);
		this.setFormula(mainBr);
	}

	@Override
	public int getInternalID() {
		return -15;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_CLASS;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		// TODO Auto-generated method stub
		return new UtilStaticAttrTemplate();
	}

}
