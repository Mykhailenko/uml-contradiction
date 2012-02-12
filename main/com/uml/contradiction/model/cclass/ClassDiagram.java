package com.uml.contradiction.model.cclass;

import java.util.List;

import com.uml.contradiction.model.common.PackageElement;



public class ClassDiagram {
	private String name;
	private List<CClass> classes;
	private List<Association> associations;
	private PackageElement parentPackageElement;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CClass> getClasses() {
		return classes;
	}
	public void setClasses(List<CClass> classes) {
		this.classes = classes;
	}
	public List<Association> getAssociations() {
		return associations;
	}
	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}
	public PackageElement getParentPackageElement() {
		return parentPackageElement;
	}
	public void setParentPackageElement(PackageElement parentPackageElement) {
		this.parentPackageElement = parentPackageElement;
	}
	@Override
	public String toString() {
		return "ClassDiagram [name=" + name + "\n\t, classes=" + classes
				+ "\n\t, associations=" + associations + "\n\t, parentPackageElement="
				+ parentPackageElement.getName() + "]";
	}
	
}
