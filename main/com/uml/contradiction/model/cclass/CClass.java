package com.uml.contradiction.model.cclass;

import java.util.Set;

import com.uml.contradiction.model.Vertex;

public class CClass implements Vertex {
	private String name;
	private String packageName;
	private Set<Stereotype> stereotypes;
	private Visibility visibility;
	private boolean isAbstract;
	private String properties;
	private Set<Attribute> attributes;
	private Set<MMethod> methods;
	private Set templateParameters;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Set<Stereotype> getStereotypes() {
		return stereotypes;
	}
	public void setStereotypes(Set<Stereotype> stereotypes) {
		this.stereotypes = stereotypes;
	}
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	public boolean isAbstract() {
		return isAbstract;
	}
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public Set<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}
	public Set<MMethod> getMethods() {
		return methods;
	}
	public void setMethods(Set<MMethod> methods) {
		this.methods = methods;
	}
	public Set getTemplateParameters() {
		return templateParameters;
	}
	public void setTemplateParameters(Set templateParameters) {
		this.templateParameters = templateParameters;
	}
	
}
