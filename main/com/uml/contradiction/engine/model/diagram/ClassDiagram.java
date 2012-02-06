package com.uml.contradiction.engine.model.diagram;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.cclass.CClass;

public class ClassDiagram {
	private static List<CClass> classes = new LinkedList<CClass>();

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
	
}
