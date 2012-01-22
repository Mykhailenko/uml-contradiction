package com.uml.contradiction.model.object;

import com.uml.contradiction.model.NamedElement;

public class Attribute implements NamedElement{
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
