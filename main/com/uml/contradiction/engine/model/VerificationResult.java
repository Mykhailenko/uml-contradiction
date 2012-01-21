package com.uml.contradiction.engine.model;

import java.util.List;

public class VerificationResult {
	private boolean good;
	private List<HistoryItem> badHistory;
	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	public List<HistoryItem> getBadHistory() {
		return badHistory;
	}

	public void setBadHistory(List<HistoryItem> badHistory) {
		this.badHistory = badHistory;
	}
	
}
