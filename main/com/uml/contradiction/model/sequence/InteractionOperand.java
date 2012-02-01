package com.uml.contradiction.model.sequence;

public class InteractionOperand {
	private InteractionConstraint guard;
	private InteractionElement fragments;
	public InteractionConstraint getGuard() {
		return guard;
	}
	public void setGuard(InteractionConstraint guard) {
		this.guard = guard;
	}
	public InteractionElement getFragments() {
		return fragments;
	}
	public void setFragments(InteractionElement fragments) {
		this.fragments = fragments;
	}
	
}
