package com.uml.contradiction.model.common;

import java.util.ArrayList;
import java.util.List;

import com.uml.contradiction.model.Vertex;
import com.uml.contradiction.model.VertexType;
import com.uml.contradiction.model.cclass.CClass;

public class Package implements Vertex {
	private String name;
	private Package parentPackageElement;
	private List<Package> childrenPackages = new ArrayList<Package>();
	private List<CClass> childrenClass = new ArrayList<CClass>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Package getParentPackageElement() {
		return parentPackageElement;
	}

	public void setParentPackageElement(Package parentPackageElement) {
		this.parentPackageElement = parentPackageElement;
	}

	public List<Package> getChildrenPackages() {
		return childrenPackages;
	}

	public void setChildrenPackages(List<Package> childrenPackages) {
		this.childrenPackages = childrenPackages;
	}

	public List<CClass> getChildrenClass() {
		return childrenClass;
	}

	public void setChildrenClass(List<CClass> childrenClass) {
		this.childrenClass = childrenClass;
	}

	@Override
	public String toString() {
		return "PackageElement [name=" + name + "]";
	}

	@Override
	public VertexType getType() {
		return VertexType.PACKAGE;
	}
}
