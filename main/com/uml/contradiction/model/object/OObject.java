package com.uml.contradiction.model.object;

import java.util.List;

import com.uml.contradiction.model.Vertex;

public class OObject implements Vertex {
	private List<String> classes;
	private List<Attribute> attributes;
	private String name;
	public List<String> getClasses() {
		return classes;
	}
	public void setClasses(List<String> classes) {
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
