package com.uml.contradiction.model.object;

import java.util.LinkedList;
import java.util.List;


public class ObjectGraph {
	private static List<OObject> objects = new LinkedList<OObject>()	;

	public static List<OObject> getObjects() {
		return objects;
	}

	public static void setObjects(List<OObject> objects) {
		ObjectGraph.objects = objects;
	}

	
}
