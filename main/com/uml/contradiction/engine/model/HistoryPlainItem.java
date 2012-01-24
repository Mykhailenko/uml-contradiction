package com.uml.contradiction.engine.model;

import java.util.LinkedList;
import java.util.List;

public class HistoryPlainItem {
	private List<VariableValue> items = new LinkedList<VariableValue>();

	public HistoryPlainItem(List<VariableValue> items) {
		this.items = items;
	}

	public List<VariableValue> getItems() {
		return items;
	}

	public void setItems(List<VariableValue> items) {
		this.items = items;
	}
	
}
