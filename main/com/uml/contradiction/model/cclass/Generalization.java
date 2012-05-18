package com.uml.contradiction.model.cclass;

import com.uml.contradiction.model.Edge;
import com.uml.contradiction.model.EdgeType;

public class Generalization implements Edge {
	private boolean isSubstitutable;
	private CClass general;
	private CClass specific;

	public boolean isSubstitutable() {
		return isSubstitutable;
	}

	public void setSubstitutable(boolean isSubstitutable) {
		this.isSubstitutable = isSubstitutable;
	}

	public CClass getGeneral() {
		return general;
	}

	public void setGeneral(CClass general) {
		this.general = general;
	}

	public CClass getSpecific() {
		return specific;
	}

	public void setSpecific(CClass specific) {
		this.specific = specific;
	}

	@Override
	public EdgeType getType() {
		// TODO Auto-generated method stub
		return EdgeType.GENERALIZATION;
	}

	@Override
	public String toString() {
		return "Generalization [isSubstitutable=" + isSubstitutable
				+ ", general=" + general + ", specific=" + specific + "]";
	}

}
