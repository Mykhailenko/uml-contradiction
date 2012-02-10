package com.uml.contradiction.model.statemachine;

import java.util.*;

import com.uml.contradiction.model.Vertex;
import com.uml.contradiction.model.VertexType;

public class State implements Vertex{

	private Trigger entry;
	private Trigger ddo;
	private Trigger exit;
	private List<Region> regions;
	private boolean isComposite;
	private boolean isOrthogonal;
	private boolean isSimple;
	private boolean isSubmachineState;
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
	public List<Region> getRegions() {
		return regions;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	public boolean isComposite() {
		return isComposite;
	}
	public void setComposite(boolean isComposite) {
		this.isComposite = isComposite;
	}
	public boolean isOrthogonal() {
		return isOrthogonal;
	}
	public void setOrthogonal(boolean isOrthogonal) {
		this.isOrthogonal = isOrthogonal;
	}
	public boolean isSimple() {
		return isSimple;
	}
	public void setSimple(boolean isSimple) {
		this.isSimple = isSimple;
	}
	public boolean isSubmachineState() {
		return isSubmachineState;
	}
	public void setSubmachineState(boolean isSubmachineState) {
		this.isSubmachineState = isSubmachineState;
	}
	public StateMachine getStateMachine() {
		return stateMachine;
	}
	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	public VertexType getType() {
		return VertexType.STATE;
	}
	
}