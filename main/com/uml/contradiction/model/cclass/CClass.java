package com.uml.contradiction.model.cclass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.uml.contradiction.model.Vertex;
import com.uml.contradiction.model.VertexType;
import com.uml.contradiction.model.common.Package;
import com.uml.contradiction.model.common.Stereotype;

public class CClass implements Vertex {
	private String name;
	private Vertex parent;
	private Set<Stereotype> stereotypes;
	private Visibility visibility;
	private boolean isAbstract;
	private List<Attribute> attributes;
	private List<MMethod> methods;
	private List<TemplateParameter> templateParameters;
	private String requiredInterface;
	private List<CClass> nestedCClasses;

	public CClass() {
		super();
		stereotypes = new HashSet<Stereotype>();
		attributes = new ArrayList<Attribute>();
		methods = new ArrayList<MMethod>();
		templateParameters = new ArrayList<TemplateParameter>();
		nestedCClasses = new ArrayList<CClass>();
	}

	public String getName() {
		return name;
	}

	public String getRequiredInterface() {
		return requiredInterface;
	}

	public void setRequiredInterface(String requiredInterface) {
		this.requiredInterface = requiredInterface;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<MMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<MMethod> methods) {
		this.methods = methods;
	}

	@SuppressWarnings("rawtypes")
	public List getTemplateParameters() {
		return templateParameters;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setTemplateParameters(List templateParameters) {
		this.templateParameters = templateParameters;
	}

	public String getFullName() {
		String fullName = new String();
		Vertex packageElement = parent;
		while (packageElement != null) {
			String str;
			if (packageElement.getType().equals(VertexType.CLASS)) {
				str = ((CClass) packageElement).getName();
				packageElement = ((CClass) packageElement).getParent();
			} else {
				str = ((Package) packageElement).getName();
				packageElement = ((Package) packageElement)
						.getParentPackageElement();
			}
			fullName = str + "." + fullName;
		}
		fullName = fullName + name;
		return fullName;
	}

	public String getPackageName() {
		String fName = getFullName();
		int point = fName.lastIndexOf('.');
		if (point == -1) {
			return fName;
		} else {
			return fName.substring(0, point);
		}
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "CClass [name=" + name + ", fullName=" + getFullName()
				+ ", stereotypes=" + stereotypes + ", visibility=" + visibility
				+ ", isAbstract=" + isAbstract + ", attributes=" + attributes
				+ ", methods=" + methods + ", templateParameters="
				+ templateParameters + ", requiredInterface="
				+ requiredInterface + "]";
	}

	public List<CClass> getNestedCClasses() {
		return nestedCClasses;
	}

	public void setNestedCClasses(List<CClass> nestedCClasses) {
		this.nestedCClasses = nestedCClasses;
	}

	@Override
	public VertexType getType() {
		return VertexType.CLASS;
	}

}
