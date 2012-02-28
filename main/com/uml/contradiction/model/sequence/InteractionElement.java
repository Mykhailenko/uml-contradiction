package com.uml.contradiction.model.sequence;
public abstract class InteractionElement {
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
	abstract public Type getType();
	public boolean isMessage(){
		return getType().equals(Type.MESSAGE);
	}
	public boolean isInteraction(){
		return getType().equals(Type.INTERACTION);
	}
	public boolean isCombinedFragment(){
		return getType().equals(Type.COMBINED);
	}

}