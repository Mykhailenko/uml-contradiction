package com.uml.contradiction.model.sequence;
import java.util.*;

public class Interaction extends InteractionElement {
	private String name;
	private List<LifeLine> lifeLines;
	private List<InteractionElement> childs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<LifeLine> getLifeLines() {
		return lifeLines;
	}
	public void setLifeLines(List<LifeLine> lifeLines) {
		this.lifeLines = lifeLines;
	}
	public List<InteractionElement> getChilds() {
		return childs;
	}
	public void setChilds(List<InteractionElement> childs) {
		this.childs = childs;
	}
	@Override
	public String toString() {
		return "Interaction [name=" + name + ", lifeLines=" + lifeLines + "\n\t, childs=" + childs + "]";
	}	
	@Override
	public Type getType() {
		return Type.INTERACTION;
	}
}