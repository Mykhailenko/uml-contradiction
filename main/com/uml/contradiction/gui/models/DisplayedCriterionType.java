package com.uml.contradiction.gui.models;

import com.uml.contradiction.engine.model.criteria.CriterionType;

public class DisplayedCriterionType {
	private CriterionType type;
	private String name;

	public CriterionType getType() {
		return type;
	}

	public void setType(CriterionType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DisplayedCriterionType(CriterionType type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
