package com.uml.contradiction.gui.vocabularies.english;

public class StartPanelVoc {
	public static String getLabel(String element) {
		String lbl = new String();

		if (element.equals("loadXMIBtn")) {
			lbl = "Load XMI";
		} else {
			lbl = "undefined";
		}

		return lbl;

	}
}
