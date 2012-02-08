package com.uml.contradiction.engine.model;

import java.util.List;

import com.uml.contradiction.engine.model.criteria.Criterion;

public class VerificationResult {
	private Criterion criterion;
	private boolean good = false;
	private List<HistoryPlainItem> failHistory;
	public Criterion getCriterion() {
		return criterion;
	}
	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}
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
