package com.uml.contradiction.model.cclass;

import com.uml.contradiction.model.common.Type;

public class Parameter {
	private String name;
	private Type type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Parameter [name=" + name + ", type=" + type + "]";
	}
}
