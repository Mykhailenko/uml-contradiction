package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.OObject;

public class NoUtilityInstanceTemplate  extends ResultTemplate{
	
	public NoUtilityInstanceTemplate() {
		super();
	}
	
	@Override
	public void fill(HistoryPlainItem hpi) {
		
		String s = new String();
		CClass cl = (CClass)(hpi.getItems().get(0).value);
		OObject ob = (OObject)(hpi.getItems().get(1).value);
		s = "Trere is an object " + ob.getName() + " of class " + cl.getName() + " with stereotype <<utility>>.";
		
		super.setDiagrams(hpi.getItems());
		this.description = s;
	}

	@Override
	public void setDiagrTypes() {
		this.diagrTypes.add(DiagramType.CLASS);
		this.diagrTypes.add(DiagramType.OBJECT);
	}
}
