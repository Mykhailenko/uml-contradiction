package com.uml.contradiction.model.cclass;

import java.util.List;
import java.util.Set;

import com.uml.contradiction.model.common.Stereotype;

public class MMethod {
	private String name;
	private Visibility visibility;
	private Set<Stereotype> stereotypes;
	private List<Parameter> parameters;
	private String returnResult;
	private String properties;
	private boolean isStatic;
	private Scope scope;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	public Set<Stereotype> getStereotypes() {
		return stereotypes;
	}
	public void setStereotypes(Set<Stereotype> stereotypes) {
		this.stereotypes = stereotypes;
	}
	public List<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	public String getReturnResult() {
		return returnResult;
	}
	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	public Scope getScope() {
		return scope;
	}
	public void setScope(Scope scope) {
		this.scope = scope;
	}
	
}	
