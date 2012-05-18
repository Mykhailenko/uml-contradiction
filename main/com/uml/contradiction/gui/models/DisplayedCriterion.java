package com.uml.contradiction.gui.models;

import javax.swing.ImageIcon;

import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.CriterionType;
import com.uml.contradiction.gui.Images;

public class DisplayedCriterion {
	private Criterion criterion;
	private String name;
	private String desctiption;

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

	public String getDesctiption() {
		return desctiption;
	}

	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
	}

	public DisplayedCriterion(Criterion criterion, String name,
			String desctiption) {
		super();
		this.criterion = criterion;
		this.name = name;
		this.desctiption = desctiption;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public CriterionType getType() {
		return this.criterion.getCriterionType();
	}

	public ImageIcon getImg() {
		return Images.getCriterionImage(this.criterion.getClass()
				.getSimpleName());
	}
}
