package com.uml.contradiction.model.object;

import com.uml.contradiction.model.Edge;
import com.uml.contradiction.model.EdgeType;

public class Link implements Edge {
	private LinkEnd end1;
	private LinkEnd end2;
	private String 	name;
	
	public Link(LinkEnd end1, LinkEnd end2, String name) {
		super();
		this.end1 = end1;
		this.end2 = end2;
		this.name = name;
	}
	
	public LinkEnd getEnd1() {
		return end1;
	}
	
	public void setEnd1(LinkEnd end1) {
		this.end1 = end1;
	}
	
	public LinkEnd getEnd2() {
		return end2;
	}
	
	public void setEnd2(LinkEnd end2) {
		this.end2 = end2;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public EdgeType getType() {
		return EdgeType.LINK;
	}	
}
