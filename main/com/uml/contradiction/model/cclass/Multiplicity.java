package com.uml.contradiction.model.cclass;

public class Multiplicity {
	private Integer lowerBound;
	private Integer UpperBound;
	
	public Multiplicity(Integer lowerBound, Integer upperBound) {
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
	public Integer getUpperBound() {
		return UpperBound;
	}
	public void setUpperBound(Integer upperBound) {
		UpperBound = upperBound;
	}

	@Override
	public String toString() {
		return "Multiplicity: [" + lowerBound + "..."
				+ UpperBound + "]";
	}
	
	
}
