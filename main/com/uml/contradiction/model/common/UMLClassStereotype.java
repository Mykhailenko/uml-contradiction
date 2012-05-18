package com.uml.contradiction.model.common;

public enum UMLClassStereotype implements Stereotype {

	UTILITY("utility"), ENTITY("entity"), CONTROL("control"), IMPLEMENTATION_CLASS(
			"implementationClass");

	private String name;

	private UMLClassStereotype(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
