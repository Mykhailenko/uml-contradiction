package com.uml.contradiction.engine.model;

import java.util.LinkedList;
import java.util.List;


public class HistoryItem {
//	private static final Logger LOGGER = Logger.getRootLogger();
	private HistoryItem parent = null;
	private VariableValue variableValue = new VariableValue();
	private List<HistoryItem> children = new LinkedList<HistoryItem>();
	private Boolean success = null;

	public boolean isSuccess() {
		return success;
	}

	public boolean isFail() {
		return !success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public HistoryPlainItem getHistoryPlainItem() {
		HistoryPlainItem historyPlainItem = new HistoryPlainItem(
				getPlainHistory());
		return historyPlainItem;
	}

	public List<VariableValue> getPlainHistory() {
		List<VariableValue> result = new LinkedList<VariableValue>();
		result.add(variableValue);

		HistoryItem curr = parent;
		while (curr != null) {
			result.add(0, curr.variableValue);
			curr = curr.parent;
		}
//		for (VariableValue vv : result) {
//			LOGGER.debug("" + vv.variable + " = " + vv.value);
//		}
//		LOGGER.debug(" ");
		return result;

	}
	

	public int getDepth() {
		int result = 1;
		HistoryItem current = this.parent;
		while (current != null) {
			++result;
			current = current.parent;
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
