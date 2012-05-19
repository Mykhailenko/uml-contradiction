package com.uml.contradiction.model.sequence;

public abstract class InteractionElement {
	public enum Type {
		INTERACTION, MESSAGE;
		@Override
		public String toString() {
			switch (this) {
			case INTERACTION:
				return "Interaction";
			case MESSAGE:
			default:
				return "Message";
			}
		};
	}

	public abstract  Type getType();

	public boolean isMessage() {
		return getType().equals(Type.MESSAGE);
	}

	public boolean isInteraction() {
		return getType().equals(Type.INTERACTION);
	}

}