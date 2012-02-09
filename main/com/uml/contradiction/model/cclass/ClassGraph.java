package com.uml.contradiction.model.cclass;

import java.util.LinkedList;
import java.util.List;


public class ClassGraph {
	private static List<ClassDiagram> classDiagrams = new LinkedList<ClassDiagram>();
	private static List<CClass> classes = new LinkedList<CClass>();
	private static List<Association> associations = new LinkedList<Association>();
	
	public static CClass findClassByName(String className){
		List<CClass> listClass = ClassGraph.getClasses();
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
		ClassGraph.classes = classes;
	}
	public static List<Association> getAssociations() {
		return associations;
	}

	public static void setAssociations(List<Association> associations) {
		ClassGraph.associations = associations;
	}
	public static List<ClassDiagram> getClassDiagrams() {
		return classDiagrams;
	}
	public static void setClassDiagrams(List<ClassDiagram> classDiagrams) {
		ClassGraph.classDiagrams = classDiagrams;
	}
}
