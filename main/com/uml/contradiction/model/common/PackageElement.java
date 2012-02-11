package com.uml.contradiction.model.common;

import java.util.List;

import com.uml.contradiction.model.cclass.CClass;

public class PackageElement {
	private String name;
	private PackageElement parentPackageElement;
	private List<PackageElement> childrenPackages;
	private List<CClass> childrenClass;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PackageElement getParentPackageElement() {
		return parentPackageElement;
	}
	public void setParentPackageElement(PackageElement parentPackageElement) {
		this.parentPackageElement = parentPackageElement;
	}
	public List<PackageElement> getChildrenPackages() {
		return childrenPackages;
	}
	public void setChildrenPackages(List<PackageElement> childrenPackages) {
		this.childrenPackages = childrenPackages;
	}
	public List<CClass> getChildrenClass() {
		return childrenClass;
	}
	public void setChildrenClass(List<CClass> childrenClass) {
		this.childrenClass = childrenClass;
	}
//	@Override
//	public String toString() {
//		return "PackageElement [name=" + name + "]";
//	}
}
