package com.uml.contradiction.engine.model.criteria;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;
import com.uml.contradiction.engine.model.mapping.ATR;
import com.uml.contradiction.engine.model.mapping.GenSeq;
import com.uml.contradiction.engine.model.mapping.Ve;
import com.uml.contradiction.engine.model.mapping.Vs;
import com.uml.contradiction.engine.model.predicate.BoundedPredicate;
import com.uml.contradiction.engine.model.predicate.Brackets;
import com.uml.contradiction.engine.model.predicate.Brackets.OperationType;
import com.uml.contradiction.engine.model.rightPart.ComplexRightPart;
import com.uml.contradiction.engine.model.rightPart.simple.RPAssosiations;
import com.uml.contradiction.engine.model.rightPart.simple.RPOblecjLink;

public class CheckAttributeOrAssociate extends Criterion {
	public CheckAttributeOrAssociate() {
		Quantifier quantifier0 = new Quantifier();
		quantifier0.setType(QuantifierType.ALL);
		quantifier0.setBoundVariable(Variable.l);
		quantifier0.setRightPart(new RPOblecjLink());
		getQuantifiers().add(quantifier0);

		Quantifier quantifier1 = new Quantifier();
		quantifier1.setType(QuantifierType.EXIST);
		quantifier1.setBoundVariable(Variable.v);
		ComplexRightPart rightPart1 = new ComplexRightPart();
		rightPart1.getBoundVariables().add(Variable.l);
		rightPart1.getNestedMappings().add(new GenSeq());
		rightPart1.getNestedMappings().add(new Vs());
		quantifier1.setRightPart(rightPart1);
		getQuantifiers().add(quantifier1);

		Quantifier quantifier2 = new Quantifier();
		quantifier2.setType(QuantifierType.EXIST);
		quantifier2.setBoundVariable(Variable.u);
		ComplexRightPart rightPart2 = new ComplexRightPart();
		rightPart2.getBoundVariables().add(Variable.l);
		rightPart2.getNestedMappings().add(new GenSeq());
		rightPart2.getNestedMappings().add(new Ve());
		quantifier2.setRightPart(rightPart2);
		getQuantifiers().add(quantifier2);

		Quantifier quantifier3 = new Quantifier();
		quantifier3.setType(QuantifierType.EXIST);
		quantifier3.setBoundVariable(Variable.a);
		quantifier3.setRightPart(new RPAssosiations());
		getQuantifiers().add(quantifier3);

		Quantifier quantifier4 = new Quantifier();
		quantifier4.setType(QuantifierType.EXIST);
		quantifier4.setBoundVariable(Variable.r);
		ComplexRightPart rightPart4 = new ComplexRightPart();
		rightPart4.getBoundVariables().add(Variable.v);
		rightPart4.getNestedMappings().add(new ATR());
		quantifier4.setRightPart(rightPart4);
		getQuantifiers().add(quantifier4);

		Quantifier quantifier5 = new Quantifier();
		quantifier5.setType(QuantifierType.EXIST);
		quantifier5.setBoundVariable(Variable.t);
		ComplexRightPart rightPart5 = new ComplexRightPart();
		rightPart5.getBoundVariables().add(Variable.u);
		rightPart5.getNestedMappings().add(new ATR());
		quantifier5.setRightPart(rightPart5);
		getQuantifiers().add(quantifier5);

		Brackets brackets = new Brackets();
		brackets.setType(OperationType.OR);

		Brackets brackets1 = new Brackets();
		brackets1.setType(OperationType.AND);
		// ////
		brackets.getFormulas().add(brackets1);

		Brackets brackets2 = new Brackets();
		brackets2.setType(OperationType.AND);
		// //////
		brackets.getFormulas().add(brackets2);

		BoundedPredicate boundedPredicate3 = new BoundedPredicate();
		// ////
		brackets.getFormulas().add(boundedPredicate3);

		BoundedPredicate boundedPredicate4 = new BoundedPredicate();
		// ////
		brackets.getFormulas().add(boundedPredicate4);

		setFormula(brackets);
	}

	@Override
	public int getInternalID() {
		return -1;
	}

	@Override
	public CriterionType getCriterionType() {
		return CriterionType.CLASS_OBJECT;
	}

	@Override
	public ResultTemplate getResultTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

}
