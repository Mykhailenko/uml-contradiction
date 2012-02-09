package com.uml.contradiction.model.object;

import java.util.List;

public class ObjectDiagram {
	private String name;
	private List<OObject> objects;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OObject> getObjects() {
		return objects;
	}
	public void setObjects(List<OObject> objects) {
		this.objects = objects;
	}
	 
}
