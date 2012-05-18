package com.uml.contradiction.model.cclass;

import java.util.ArrayList;
import java.util.List;

import com.uml.contradiction.model.Diagram;
import com.uml.contradiction.model.common.Package;

public class ClassDiagram implements Diagram {
	private String name;
	private List<CClass> classes;
	private List<Association> associations;
	private List<Dependency> dependencies;
	private List<Realization> realizations;
	private List<Generalization> generalizations;
	private Package parentPackageElement;

	public ClassDiagram() {
		super();
		classes = new ArrayList<CClass>();
		associations = new ArrayList<Association>();
		dependencies = new ArrayList<Dependency>();
		realizations = new ArrayList<Realization>();
		generalizations = new ArrayList<Generalization>();
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

	public Package getParentPackageElement() {
		return parentPackageElement;
	}

	public void setParentPackageElement(Package parentPackageElement) {
		this.parentPackageElement = parentPackageElement;
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}

	public List<Realization> getRealizations() {
		return realizations;
	}

	public void setRealizations(List<Realization> realizations) {
		this.realizations = realizations;
	}

	public List<Generalization> getGeneralizations() {
		return generalizations;
	}

	public void setGeneralizations(List<Generalization> generalizations) {
		this.generalizations = generalizations;
	}

	@Override
	public String toString() {
		return "ClassDiagram [name=" + getName() + "\n\t, classes=" + classes
				+ "\n\t, associations=" + associations + "\n\t, dependencies="
				+ dependencies + "\n\t, realizations=" + realizations
				+ "\n\t, generalizations=" + generalizations
				+ "\n\t, parentPackageElement=" + parentPackageElement + "]";
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
