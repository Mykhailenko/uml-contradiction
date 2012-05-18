package com.uml.contradiction.model.common;

public enum UMLType implements Type {

	BOOLEAN("Boolean"), STRING("String"), INTEGER("Integer"), UNLIMITED_NATURAL(
			"UnlimitedNatural");

	private String name;

	private UMLType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
