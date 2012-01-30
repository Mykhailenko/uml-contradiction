package com.uml.contradiction.tests;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.uml.contradiction.engine.Engine;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.MustExistClassCriterion;
import com.uml.contradiction.engine.model.criteria.SimpleCriterion;
import com.uml.contradiction.engine.model.diagram.ClassDiagram;
import com.uml.contradiction.engine.model.diagram.ObjectDiagram;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.OObject;

public class MustExistClassCriterionTest {
	private static final Logger LOGGER = Logger.getRootLogger();
	@Test
	public void failMustExistTest(){
		////// good case
		CClass cClass = new CClass();
		cClass.setName("Student");
		List<CClass> classes = new LinkedList<CClass>();
		classes.add(cClass);
		ClassDiagram.setClasses(classes);
		
		OObject object0 = new OObject();
		object0.setName("goodovo");
		List<String> c0 = new LinkedList<String>();
		c0.add("Student");
		object0.setClasses(c0);
		OObject object1 = new OObject();
		object1.setName("badovo");
		List<String> c1 = new LinkedList<String>();
		c1.add("Student!");
		object1.setClasses(c1);

		List<OObject> loo = new LinkedList<OObject>();
		loo.add(object0);
		loo.add(object1);
		ObjectDiagram.setObjects(loo);
		
		/////
		Criterion criterion = new MustExistClassCriterion();
		Engine engine = new Engine(criterion);
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