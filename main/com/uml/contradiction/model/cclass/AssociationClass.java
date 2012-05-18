package com.uml.contradiction.model.cclass;

import com.uml.contradiction.model.VertexType;

public class AssociationClass extends CClass {
	private Association association;

	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}

	@Override
	public VertexType getType() {
		return VertexType.ASSOCIATION_CLASS;
	}

	@Override
	public String toString() {
		return "AssociationClass [" + super.toString() + "[association="
				+ association + "]";
	}

}
