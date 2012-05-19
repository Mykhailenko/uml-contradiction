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
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(VariableValue vv : items){
			sb.append(vv.variable + ":");
			if(vv.value == null){
				sb.append("null;");
			}else{
				sb.append("!=null;");
			}
		}
		return sb.toString();
	}
	@Override
	public boolean equals(Object obj) {
		HistoryPlainItem hpi = (HistoryPlainItem) obj;
		if (hpi.getItems().size() == items.size()) {
			for (int i = 0; i < items.size(); ++i) {
				VariableValue origin = items.get(i);
				if (!origin.equals(hpi.getItems().get(i))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 0;
		for (VariableValue vv : items) {
			if (vv != null) {
				hash += vv.hashCode();
			}
		}
		return hash;
	}
}
