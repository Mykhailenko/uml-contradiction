package com.uml.contradiction.engine.model;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.result.ResultTemplate;

public class VerificationResult {
	private Criterion criterion;
	private boolean good = false;
	private List<HistoryPlainItem> failHistory = new LinkedList<HistoryPlainItem>();
	private List<ResultTemplate> descriptions = null;
	
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
	
	public String toString() {
		if(good)
			return "OK!";
		else
			return "ERROR";
	}
	
	public List<ResultTemplate> getResultTemplate() {
		if(this.descriptions == null) {
			this.descriptions = new LinkedList<ResultTemplate>();
			for(HistoryPlainItem hpi : this.failHistory) {
				ResultTemplate rt = this.criterion.getResultTemplate();
				rt.fill(hpi);
				descriptions.add(rt);
			}
		}
		
		return this.descriptions;
	}
	
}
