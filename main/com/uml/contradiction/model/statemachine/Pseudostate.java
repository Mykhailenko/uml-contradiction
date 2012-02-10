package com.uml.contradiction.model.statemachine;

import com.uml.contradiction.model.Vertex;
import com.uml.contradiction.model.VertexType;


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
	@Override
	public VertexType getType() {
		return VertexType.PSEUDOSTATE;
	}
	


}