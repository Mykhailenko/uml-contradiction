package com.uml.contradiction.model.cclass;

import java.util.HashSet;
import java.util.Set;

public class GeneralizationSet {
	private CClass root;
	private Set<CClass> leafs;
	private String name;
	private boolean isCovering;
	private boolean isDisjoint;

	public GeneralizationSet() {
		leafs = new HashSet<CClass>();
	}

	public CClass getRoot() {
		return root;
	}

	public void setRoot(CClass root) {
		this.root = root;
	}

	public Set<CClass> getLeafs() {
		return leafs;
	}

	public void setLeafs(Set<CClass> leafs) {
		this.leafs = leafs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCovering() {
		return isCovering;
	}

	public void setCovering(boolean isCovering) {
		this.isCovering = isCovering;
	}

	public boolean isDisjoint() {
		return isDisjoint;
	}

	public void setDisjoint(boolean isDisjoint) {
		this.isDisjoint = isDisjoint;
	}

}
