package com.uml.contradiction.engine.model.criteria.result;

import java.util.List;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.model.object.OObject;
import com.uml.contradiction.model.object.ObjectGraph;

public class MustExistClassTemplate extends ResultTemplate{
	
	public MustExistClassTemplate() {
		super();
	}
	
	@Override
	public void fill(HistoryPlainItem hpi) {
		super.setDiagrams(hpi.getItems());
		String s = new String();
		OObject ob = (OObject)(hpi.getItems().get(0).value);
		s = "Trere is no class for object " + ob.getName();
		this.description = s;
	}

	@Override
	public void setDiagrTypes() {
		this.diagrTypes.add(DiagramType.OBJECT);
		this.diagrTypes.add(null);
	}
}