package com.uml.contradiction.model.sequence;

import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.OObject;

public class LifeLine {	
	private String name;
	private CClass cClass;
	private OObject oObject;
	private boolean classLifeLine;	//равно 0, когда актер(имя в name заноситься) или нет ссылки на класс
	private boolean anonymObject;	//равно 1, когда анонимный объект класса
	//Случай classLifeLine = 1, anonymObject = 0 означает наличия имени объекта у lifeline
	//если при этом oObject = null, то нет объекта с заданным именем и это имя запишеться в name
	
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAnonymObject() {
		return anonymObject;
	}
	public void setAnonymObject(boolean anonymObject) {
		this.anonymObject = anonymObject;
	}
	
	@Override
	public String toString() {
		String s = new String("LifeLine [");
		if(name != null)
			s += "LifeLineName=" + name;
		if(cClass != null)
			s += ", cClassName=" + cClass.getName();
		if(oObject != null)
			s += ", oObjectName=" + oObject.getName();
		s += ", classLifeLine=" + classLifeLine + "]";
		s += ", anonymObject=" + anonymObject + "]";
		return s;
	}
}