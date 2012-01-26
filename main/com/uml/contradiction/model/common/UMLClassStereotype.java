package com.uml.contradiction.model.common;

public class UMLClassStereotype implements Stereotype {
	
	private String name;
	
	public final static UMLClassStereotype UTILITY 	= new UMLClassStereotype("utility");
	public final static UMLClassStereotype ENTITY 	= new UMLClassStereotype("entity");
	public final static UMLClassStereotype CONTROL	= new UMLClassStereotype("control");
	public final static UMLClassStereotype IMPLEMENTATION_CLASS = new UMLClassStereotype("implementationClass");
	
	private UMLClassStereotype(String name) {
		super();
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
	
	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((Stereotype)obj).getName());
	}
}
