package com.uml.contradiction.gui.models;

import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.CriterionType;

public class DisplayedCriterion {
	private Criterion criterion;
	private String name;
	private String description;
	public Criterion getCriterion() {
		return criterion;
	}
	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CriterionType getType() {
		return this.criterion.getCriterionType();
	}
	public DisplayedCriterion(Criterion criterion, String name,
			String description) {
		super();
		this.criterion = criterion;
		this.name = name;
		this.description = description;
	}
	
	
}
