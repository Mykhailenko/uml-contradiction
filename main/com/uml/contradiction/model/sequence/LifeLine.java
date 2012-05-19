package com.uml.contradiction.model.sequence;

import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.OObject;

public class LifeLine {
	private String name;
	private CClass cClass;
	private OObject oObject;
	private boolean classLifeLine; // ����� 0, ����� �����(��� � name
									// ����������) ��� ��� ������ �� �����
	private boolean anonymObject; // ����� 1, ����� ��������� ������ ������

	// ������ classLifeLine = 1, anonymObject = 0 �������� ������� ����� �������
	// � lifeline
	// ���� ��� ���� oObject = null, �� ��� ������� � �������� ������ � ��� ���
	// ���������� � name

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
		String s = "LifeLine [";
		if (name != null) {
			s += "LifeLineName=" + name;
		}
		if (cClass != null) {
			s += ", cClassName=" + cClass.getName();
		}
		if (oObject != null) {
			s += ", oObjectName=" + oObject.getName();
		}
		s += ", classLifeLine=" + classLifeLine + "]";
		s += ", anonymObject=" + anonymObject + "]";
		return s;
	}
}