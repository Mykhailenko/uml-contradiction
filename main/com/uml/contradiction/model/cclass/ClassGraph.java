package com.uml.contradiction.model.cclass;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.Vertex;
import com.uml.contradiction.model.VertexType;

public class ClassGraph {
	private static List<ClassDiagram> classDiagrams = new LinkedList<ClassDiagram>();
	private static List<CClass> classes = new LinkedList<CClass>();
	private static List<Association> associations = new LinkedList<Association>();
	private static List<Dependency> dependencies = new LinkedList<Dependency>();
	private static List<Realization> realizations = new LinkedList<Realization>();
	private static List<Generalization> generalizations = new LinkedList<Generalization>();

	public static CClass findClassByName(String className) {
		List<CClass> listClass = ClassGraph.getClasses();
		for (CClass cClass : listClass) {
			if (cClass.getName().equals(className)) {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<NaryAssociationClass> getNaries() {
		List res = new LinkedList();
		for (Vertex v : classes) {
			if (v.getType() == VertexType.NARY) {
				res.add(v);
			}
		}

		return res;
	}

	public static List<Dependency> getDependencies() {
		return dependencies;
	}

	public static void setDependencies(List<Dependency> dependencies) {
		ClassGraph.dependencies = dependencies;
	}

	public static List<Realization> getRealizations() {
		return realizations;
	}

	public static void setRealizations(List<Realization> realizations) {
		ClassGraph.realizations = realizations;
	}

	public static List<Generalization> getGeneralizations() {
		return generalizations;
	}

	public static void setGeneralizations(List<Generalization> generalizations) {
		ClassGraph.generalizations = generalizations;
	}

	public static void clear() {
		classDiagrams.clear();
		classes.clear();
		associations.clear();
		dependencies.clear();
		generalizations.clear();
		realizations.clear();
	}
}
