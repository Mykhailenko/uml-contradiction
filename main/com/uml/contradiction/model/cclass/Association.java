package com.uml.contradiction.model.cclass;

import com.uml.contradiction.model.Edge;
import com.uml.contradiction.model.EdgeType;

public class Association implements Edge {
	private String name;
	private AssociationEnd end1;
	private AssociationEnd end2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AssociationEnd getEnd1() {
		return end1;
	}

	public void setEnd1(AssociationEnd end1) {
		this.end1 = end1;
	}

	public AssociationEnd getEnd2() {
		return end2;
	}

	public void setEnd2(AssociationEnd end2) {
		this.end2 = end2;
	}

	@Override
	public String toString() {
		return "Association [name=" + name + ", end1=" + end1 + ", end2="
				+ end2 + "]";
	}

	@Override
	public EdgeType getType() {
		return EdgeType.ASSOCIATION;
	}
}
