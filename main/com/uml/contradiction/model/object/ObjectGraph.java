package com.uml.contradiction.model.object;

import java.util.LinkedList;
import java.util.List;

public class ObjectGraph {
	private static List<ObjectDiagram> objectDiagrams = new LinkedList<ObjectDiagram>();
	private static List<OObject> objects = new LinkedList<OObject>();
	private static List<Link> links = new LinkedList<Link>();

	public static List<OObject> getObjects() {
		return objects;
	}

	public static void setObjects(List<OObject> objects) {
		ObjectGraph.objects = objects;
	}

	public static List<Link> getLinks() {
		return links;
	}

	public static void setLinks(List<Link> links) {
		ObjectGraph.links = links;
	}

	public static List<ObjectDiagram> getObjectDiagrams() {
		return objectDiagrams;
	}

	public static void setObjectDiagrams(List<ObjectDiagram> objectDiagrams) {
		ObjectGraph.objectDiagrams = objectDiagrams;
	}

	public static void clear() {
		objectDiagrams.clear();
		objects.clear();
		links.clear();
	}
}
