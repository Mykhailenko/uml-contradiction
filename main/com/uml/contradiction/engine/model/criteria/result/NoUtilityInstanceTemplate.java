package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.OObject;

public class NoUtilityInstanceTemplate extends ResultTemplate {

	public NoUtilityInstanceTemplate() {
		super();
	}

	@Override
	public void fill(HistoryPlainItem hpi) {
		String s = new String();
		OObject ob = (OObject) (hpi.getItems().get(0).value);
		CClass cl = (CClass) (hpi.getItems().get(1).value);
		s = "Trere is an object " + ResultTemplate.ELEMENT_MARKER
				+ ob.getName() + ResultTemplate.ELEMENT_MARKER + " of class "
				+ ResultTemplate.ELEMENT_MARKER + cl.getName()
				+ ResultTemplate.ELEMENT_MARKER
				+ " with stereotype <<utility>>.";

		super.setDiagrams(hpi.getItems());
		this.description = s;
	}

	@Override
	public void setDiagrTypes() {
		this.diagrTypes.add(DiagramType.OBJECT);
		this.diagrTypes.add(DiagramType.CLASS);

	}
}
