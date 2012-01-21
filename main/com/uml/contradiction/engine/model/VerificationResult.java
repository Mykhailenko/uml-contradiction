package com.uml.contradiction.engine.model;

import java.util.List;

public class VerificationResult {
	private boolean good = false;
	private List<HistoryPlainItem> badHistory;
	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	public List<HistoryPlainItem> getBadHistory() {
		return badHistory;
	}

	public void setBadHistory(List<HistoryPlainItem> badHistory) {
		this.badHistory = badHistory;
	}

	
}
