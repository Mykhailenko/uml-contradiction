package com.uml.contradiction.engine.model.diagram;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.CClass;

public class ClassDiagram {
	private static List<CClass> classes = new LinkedList<CClass>();
	private static List<Association> associations = new LinkedList<Association>();
	
	public static CClass findClassByName(String className){
		List<CClass> listClass = ClassDiagram.getClasses();
		for(CClass cClass : listClass){
			if(cClass.getName().equals(className)){
				return cClass;
			}
		}
		return null;
	}
	
	public static List<CClass> getClasses() {
		return classes;
	}

	public static void setClasses(List<CClass> classes) {
		ClassDiagram.classes = classes;
	}
	public static List<Association> getAssociations() {
		return associations;
	}

	public static void setAssociations(List<Association> associations) {
		ClassDiagram.associations = associations;
	}
}
