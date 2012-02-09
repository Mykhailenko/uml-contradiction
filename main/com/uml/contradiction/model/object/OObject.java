package com.uml.contradiction.model.object;

import java.util.List;

import com.uml.contradiction.model.Vertex;
import com.uml.contradiction.model.cclass.CClass;

public class OObject implements Vertex {
	private List<CClass> classes;
	private List<Attribute> attributes;
	private String name;
	public List<CClass> getClasses() {
		return classes;
	}
	public void setClasses(List<CClass> classes) {
		this.classes = classes;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
