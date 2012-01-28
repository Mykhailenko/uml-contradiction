package com.uml.contradiction.model.statemachine;

import com.uml.contradiction.model.Vertex;

public class Transition {

	private Vertex target;
	private Vertex source;
	private TransitionKind kind;
	public Vertex getTarget() {
		return target;
	}
	public void setTarget(Vertex target) {
		this.target = target;
	}
	public Vertex getSource() {
		return source;
	}
	public void setSource(Vertex source) {
		this.source = source;
	}
	public TransitionKind getKind() {
		return kind;
	}
	public void setKind(TransitionKind kind) {
		this.kind = kind;
	}
	

}