package com.uml.contradiction.tests;

import java.io.File;

import org.junit.Test;

import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.engine.Engine;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.MustExistMethodCriterion;
import static org.junit.Assert.assertTrue;
public class MustExistMethodCriterionTest {
	@Test
	public void test(){
		File file = new File("g.uml");
		
		XMIConverter.reset();
		XMIConverter.setFile(file);
		
		try {
			XMIConverter.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Criterion criterion = new MustExistMethodCriterion();
		Engine engine = new Engine(criterion);
		VerificationResult result = engine.verify();
		assertTrue(result.isGood());
	}
}
