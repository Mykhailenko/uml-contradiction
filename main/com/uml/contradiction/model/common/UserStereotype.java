package com.uml.contradiction.model.common;

public class UserStereotype implements Stereotype {

	private String name;

	public UserStereotype(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((Stereotype) obj).getName());
	}

	@Override
	public String toString() {
		return "UserStereotype [name=" + name + "]";
	}

}
