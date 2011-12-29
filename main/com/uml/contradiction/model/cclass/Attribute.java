package com.uml.contradiction.model.cclass;

public class Attribute {
	private String name;
	private Visibility visibility;
	private boolean isDerived;
	private String type;
	private Multiplicity multiplicity;
	private String ddefault;
	private String constraints;
	private Scope scope;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
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
	public String getConstraints() {
		return constraints;
	}
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
	public Scope getScope() {
		return scope;
	}
	public void setScope(Scope scope) {
		this.scope = scope;
	}
	
}
