package com.uml.contradiction.model.object;

import java.util.List;

import com.uml.contradiction.model.Vertex;
import com.uml.contradiction.model.VertexType;
import com.uml.contradiction.model.cclass.CClass;

public class OObject implements Vertex {	
	private String name;
	private List<CClass> classes;
	private List<Attribute> attributes;
	
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
	
	public VertexType getType() {
		return VertexType.OBJECT;
	}
	@Override
	public String toString() {
		return "OObject [name=" + name + ", classes=" + classes
				+ ", attributes=" + attributes + "]";
	}
	
}
