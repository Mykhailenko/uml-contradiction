package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;

public class UtilStaticMthsTemplate extends ResultTemplate {

	public UtilStaticMthsTemplate() {
		super();
	}

	@Override
	public void fill(HistoryPlainItem hpi) {
		String s = new String();
		String marker = ResultTemplate.ELEMENT_MARKER;

		CClass cl = (CClass) (hpi.getItems().get(0).value);
		MMethod meth = (MMethod) (hpi.getItems().get(1).value);

		s = s + "There is a method " + marker + meth.getName() + marker
				+ " withinstance scope in class " + marker + cl.getName()
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