package com.uml.contradiction.engine.model.diagram;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.object.OObject;

public class ObjectDiagram {
	private static List<OObject> objects = new LinkedList<OObject>()	;

	public static List<OObject> getObjects() {
		return objects;
	}

	public static void setObjects(List<OObject> objects) {
		ObjectDiagram.objects = objects;
	}

	
}
