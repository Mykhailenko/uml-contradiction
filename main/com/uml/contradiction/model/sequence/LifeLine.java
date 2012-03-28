package com.uml.contradiction.model.sequence;

import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.OObject;

public class LifeLine {	
	private CClass cClass;
	private OObject oObject;
	private boolean classLifeLine;
	public CClass getcClass() {
		return cClass;
	}
	public void setcClass(CClass cClass) {
		this.cClass = cClass;
	}
	public OObject getoObject() {
		return oObject;
	}
	public void setoObject(OObject oObject) {
		this.oObject = oObject;
	}
	public boolean isClassLifeLine() {
		return classLifeLine;
	}
	public void setClassLifeLine(boolean classLifeLine) {
		this.classLifeLine = classLifeLine;
	}
	
	@Override
	public String toString() {
		String s = new String("LifeLine [");
		if(cClass != null)
			s += "cClassName=" + cClass.getName();
		if(oObject != null)
			s += ", oObjectName=" + oObject.getName();
		s += ", classLifeLine=" + classLifeLine + "]";
		return s;
	}
}