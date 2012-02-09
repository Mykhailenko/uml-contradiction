package com.uml.contradiction.tests;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.uml.contradiction.engine.Engine;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.SimpleCriterion;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.object.OObject;
import com.uml.contradiction.model.object.ObjectGraph;

import static org.junit.Assert.assertTrue;

public class EngineTest {
	private static final Logger LOGGER = Logger.getRootLogger();
	@Test
	public void goodContradictionTest(){
		////// good case
		CClass cClass = new CClass();
		cClass.setName("Student");
		List<com.uml.contradiction.model.cclass.Attribute> lac = new LinkedList<com.uml.contradiction.model.cclass.Attribute>();
		com.uml.contradiction.model.cclass.Attribute newAttrc = new com.uml.contradiction.model.cclass.Attribute();
		newAttrc.setName("fio");
		lac.add(newAttrc);
		cClass.setAttributes(lac);
		List<CClass> classes = new LinkedList<CClass>();
		classes.add(cClass);
		ClassGraph.setClasses(classes);
		
		OObject object0 = new OObject();
		List<CClass> c0 = new LinkedList<CClass>();
		c0.add(cClass);
		object0.setClasses(c0);
		
		List<com.uml.contradiction.model.object.Attribute> lao = new LinkedList<com.uml.contradiction.model.object.Attribute>();
		com.uml.contradiction.model.object.Attribute a0 = new com.uml.contradiction.model.object.Attribute();
		a0.setName("fio");
		lao.add(a0);
		object0.setAttributes(lao);
		List<OObject> loo = new LinkedList<OObject>();
		loo.add(object0);
		ObjectGraph.setObjects(loo);
		
		/////
		SimpleCriterion simpleCriterion = new SimpleCriterion();
		Engine engine = new Engine(simpleCriterion);
		VerificationResult verificationResult = engine.verify();
		assert verificationResult != null : "verificationResult == null";
		LOGGER.info("isGood = " + verificationResult.isGood());
		assertTrue(verificationResult.isGood());
		LOGGER.info("test really Completed");
	}
	
	@Test
	public void failContradictionTest(){
		CClass cClass = new CClass();
		cClass.setName("Student");
		List<com.uml.contradiction.model.cclass.Attribute> lac = new LinkedList<com.uml.contradiction.model.cclass.Attribute>();
		com.uml.contradiction.model.cclass.Attribute newAttrc = new com.uml.contradiction.model.cclass.Attribute();
		newAttrc.setName("fio");
		lac.add(newAttrc);
		cClass.setAttributes(lac);
		List<CClass> classes = new LinkedList<CClass>();
		classes.add(cClass);
		ClassGraph.setClasses(classes);
		
		OObject object0 = new OObject();
		object0.setName("olololool");
		List<CClass> c0 = new LinkedList<CClass>();
		c0.add(cClass);
		object0.setClasses(c0);
		
		List<com.uml.contradiction.model.object.Attribute> lao = new LinkedList<com.uml.contradiction.model.object.Attribute>();
		com.uml.contradiction.model.object.Attribute a0 = new com.uml.contradiction.model.object.Attribute();
		a0.setName("fio");
		lao.add(a0);
		com.uml.contradiction.model.object.Attribute a1 = new com.uml.contradiction.model.object.Attribute();
		a1.setName("crab");
		lao.add(a1);
		object0.setAttributes(lao);
		List<OObject> loo = new LinkedList<OObject>();
		loo.add(object0);
		ObjectGraph.setObjects(loo);
		
		/////
		SimpleCriterion simpleCriterion = new SimpleCriterion();
		Engine engine = new Engine(simpleCriterion);
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

