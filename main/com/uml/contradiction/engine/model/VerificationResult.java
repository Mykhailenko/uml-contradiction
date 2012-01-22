package com.uml.contradiction.engine.model;

import java.util.LinkedList;
import java.util.List;

public class VerificationResult {
	private boolean good = false;
	private List<HistoryPlainItem> failHistory;
	public boolean isGood() {
		return good;
	}
	public boolean isFail(){
		return !good;
	}
	public void setGood(boolean good) {
		this.good = good;
	}

	public List<HistoryPlainItem> getFailHistory() {
		return failHistory;
	}

	public void setFailHistory(List<HistoryPlainItem> badHistory) {
		this.failHistory = badHistory;
	}

	
}
