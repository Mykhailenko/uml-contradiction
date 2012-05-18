package com.uml.contradiction.tests;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.uml.contradiction.engine.Checker;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.MustExistClassCriterion;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.object.OObject;
import com.uml.contradiction.model.object.ObjectGraph;

public class MustExistClassCriterionTest {
	private static final Logger LOGGER = Logger.getRootLogger();
	@Test
	public void failMustExistTest(){
		////// good case
		CClass cClass = new CClass();
		cClass.setName("Student");
		List<CClass> classes = new LinkedList<CClass>();
		classes.add(cClass);
		ClassGraph.setClasses(classes);
		
		OObject object0 = new OObject();
		object0.setName("goodovo");
		object0.setClasses(cClass);
		OObject object1 = new OObject();
		object1.setName("badovo");
		object1.setClasses(cClass);

		List<OObject> loo = new LinkedList<OObject>();
		loo.add(object0);
		loo.add(object1);
		ObjectGraph.setObjects(loo);
		
		/////
		Criterion criterion = new MustExistClassCriterion();
		Checker engine = new Checker(criterion);
		VerificationResult verificationResult = engine.verify();
		assert verificationResult != null : "verificationResult == null";
		LOGGER.info("isFail = " + verificationResult.isFail());
		assertTrue(verificationResult.isFail());
		for(HistoryPlainItem plain : verificationResult.getFailHistory()){
			try {
				String slog = new String();
				for(int i = 0; i < plain.getItems().size(); ++i){
					slog += plain.getItems().get(i).variable + " = " + plain.getItems().get(i).value.getClass().getMethod("getName").invoke(plain.getItems().get(i).value) + ";";
				}
				LOGGER.info(slog);
			} catch (Exception e) {	}
		}
		LOGGER.info("test really Completed");
	}
}
