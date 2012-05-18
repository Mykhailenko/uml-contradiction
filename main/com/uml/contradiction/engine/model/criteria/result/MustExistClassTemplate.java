package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.object.OObject;

public class MustExistClassTemplate extends ResultTemplate {

	public MustExistClassTemplate() {
		super();
	}

	@Override
	public void fill(HistoryPlainItem hpi) {
		super.setDiagrams(hpi.getItems());
		String s = new String();
		OObject ob = (OObject) (hpi.getItems().get(0).value);
		s = "Trere is no class for object " + ResultTemplate.ELEMENT_MARKER
				+ ob.getName() + ResultTemplate.ELEMENT_MARKER;
		this.description = s;
	}

	@Override
	public void setDiagrTypes() {
		this.diagrTypes.add(DiagramType.OBJECT);
		this.diagrTypes.add(null);
	}
}
