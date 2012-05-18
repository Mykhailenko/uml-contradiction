package com.uml.contradiction.gui.vocabularies.english;

import com.uml.contradiction.engine.model.criteria.Criterion;

public class CriteriasVoc {

	public static String getName(Criterion crit) {
		String name = new String();
		Integer id = crit.getInternalID();

		switch (id) {
		case 1:
			name = "1";
			break;
		case 2:
			name = "2";
			break;
		default:
			name = "undefined";
			break;
		}

		return name;
	}

	public static String getDescription(Criterion crit) {
		String descr = new String();
		Integer id = crit.getInternalID();

		switch (id) {
		case 1:
			descr = "1";
			break;
		case 2:
			descr = "2";
			break;
		default:
			descr = "undefined";
			break;
		}

		return descr;
	}
}
