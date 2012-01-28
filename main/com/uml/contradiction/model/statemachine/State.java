package com.uml.contradiction.model.statemachine;

import java.util.*;

import com.uml.contradiction.model.Vertex;

public class State implements Vertex{

	private Transition entry;
	private Transition ddo;
	private Transition exit;
	private List<Region> regions;
	private boolean isComposite;
	private boolean isOrthogonal;
	private boolean isSimple;
	private boolean isSubmachineState;
	public Transition getEntry() {
		return entry;
	}
	public void setEntry(Transition entry) {
		this.entry = entry;
	}
	public Transition getDdo() {
		return ddo;
	}
	public void setDdo(Transition ddo) {
		this.ddo = ddo;
	}
	public Transition getExit() {
		return exit;
	}
	public void setExit(Transition exit) {
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
	
}