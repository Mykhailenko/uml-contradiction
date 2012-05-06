package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.sequence.LifeLine;

public class CorrectLifeLinesTemplate extends ResultTemplate {

	@Override
	public void fill(HistoryPlainItem hpi) {
		String sep = ResultTemplate.ELEMENT_MARKER;

		LifeLine lifeLine = (LifeLine) hpi.getItems().get(1).value;
		super.setDiagrams(hpi.getItems());
		description = "There is an uncorrect lifeLine " + sep
				+ lifeLine.getName() + sep;

	}

	@Override
	public void setDiagrTypes() {
		this.diagrTypes.add(DiagramType.CLASS);
		this.diagrTypes.add(DiagramType.SEQUENCE);
	}

}
