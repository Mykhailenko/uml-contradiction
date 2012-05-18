package com.uml.contradiction.tests;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.uml.contradiction.engine.Checker;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.NaryNoAggregationOnly;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationEnd;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.cclass.NaryAssociationClass;

public class NaryNoAggregationCriterion {
	@Test
	public void test() {
		NaryAssociationClass nary = new NaryAssociationClass();
		
		Association ass1 = new Association();
		AssociationEnd end1 = new AssociationEnd();
		end1.setAggregationKind(AggregationKind.NONE);
		end1.setAssociatedClass(nary);
		
		AssociationEnd end2 = new AssociationEnd();
		end2.setAggregationKind(AggregationKind.NONE);
		end2.setAssociatedClass(new CClass());
	
		ass1.setEnd1(end1); ass1.setEnd2(end2);
		
		
		Association ass2 = new Association();
		AssociationEnd end12 = new AssociationEnd();
		end12.setAggregationKind(AggregationKind.NONE);
		end12.setAssociatedClass(nary);
		
		AssociationEnd end22 = new AssociationEnd();
		end22.setAggregationKind(AggregationKind.SHARED);
		end22.setAssociatedClass(new CClass());
		
		ass2.setEnd1(end12); ass2.setEnd2(end22);		

		List<CClass> classes = new LinkedList<CClass>();
		classes.add(nary);
		List<Association> ass = new LinkedList<Association>();
		ass.add(ass2);
		ass.add(ass1);
		ClassGraph.setClasses(classes);
		ClassGraph.setAssociations(ass);
		
		
		Criterion criterion = new NaryNoAggregationOnly();
		Checker engine = new Checker(criterion);
		VerificationResult verificationResult = engine.verify();
		assertTrue(verificationResult.isFail());
		System.out.println(verificationResult.getFailHistory());
	
	}
}
