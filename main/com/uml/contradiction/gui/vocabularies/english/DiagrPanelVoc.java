package com.uml.contradiction.gui.vocabularies.english;

public class DiagrPanelVoc {
	public static String getBtnLabel(String element) {
		String lbl = new String();
		
		if(lbl.equals("nextBut")) {
			lbl = "Next >>";
		}
		else if(lbl.equals("backBut")) {
			lbl = "<< Back";
		}
		else if(lbl.equals("addBut")) {
			lbl = "Add";
		}
		else if(lbl.equals("raddAllBut")) {
			lbl = "Add all";
		}
		else if(lbl.equals("removeBut")) {
			lbl = "Remove";
		}
		else if(lbl.equals("removeAllBut")) {
			lbl = "Remove all";
		}
		return lbl;
	}
	
	public static String getTextLabel(String element) {
		String lbl = new String();
		
		if(lbl.equals("ChooseDiagr")) {
			lbl = "Choose diagrams for verification, please";
		}

		return lbl;
	}	
}
