package com.uml.contradictions.view.tests;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.panels.ContradictionsPanel;
import com.uml.contradiction.gui.panels.VerificationResultsPanel;
import com.uml.contradiction.gui.windows.MainWindow;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationEnd;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.cclass.NaryAssociationClass;
import com.uml.contradiction.model.object.OObject;
import com.uml.contradiction.model.object.ObjectDiagram;
import com.uml.contradiction.model.object.ObjectGraph;

public class StartTest {
	
	@SuppressWarnings("deprecation")
	@Test
	public void createStartWindow() throws IOException {
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
		
		///////////////////////////////////////
		OObject ob1 = new OObject();
		OObject ob2 = new OObject();
		CClass cl1 = new CClass();
		
		cl1.setName("cl1");
		
		ob1.setClasses(cl1);
		ob1.setName("ob1");	
		ob2.setClasses(cl1);
		
		ObjectDiagram od = new ObjectDiagram();
		od.setName("od1");
		
		List<OObject> obs = new LinkedList<OObject>();
		List<ObjectDiagram> ods = new LinkedList<ObjectDiagram>();
		
		
		obs.add(ob1);
		obs.add(ob2);
		od.setObjects(obs);
		ods.add(od);
		
		ObjectGraph.setObjectDiagrams(ods);
		
		ObjectGraph.setObjects(obs);
		
		System.out.println(ObjectGraph.getObjects().size());
		
		MainWindow mainWindow = new MainWindow();
	
		ContradictionsPanel p = new ContradictionsPanel();
		VerificationResultsPanel pp = new VerificationResultsPanel();
		PanelsController.contradictionsPanel = p;
		PanelsController.resultsPanel = pp;
		PanelsController.mainWindow = mainWindow;
		//PanelsController.showPanel(pp);
		PanelsController.showPanel(p);
		
		mainWindow.show();
		System.in.read();
	}
}
