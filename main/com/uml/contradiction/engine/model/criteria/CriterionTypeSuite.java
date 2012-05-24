package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.gui.models.DisplayedCriterionType;

public class CriterionTypeSuite {
	public static List<DisplayedCriterionType> getAllDisplayedCriterions() {
		List<DisplayedCriterionType> res = new LinkedList<DisplayedCriterionType>();

		res.add(new DisplayedCriterionType(CriterionType.CLASS_CLASS,
				"Class-Class"));
		res.add(new DisplayedCriterionType(CriterionType.CLASS_OBJECT,
				"Class-Object"));
		res.add(new DisplayedCriterionType(CriterionType.CLASS_SEQUENCE,
				"Class-Sequence"));
		res.add(new DisplayedCriterionType(CriterionType.CLASS_STATE,
				"Class-State"));
//		res.add(new DisplayedCriterionType(CriterionType.SEQUENCE_STATE,
//				"Sequence-State"));

		return res;
	}
}
