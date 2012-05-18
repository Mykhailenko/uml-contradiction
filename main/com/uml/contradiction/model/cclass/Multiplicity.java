package com.uml.contradiction.model.cclass;

public class Multiplicity {
	private Integer lowerBound;
	private Double UpperBound;

	public Multiplicity(Integer lowerBound, Double upperBound) {
		super();
		this.lowerBound = lowerBound;
		UpperBound = upperBound;
	}

	public Integer getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(Integer lowerBound) {
		this.lowerBound = lowerBound;
	}

	public Double getUpperBound() {
		return UpperBound;
	}

	public void setUpperBound(Double upperBound) {
		UpperBound = upperBound;
	}

	@Override
	public String toString() {
		return "Multiplicity: [" + lowerBound + "..." + UpperBound + "]";
	}

}
