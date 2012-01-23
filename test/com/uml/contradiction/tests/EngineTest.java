package com.uml.contradiction.tests;

import org.junit.Test;

import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.criteria.Criterion;

import static org.junit.Assert.*;

public class EngineTest {
	@Test
	public void stupidContradictionTest(){
		Criterion criterion = new Criterion();
		Quantifier q0 = new Quantifier();
		q0.setType(QuantifierType.ALL);
		q0.setBoundVariable(Variable.o);
//		criterion.getQuantifiers().add(e)
	}
}
