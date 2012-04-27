package com.uml.contradiction.model.object;

import java.util.List;

import com.uml.contradiction.model.Diagram;

public class ObjectDiagram extends Diagram{

	private List<OObject> objects;

	public List<OObject> getObjects() {
		return objects;
	}
	public void setObjects(List<OObject> objects) {
		this.objects = objects;
	}
	 
}
