package com.uml.contradiction.tests;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.uml.contradiction.engine.Checker;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.PartOfSingleComposite;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationEnd;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.cclass.Multiplicity;

public class CorrectCompositeMultiplicityTest {
	private static final Logger LOGGER = Logger.getRootLogger();
	@Test
	public void goodTest(){
		////// good case
		Association ass = new Association();
		
		AssociationEnd end1 = new AssociationEnd();
		end1.setAggregationKind(AggregationKind.COMPOSITE);
		Multiplicity mult = new Multiplicity(0, 1.);
		end1.setMultiplicity(mult);
		
		AssociationEnd end2 = new AssociationEnd();
		end2.setAggregationKind(AggregationKind.NONE);
		
		ass.setEnd1(end1);
		ass.setEnd2(end2);
		
		List<Association> classes = new LinkedList<Association>();
		classes.add(ass);
	
		
		
		Association ass2 = new Association();
		
		AssociationEnd end12 = new AssociationEnd();
		end1.setAggregationKind(AggregationKind.COMPOSITE);
		Multiplicity mult2 = new Multiplicity(0, 1.);
		end1.setMultiplicity(mult2);
		
		AssociationEnd end22 = new AssociationEnd();
		end22.setAggregationKind(AggregationKind.COMPOSITE);
		Multiplicity mult22 = new Multiplicity(0, 1.);
		end12.setMultiplicity(mult22);
		
		ass2.setEnd1(end12);
		ass2.setEnd2(end22);
		
		classes.add(ass2);
		ClassGraph.setAssociations(classes);
		
		
		Criterion criterion = new PartOfSingleComposite();
		Checker engine = new Checker(criterion);
		VerificationResult verificationResult = engine.verify();
		assert verificationResult != null : "verificationResult == null";
		LOGGER.info("isGood = " + verificationResult.isGood());
		assertTrue(verificationResult.isGood());
		LOGGER.info("test really Completed");
	}
	
	@Test
	public void failTest(){
		////// good case
		Association ass = new Association();
		
		AssociationEnd end1 = new AssociationEnd();
		end1.setAggregationKind(AggregationKind.COMPOSITE);
		Multiplicity mult = new Multiplicity(0, Double.POSITIVE_INFINITY);
		end1.setMultiplicity(mult);
		
		AssociationEnd end2 = new AssociationEnd();
		end2.setAggregationKind(AggregationKind.NONE);
		
		ass.setEnd1(end1);
		ass.setEnd2(end2);
		
		List<Association> classes = new LinkedList<Association>();
	//	classes.add(ass);
	
		
		
		Association ass2 = new Association();
		
		AssociationEnd end12 = new AssociationEnd();
		end1.setAggregationKind(AggregationKind.COMPOSITE);
		Multiplicity mult2 = new Multiplicity(0, 1.);
		end1.setMultiplicity(mult2);
		
		AssociationEnd end22 = new AssociationEnd();
		end22.setAggregationKind(AggregationKind.COMPOSITE);
		Multiplicity mult22 = new Multiplicity(0, 2.);
		end22.setMultiplicity(mult22);
		
		ass2.setEnd1(end12);
		ass2.setEnd2(end22);
		
		classes.add(ass2);
		ClassGraph.setAssociations(classes);
		
		
		Criterion criterion = new PartOfSingleComposite();
		Checker engine = new Checker(criterion);
		VerificationResult verificationResult = engine.verify();
		assert verificationResult != null : "verificationResult == null";
		LOGGER.info("isGood = " + verificationResult.isGood());
		assertTrue(!verificationResult.isGood());
		LOGGER.info("test really Completed");
		
	}
}
