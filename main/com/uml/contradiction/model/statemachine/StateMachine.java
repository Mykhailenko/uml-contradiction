package com.uml.contradiction.model.statemachine;

import java.util.List;

public class StateMachine {
	private List<Pseudostate> connectionPoint;
	private List<State> submachineState;
	private String className;
	public List<Pseudostate> getConnectionPoint() {
		return connectionPoint;
	}
	public void setConnectionPoint(List<Pseudostate> connectionPoint) {
		this.connectionPoint = connectionPoint;
	}
	public List<State> getSubmachineState() {
		return submachineState;
	}
	public void setSubmachineState(List<State> submachineState) {
		this.submachineState = submachineState;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
