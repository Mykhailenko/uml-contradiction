package com.uml.contradiction.model.sequence;
public interface InteractionElement {
	public enum Type {
		INTERACTION, COMBINED, MESSAGE;
	}
	Type getType();
}