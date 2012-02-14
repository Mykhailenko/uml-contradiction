package com.uml.contradiction.model.sequence;

import com.uml.contradiction.model.cclass.CClass;

public class LifeLine {
	private String name;
	private CClass cclass;
	boolean isClassLifeLine;
	String objectName;

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isClassLifeLine() {
		return isClassLifeLine;
	}

	public void setClassLifeLine(boolean isClassLifeLine) {
		this.isClassLifeLine = isClassLifeLine;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

		public CClass getCclass() {
		return cclass;
	}

	public void setCclass(CClass cclass) {
		this.cclass = cclass;
	}

	@Override
	public String toString() {
		return "LifeLine [name=" + name + ", cclass=" + cclass
				+ ", isClassLifeLine=" + isClassLifeLine + ", objectName="
				+ objectName + "]";
	}
}