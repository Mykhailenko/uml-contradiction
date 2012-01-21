package com.uml.contradiction.engine.model;

import java.util.LinkedList;
import java.util.List;


public class HistoryItem {
	private HistoryItem parent = null;
	private VariableValue variableValue = new VariableValue();
	private List<HistoryItem> children = new LinkedList<HistoryItem>();
	private boolean success = false;
	public boolean isSuccess() {
		return success;
	}
	public boolean isFail(){
		return !success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<VariableValue> getPlainHistory(){
		List<VariableValue> result = new LinkedList<VariableValue>();
		result.add(variableValue);

		HistoryItem curr = parent;
		while(curr != null){
			result.add(0, curr.variableValue);
			curr = curr.parent;
		}
		
		return result;
		
	}
	public VariableValue getVariableValue() {
		return variableValue;
	}
	public void setVariableValue(VariableValue variableValue) {
		this.variableValue = variableValue;
	}
	public List<HistoryItem> getChildren() {
		return children;
	}
	public void setChildren(List<HistoryItem> children) {
		this.children = children;
	}
	public HistoryItem getParent() {
		return parent;
	}
	public void setParent(HistoryItem parent) {
		this.parent = parent;
	}
	
	
}
