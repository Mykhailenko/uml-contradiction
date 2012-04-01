package com.uml.contradiction.model.statemachine;

import java.util.List;

import com.uml.contradiction.model.Edge;
import com.uml.contradiction.model.EdgeType;
import com.uml.contradiction.model.Vertex;

public class Transition implements Edge{
	
	private Vertex target;
	private Vertex source;
	private List<Trigger> triggers;
	private Guard guard;
	private StateMachine stateMachine;
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
	public List<Trigger> getTriggers() {
		return triggers;
	}
	public void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
	}
	public Guard getGuard() {
		return guard;
	}
	public void setGuard(Guard guard) {
		this.guard = guard;
	}

	public EdgeType getType() {
		return EdgeType.TRANSITION;
	}
	public StateMachine getStateMachine() {
		return stateMachine;
	}
	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	@Override
	public String toString() {
		return "Transition [target=" + target + ", source=" + source
				+ ", triggers=" + triggers + ", guard=" + guard + "]";
	}
}