package com.uml.contradiction.model.common;

public class UserType implements Type {

	private String name;

	public UserType(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Type) obj).getName());
	}

	@Override
	public String toString() {
		return "UserType [name=" + name + "]";
	}
}
