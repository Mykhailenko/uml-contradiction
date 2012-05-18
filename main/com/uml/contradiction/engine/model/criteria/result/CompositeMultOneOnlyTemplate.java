package com.uml.contradiction.engine.model.criteria.result;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.CClass;

public class CompositeMultOneOnlyTemplate extends ResultTemplate {

	public CompositeMultOneOnlyTemplate() {
		super();
	}

	@Override
	public void fill(HistoryPlainItem hpi) {
		String s = new String();
		Association as = (Association) (hpi.getItems().get(0).value);
		CClass cls = as.getEnd1().getAssociatedClass();
		CClass cle = as.getEnd2().getAssociatedClass();

		s = "A composition between classes " + ResultTemplate.ELEMENT_MARKER
				+ cls.getName() + ResultTemplate.ELEMENT_MARKER + " and "
				+ ResultTemplate.ELEMENT_MARKER + cle.getName()
				+ ResultTemplate.ELEMENT_MARKER
				+ " has incorrect multiplicity. ";

		if (as.getEnd1().getAggregationKind() != null
				&& as.getEnd1().getAggregationKind() == AggregationKind.COMPOSITE
				&& as.getEnd1().getMultiplicity() != null
				&& as.getEnd1().getMultiplicity().getUpperBound() > 1) {
			s = s + "Composite end corresponding to class "
					+ ResultTemplate.ELEMENT_MARKER + cls.getName()
					+ ResultTemplate.ELEMENT_MARKER + " has multiplicity "
					+ ResultTemplate.ELEMENT_MARKER + "["
					+ as.getEnd1().getMultiplicity().getLowerBound() + ".."
					+ as.getEnd1().getMultiplicity().getUpperBound() + "]"
					+ ResultTemplate.ELEMENT_MARKER + ". ";
		}

		if (as.getEnd2().getAggregationKind() != null
				&& as.getEnd2().getAggregationKind() == AggregationKind.COMPOSITE
				&& as.getEnd2().getMultiplicity() != null
				&& as.getEnd2().getMultiplicity().getUpperBound() > 1) {
			s = s + "Composite end corresponding to class "
					+ ResultTemplate.ELEMENT_MARKER + cle.getName()
					+ ResultTemplate.ELEMENT_MARKER + " has multiplicity "
					+ ResultTemplate.ELEMENT_MARKER + "["
					+ as.getEnd2().getMultiplicity().getLowerBound() + ".."
					+ as.getEnd2().getMultiplicity().getUpperBound() + "]"
					+ ResultTemplate.ELEMENT_MARKER + ". ";
		}

		super.setDiagrams(hpi.getItems());
		this.description = s;
	}

	@Override
	public void setDiagrTypes() {
		this.diagrTypes.add(DiagramType.CLASS);

	}
}