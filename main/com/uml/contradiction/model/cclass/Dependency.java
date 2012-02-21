package com.uml.contradiction.model.cclass;

import java.util.List;

import com.uml.contradiction.model.Edge;
import com.uml.contradiction.model.EdgeType;
import com.uml.contradiction.model.common.Stereotype;


public class Dependency implements Edge {
	private List<Stereotype> stereotypes;
	private List<CClass> suppliers;
	private List<CClass> clients;
	public List<Stereotype> getStereotypes() {
		return stereotypes;
	}
	public void setStereotypes(List<Stereotype> stereotypes) {
		this.stereotypes = stereotypes;
	}
	public List<CClass> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(List<CClass> suppliers) {
		this.suppliers = suppliers;
	}
	public List<CClass> getClients() {
		return clients;
	}
	public void setClients(List<CClass> clients) {
		this.clients = clients;
	}
	
	public EdgeType getType() {
		return EdgeType.DEPENDENCY;
	}
	@Override
	public String toString() {
		return "Dependency [stereotypes=" + stereotypes + ", suppliers="
				+ suppliers + ", clients=" + clients + "]";
	}	
	
} 
