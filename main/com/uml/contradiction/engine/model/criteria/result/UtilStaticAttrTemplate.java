package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;

public class UtilStaticAttrTemplate extends ResultTemplate {

	public UtilStaticAttrTemplate() {
		super();
	}

	@Override
	public void fill(HistoryPlainItem hpi) {
		String s = new String();
		String marker = ResultTemplate.ELEMENT_MARKER;

		CClass cl = (CClass) (hpi.getItems().get(0).value);
		Attribute attr = (Attribute) (hpi.getItems().get(1).value);
		System.out.println(cl.getName());
		System.out.println(attr.getName());
		s = s + "There is an attribute " + marker + attr.getName() + marker
				+ " with instance scope in class " + marker + cl.getName()
				+ marker + " with stereotype \"utility\"";

		super.setDiagrams(hpi.getItems());
		this.description = s;
	}

	@Override
	public void setDiagrTypes() {
		this.diagrTypes.add(DiagramType.CLASS);
		this.diagrTypes.add(null);
	}
}
