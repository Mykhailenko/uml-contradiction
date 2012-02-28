package com.uml.contradiction.model.sequence;
public interface InteractionElement {
	public enum Type {
		INTERACTION, COMBINED, MESSAGE;
		public String toString() {
			switch (this) {
			case COMBINED:
				return "Combined fragment";
			case INTERACTION:
				return "Interaction";
			case MESSAGE:
			default:
				return "Message";
			}
		};
	}
	Type getType();

}