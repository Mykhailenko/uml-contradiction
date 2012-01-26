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
import com.uml.contradiction.engine.model.criteria.CorrectTypeCriterion;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.SimpleCriterion;
import com.uml.contradiction.engine.model.diagram.ClassDiagram;
import com.uml.contradiction.engine.model.diagram.ObjectDiagram;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.common.Type;
import com.uml.contradiction.model.common.UMLType;
import com.uml.contradiction.model.object.OObject;

public class CorrectTypeCriterionTest {
	private static final Logger LOGGER = Logger.getRootLogger();
	@Test
	public void goodTest(){
		////// good case
		CClass cClass = new CClass();
		cClass.setName("Student");
		List<com.uml.contradiction.model.cclass.Attribute> lac = new LinkedList<com.uml.contradiction.model.cclass.Attribute>();
		com.uml.contradiction.model.cclass.Attribute newAttrc = new com.uml.contradiction.model.cclass.Attribute();
		newAttrc.setName("sex");
		newAttrc.setType(UMLType.BOOLEAN);
		lac.add(newAttrc);
		com.uml.contradiction.model.cclass.Attribute newAttrc2 = new com.uml.contradiction.model.cclass.Attribute();
		newAttrc2.setName("crab");
		newAttrc2.setType(UMLType.INTEGER);
		lac.add(newAttrc2);
		cClass.setAttributes(lac);
		List<CClass> classes = new LinkedList<CClass>();
		classes.add(cClass);
		ClassDiagram.setClasses(classes);
		
		OObject object0 = new OObject();
		List<String> c0 = new LinkedList<String>();
		c0.add("Student");
		object0.setClasses(c0);
		
		List<com.uml.contradiction.model.object.Attribute> lao = new LinkedList<com.uml.contradiction.model.object.Attribute>();
		com.uml.contradiction.model.object.Attribute a0 = new com.uml.contradiction.model.object.Attribute();
		a0.setName("sex");
		a0.setValue("true");
		lao.add(a0);
		com.uml.contradiction.model.object.Attribute a1 = new com.uml.contradiction.model.object.Attribute();
		a1.setName("crab");
		a1.setValue("156");
		lao.add(a1);
		object0.setAttributes(lao);
		List<OObject> loo = new LinkedList<OObject>();
		loo.add(object0);
		ObjectDiagram.setObjects(loo);
		
		/////
		Criterion criterion = new CorrectTypeCriterion();
		Engine engine = new Engine(criterion);
		VerificationResult verificationResult = engine.verify();
		assert verificationResult != null : "verificationResult == null";
		LOGGER.info("isGood = " + verificationResult.isGood());
		assertTrue(verificationResult.isGood());
		LOGGER.info("test really Completed");
	}
	
	@Test
	public void failTest(){
		CClass cClass = new CClass();
		cClass.setName("Student");
		List<com.uml.contradiction.model.cclass.Attribute> lac = new LinkedList<com.uml.contradiction.model.cclass.Attribute>();
		com.uml.contradiction.model.cclass.Attribute newAttrc = new com.uml.contradiction.model.cclass.Attribute();
		newAttrc.setName("sex");
		newAttrc.setType(UMLType.BOOLEAN);
		lac.add(newAttrc);
		cClass.setAttributes(lac);
		List<CClass> classes = new LinkedList<CClass>();
		classes.add(cClass);
		ClassDiagram.setClasses(classes);
		
		OObject object0 = new OObject();
		object0.setName("olololool");
		List<String> c0 = new LinkedList<String>();
		c0.add("Student");
		object0.setClasses(c0);
		
		List<com.uml.contradiction.model.object.Attribute> lao = new LinkedList<com.uml.contradiction.model.object.Attribute>();
		com.uml.contradiction.model.object.Attribute a0 = new com.uml.contradiction.model.object.Attribute();
		a0.setName("sex");
		a0.setValue("1");
		lao.add(a0);
		com.uml.contradiction.model.object.Attribute a1 = new com.uml.contradiction.model.object.Attribute();
		a1.setName("crab");
		a1.setValue("314");
//		lao.add(a1);
		object0.setAttributes(lao);
		List<OObject> loo = new LinkedList<OObject>();
		loo.add(object0);
		ObjectDiagram.setObjects(loo);
		
		/////
		Criterion criterion = new CorrectTypeCriterion();
		Engine engine = new Engine(criterion);
		VerificationResult verificationResult = engine.verify();
		assert verificationResult != null : "verificationResult == null";
		LOGGER.info("isGood = " + verificationResult.isGood());
		for(HistoryPlainItem plain : verificationResult.getFailHistory()){
			try {
				String slog = new String();
				for(int i = 0; i < plain.getItems().size(); ++i){
					slog += plain.getItems().get(i).variable + " = " + plain.getItems().get(i).value.getClass().getMethod("getName").invoke(plain.getItems().get(i).value) + ";";
				}
				LOGGER.info(slog);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		assertTrue(verificationResult.isFail());
		LOGGER.info("test really Completed");
	}
}
