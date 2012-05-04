package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.object.AttributeObj;
import com.uml.contradiction.model.object.OObject;

public class CorrectTypeTemplate extends ResultTemplate {

	@Override
	public void fill(HistoryPlainItem hpi) {
		super.setDiagrams(hpi.getItems());
		OObject ob = (OObject) (hpi.getItems().get(0).value);
		AttributeObj ao = (AttributeObj) hpi.getItems().get(1).value;
		String s = "Trere is incorrect value " + ResultTemplate.ELEMENT_MARKER
				+ ao.getValue() + ResultTemplate.ELEMENT_MARKER + " for attribute " + 
				ResultTemplate.ELEMENT_MARKER + ao.getName() + ResultTemplate.ELEMENT_MARKER + 
				" in object " + ResultTemplate.ELEMENT_MARKER + ob.getName() + ResultTemplate.ELEMENT_MARKER;
		this.description = s;
	}

	@Override
	public void setDiagrTypes() {
		diagrTypes.add(DiagramType.CLASS);
		diagrTypes.add(DiagramType.OBJECT);
	}

}
