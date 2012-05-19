package com.uml.contradiction.gui.vocabularies.english;

public class DiagrPanelVoc {
	public static String getBtnLabel(String element) {
		String lbl = "?";

		if (element.equals("nextBut")) {
			lbl = "Next >>";
		} else if (element.equals("backBut")) {
			lbl = "<< Back";
		} else if (element.equals("addBut")) {
			lbl = "Add";
		} else if (element.equals("addAllBut")) {
			lbl = "Add all";
		} else if (element.equals("removeBut")) {
			lbl = "Remove";
		} else if (element.equals("removeAllBut")) {
			lbl = "Remove all";
		}
		return lbl;
	}

	public static String getTextLabel(String element) {
		String lbl = new String();

		if (lbl.equals("ChooseDiagr")) {
			lbl = "Choose diagrams for verification, please";
		}

		return lbl;
	}
}
