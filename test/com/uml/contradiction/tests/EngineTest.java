package com.uml.contradiction.tests;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.uml.contradiction.engine.Engine;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.SimpleCriterion;
import com.uml.contradiction.engine.model.diagram.ClassDiagram;
import com.uml.contradiction.engine.model.diagram.ObjectDiagram;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.OObject;

import static org.junit.Assert.assertTrue;

public class EngineTest {
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
		ClassDiagram.setClasses(classes);
		
		OObject object0 = new OObject();
		List<String> c0 = new LinkedList<String>();
		c0.add("Student");
		object0.setClasses(c0);
		
		List<com.uml.contradiction.model.object.Attribute> lao = new LinkedList<com.uml.contradiction.model.object.Attribute>();
		com.uml.contradiction.model.object.Attribute a0 = new com.uml.contradiction.model.object.Attribute();
		a0.setName("fio");
		lao.add(a0);
		object0.setAttributes(lao);
		List<OObject> loo = new LinkedList<OObject>();
		loo.add(object0);
		ObjectDiagram.setObjects(loo);
		
		/////
		SimpleCriterion simpleCriterion = new SimpleCriterion();
		Engine engine = new Engine(simpleCriterion);
		VerificationResult verificationResult = engine.verify();
		assertTrue(verificationResult.isGood());
	}
	
//	@Test
	public void failContradictionTest(){
		////// good case
		
		/////
		SimpleCriterion simpleCriterion = new SimpleCriterion();
		Engine engine = new Engine(simpleCriterion);
		VerificationResult verificationResult = engine.verify();
		assertTrue(verificationResult.isFail());
	}
}

