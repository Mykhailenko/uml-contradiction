package com.uml.contradiction.engine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.uml.contradiction.engine.model.Criterion;
import com.uml.contradiction.engine.model.HistoryItem;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VerificationResult;

public class Engine {
	private Criterion criterion;
	
	private List<Map<Variable,Object>> list;
	private List<HistoryItem> wholeHistory;
	private HistoryItem currentHistory;
	private int currentWorkIndex;
	
	public VerificationResult verify(){
		Quantifier quantifier = criterion.getQuantifiers().get(0);
		List firstSet = quantifier.getRightPart().getSet(null);
		int counter = 0;
		for(int i = 0; i < firstSet.size(); ++i){
			Object o = firstSet.get(i);
			currentHistory = new HistoryItem();
//			currentHistory.
//			verifyCasual();
		}
		boolean isSuccess = true;

		
		
		VerificationResult result = new VerificationResult();
		result.setGood(isSuccess);
		if(isSuccess == false){
			result.setBadHistory(filterFailHappends());
		}
		return null;
	}
	private List<HistoryItem> filterFailHappends() {
		List<HistoryItem> result = new LinkedList<HistoryItem>();
		for(HistoryItem historyItem : wholeHistory){
			if(historyItem.isFail()){
				result.add(historyItem);
			}
		}
		return result;
	}
	public void verifyCasual(){
	}
	
	public Engine(Criterion criterion) {
		this.criterion = criterion;
		this.list = new LinkedList<Map<Variable,Object>>();
		this.wholeHistory = new LinkedList<HistoryItem>();
		this.currentWorkIndex = 0;
	}
}
