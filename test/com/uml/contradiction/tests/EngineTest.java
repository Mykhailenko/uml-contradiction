package com.uml.contradiction.tests;

import org.junit.Test;

import com.uml.contradiction.engine.Engine;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.QuantifierType;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.SimpleCriterion;

import static org.junit.Assert.*;

public class EngineTest {
	@Test
	public void goodContradictionTest(){
		////// good case
		
		/////
		SimpleCriterion simpleCriterion = new SimpleCriterion();
		Engine engine = new Engine(simpleCriterion);
		VerificationResult verificationResult = engine.verify();
		assertTrue(verificationResult.isGood());
	}
	
	@Test
	public void failContradictionTest(){
		////// good case
		
		/////
		SimpleCriterion simpleCriterion = new SimpleCriterion();
		Engine engine = new Engine(simpleCriterion);
		VerificationResult verificationResult = engine.verify();
		assertTrue(verificationResult.isFail());
	}
}
