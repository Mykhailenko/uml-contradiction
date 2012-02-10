package com.uml.contradiction.model.sequence;

import com.uml.contradiction.model.cclass.CClass;

public class LifeLine {
	private CClass cclass;

	public CClass getCclass() {
		return cclass;
	}

	public void setCclass(CClass cclass) {
		this.cclass = cclass;
	}
	
	public String getClassName() {
		return this.cclass.getName();
	}
	

}