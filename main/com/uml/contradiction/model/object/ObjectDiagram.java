package com.uml.contradiction.model.object;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.Diagram;

public class ObjectDiagram implements Diagram {
	private String name;
	private List<OObject> objects = new LinkedList<OObject>();
	private List<Link> links = new LinkedList<Link>();;

	public List<OObject> getObjects() {
		return objects;
	}

	public void setObjects(List<OObject> objects) {
		this.objects = objects;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "ObjectDiagram [name=" + name + "\n\t" + ", objects=" + objects
				+ "\n\t" + ", links=" + links + "]";
	}
}
