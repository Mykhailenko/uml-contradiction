package com.uml.contradiction.model.object;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.Vertex;
import com.uml.contradiction.model.VertexType;
import com.uml.contradiction.model.cclass.CClass;

public class OObject implements Vertex {
	private String name;
	private CClass cclass;
	private List<AttributeObj> attributes = new LinkedList<AttributeObj>();

	public CClass getClasses() {
		return cclass;
	}

	public void setClasses(CClass cclass) {
		this.cclass = cclass;
	}

	public List<AttributeObj> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeObj> attributes) {
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public VertexType getType() {
		return VertexType.OBJECT;
	}

	@Override
	public String toString() {
		String s = new String();
		if (cclass != null) {

			s += cclass.getName() + ", ";
		}
		return "OObject [name=" + name + "," + " classesName= " + s
				+ ", attributes=" + attributes + "]";
	}

}
