package com.uml.contradiction.model.statemachine;

import com.uml.contradiction.model.Vertex;
import com.uml.contradiction.model.VertexType;

public class State implements Vertex {
	private String name;
	private Trigger entry;
	private Trigger ddo;
	private Trigger exit;
	private StateMachine stateMachine;

	public Trigger getEntry() {
		return entry;
	}

	public void setEntry(Trigger entry) {
		this.entry = entry;
	}

	public Trigger getDdo() {
		return ddo;
	}

	public void setDdo(Trigger ddo) {
		this.ddo = ddo;
	}

	public Trigger getExit() {
		return exit;
	}

	public void setExit(Trigger exit) {
		this.exit = exit;
	}

	public StateMachine getStateMachine() {
		return stateMachine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public VertexType getType() {
		return VertexType.STATE;
	}

	@Override
	public String toString() {
		return "State [name=" + name + ", entry=" + entry + ", ddo=" + ddo
				+ ", exit=" + exit + "]";
	}
}