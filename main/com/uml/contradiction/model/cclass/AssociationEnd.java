package com.uml.contradiction.model.cclass;

public class AssociationEnd {
	private String role;
	private Multiplicity multiplicity;
	private AggregationKind aggregationKind;
	private Visibility visibility;
	private boolean isDerived;
	private Navigability navigability;
	private CClass associatedClass;

	public Navigability getNavigability() {
		return navigability;
	}

	public void setNavigability(Navigability navigability) {
		this.navigability = navigability;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Multiplicity getMultiplicity() {
		return multiplicity;
	}

	public void setMultiplicity(Multiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

	public AggregationKind getAggregationKind() {
		return aggregationKind;
	}

	public void setAggregationKind(AggregationKind aggregationKind) {
		this.aggregationKind = aggregationKind;
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

	public CClass getAssociatedClass() {
		return associatedClass;
	}

	public void setAssociatedClass(CClass associatedClass) {
		this.associatedClass = associatedClass;
	}

	@Override
	public String toString() {
		return "AssociationEnd [role=" + role + ", multiplicity="
				+ multiplicity + ", aggregationKind=" + aggregationKind
				+ ", visibility=" + visibility + ", isDerived=" + isDerived
				+ ", navigability=" + navigability + ", associatedClass="
				+ associatedClass + "]";
	}
}
