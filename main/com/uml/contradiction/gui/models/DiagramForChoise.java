package com.uml.contradiction.gui.models;

import com.uml.contradiction.common.DiagramType;

public class DiagramForChoise {
	private Integer id;
	private String name;
	private DiagramType type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DiagramType getType() {
		return type;
	}

	public void setType(DiagramType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
