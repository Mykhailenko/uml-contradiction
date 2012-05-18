package com.uml.contradiction.gui.panels.diagramstypes;

import com.uml.contradiction.common.DiagramType;

public class ViewedDiagramType {

	private DiagramType type;
	private String viewedName;

	public ViewedDiagramType(DiagramType type, String viewedName) {
		super();
		this.type = type;
		this.viewedName = viewedName;
	}

	public DiagramType getType() {
		return type;
	}

	public void setType(DiagramType type) {
		this.type = type;
	}

	public String getViewedName() {
		return viewedName;
	}

	public void setViewedName(String viewedName) {
		this.viewedName = viewedName;
	}

	@Override
	public String toString() {
		return this.viewedName;
	}
}
