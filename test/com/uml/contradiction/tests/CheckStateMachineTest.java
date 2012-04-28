package com.uml.contradiction.tests;

import java.io.File;

import org.junit.Test;

import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.engine.Checker;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.CheckStateMachineCriterion;
import com.uml.contradiction.engine.model.criteria.Criterion;
import static org.junit.Assert.assertTrue;

public class CheckStateMachineTest {
	@Test
	public void test(){
		File file = new File("uno.uml");
		XMIConverter.reset();
		XMIConverter.setFile(file);
		try {
			XMIConverter.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Criterion criterion = new CheckStateMachineCriterion();
		Checker engine = new Checker(criterion);
		VerificationResult result = engine.verify();
		assertTrue(result.isFail());
	}
}
