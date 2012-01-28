package com.uml.contradiction.model.statemachine;

import com.uml.contradiction.model.Vertex;


public class Pseudostate implements Vertex{
	private PseudostateKind kind;
	private StateMachine stateMachine;
	public PseudostateKind getKind() {
		return kind;
	}
	public void setKind(PseudostateKind kind) {
		this.kind = kind;
	}
	public StateMachine getStateMachine() {
		return stateMachine;
	}
	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

}