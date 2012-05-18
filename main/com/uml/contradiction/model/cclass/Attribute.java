package com.uml.contradiction.model.cclass;

import com.uml.contradiction.model.NamedElement;
import com.uml.contradiction.model.common.Type;

public class Attribute implements NamedElement {
	private String name;
	private Visibility visibility;
	private boolean isDerived;
	private Type type;
	private Multiplicity multiplicity;
	private String ddefault;
	private Scope scope;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public boolean isDerived() {
		return isDerived;
	}

	public void setDerived(boolean isDerived) {
		this.isDerived = isDerived;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Multiplicity getMultiplicity() {
		return multiplicity;
	}

	public void setMultiplicity(Multiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

	public String getDdefault() {
		return ddefault;
	}

	public void setDdefault(String ddefault) {
		this.ddefault = ddefault;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", visibility=" + visibility
				+ ", isDerived=" + isDerived + ", type=" + type
				+ ", multiplicity=" + multiplicity + ", ddefault=" + ddefault
				+ ", scope=" + scope + "]";
	}

}
