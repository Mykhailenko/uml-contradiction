package com.uml.contradiction.model.cclass;

import com.uml.contradiction.model.Edge;
import com.uml.contradiction.model.EdgeType;

public class Realization implements Edge {
	private CClass abstraction;
	private CClass realization;

	public CClass getAbstraction() {
		return abstraction;
	}

	public void setAbstraction(CClass abstraction) {
		this.abstraction = abstraction;
	}

	public CClass getRealization() {
		return realization;
	}

	public void setRealization(CClass realization) {
		this.realization = realization;
	}

	@Override
	public EdgeType getType() {
		return EdgeType.REALIZATION;
	}

	@Override
	public String toString() {
		return "Realization [abstraction=" + abstraction + ", realization="
				+ realization + "]";
	}

}
