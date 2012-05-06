	package com.uml.contradiction.tests;

import java.io.File;

import org.junit.Test;

import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.engine.Checker;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.CheckTriggers;
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
		Criterion criterion = new CheckTriggers();
		Checker engine = new Checker(criterion);
		VerificationResult result = engine.verify();
		
		System.out.println("res= "+ result.isGood() + "his=" + result.getFailHistory().size());
		assertTrue(result.isFail());
	}
}
