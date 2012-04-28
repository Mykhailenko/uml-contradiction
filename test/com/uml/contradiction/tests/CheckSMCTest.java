	package com.uml.contradiction.tests;

import java.io.File;

import org.junit.Test;

import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.engine.Engine;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.CheckStateMachineCriterion;
import com.uml.contradiction.engine.model.criteria.Criterion;
import static org.junit.Assert.assertTrue;
public class CheckSMCTest {
	@Test
	public void doit(){
		File file = new File("oa.uml");
		XMIConverter.reset();
		XMIConverter.setFile(file);
		try {
			XMIConverter.parse();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("Fuckkkkkkkkkkkkkkkkkkkkk\n\n");
		}
		Criterion criterion = new CheckStateMachineCriterion();
		Engine engine = new Engine(criterion);
		VerificationResult result = engine.verify();
		assertTrue(result.isFail());
	}
}
