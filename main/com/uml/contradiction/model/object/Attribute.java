package com.uml.contradiction.model.object;

import com.uml.contradiction.model.NamedElement;
import com.uml.contradiction.model.cclass.*;

public class Attribute implements NamedElement{
	private String name;
	private String value;
	private Attribute refClassAttr;
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
	public Attribute getRefClassAttr() {
		return refClassAttr;
	}
	public void setRefClassAttr(Attribute refClassAttr) {
		this.refClassAttr = refClassAttr;
	}
	@Override
	public String toString() {
		return "Attribute [name=" + name + ", value=" + value
				+ ", refClassAttr=" + refClassAttr + "]";
	}
	
}
