package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.CompositeMultOneOnlyTemplate;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.Brackets;
import com.uml.contradiction.engine.model.predicate.Brackets.OperationType;
import com.uml.contradiction.engine.model.predicate.CompMultCorrect;
import com.uml.contradiction.engine.model.predicate.Formula;
import com.uml.contradiction.engine.model.predicate.IsComposition;
import com.uml.contradiction.engine.model.rightPart.simple.RPAssosiations;

public class CompositeMultOneOnly extends Criterion {

	public CompositeMultOneOnly() {
		super();

		Quantifier quantifier = new Quantifier();
		quantifier.setType(QuantifierType.ALL);
		quantifier.setBoundVariable(Variable.a);
		quantifier.setRightPart(new RPAssosiations());
		getQuantifiers().add(quantifier);

		Brackets br = new Brackets();
		br.setType(OperationType.OR);

		BoundedPredicate pr1 = new BoundedPredicate();
		pr1.setPredicate(new IsComposition());
		pr1.getBoundVariable().add(Variable.a);
		pr1.setNegative(true);

		BoundedPredicate pr2 = new BoundedPredicate();
		pr2.setPredicate(new CompMultCorrect());
		pr2.getBoundVariable().add(Variable.a);
		pr2.setNegative(false);

		List<Formula> predicates = new LinkedList<Formula>();
		predicates.add(pr1);
		predicates.add(pr2);
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
		return CriterionType.CLASS_CLASS;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		// TODO Auto-generated method stub
		return new CompositeMultOneOnlyTemplate();
	}

}
