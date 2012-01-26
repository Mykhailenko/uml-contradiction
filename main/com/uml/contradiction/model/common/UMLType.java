package com.uml.contradiction.model.common;

public class UMLType implements Type {
	
	private String name;
	
	public final static UMLType BOOLEAN = new UMLType("Boolean");
	public final static UMLType STRING 	= new UMLType("String");
	public final static UMLType INTEGER = new UMLType("Integer");
	public final static UMLType UNLIMITED_NATURAL = new UMLType("UnlimitedNatural");
	
	private UMLType(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Type)obj).getName());
	}
}
