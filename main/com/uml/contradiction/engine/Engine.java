package com.uml.contradiction.engine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.uml.contradiction.engine.model.BoundedPredicate;
import com.uml.contradiction.engine.model.Criterion;
import com.uml.contradiction.engine.model.HistoryItem;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.VerificationResult;

public class Engine {
	private Criterion criterion;
	private List<HistoryItem> wholeHistory;
	
	public VerificationResult verify(){
		Quantifier quantifier = criterion.getQuantifiers().get(0);
		List<Object> firstSet = quantifier.getRightPart().getSet(null);

		for(int i = 0; i < firstSet.size(); ++i){
			Object o = firstSet.get(i);
			HistoryItem historyItem = new HistoryItem();
			historyItem.getVariableValue().variable = quantifier.getBoundVariable();
			historyItem.getVariableValue().value = o;
			wholeHistory.add(historyItem);
			verify(historyItem , 1);
		}
		// тут мы имеем "консервативную историю". Осталось её санализировать
		
		
		boolean isSuccess = true;
		VerificationResult result = new VerificationResult();
		result.setGood(isSuccess);
		///////////////////////////////////////
		return null;
	}
	
	private void verify(HistoryItem parentHistoryItem, int currentIndex) {
		if(currentIndex < criterion.getQuantifiers().size()){
			Quantifier quantifier = criterion.getQuantifiers().get(currentIndex);
			List<Object> set = quantifier.getRightPart().getSet(parentHistoryItem.getPlainHistory()); 
			
			for(int i = 0; i < set.size(); ++i){
				Object o = set.get(i);
				HistoryItem historyItem = new HistoryItem();
				historyItem.getVariableValue().variable = quantifier.getBoundVariable();
				historyItem.getVariableValue().value = o;
				historyItem.setParent(parentHistoryItem);
				parentHistoryItem.getChildren().add(historyItem);
				verify(historyItem, currentIndex+1);
			}
		}else{
			List<VariableValue> currentVariables = parentHistoryItem.getPlainHistory();
			boolean result = true;
			for(int i = 0; i < criterion.getBoundedPredicates().size(); ++i){
				BoundedPredicate boundedPredicate = criterion.getBoundedPredicates().get(i);
				result = evaluetePredicate(boundedPredicate, currentVariables);
				if(result == false){
					break;
				}
			}
			parentHistoryItem.setSuccess(result);
		}
	}
	private boolean evaluetePredicate(BoundedPredicate boundedPredicate, List<VariableValue> currentVariables){
		return true;
	}
	public Engine(Criterion criterion) {
		this.criterion = criterion;
		this.wholeHistory = new LinkedList<HistoryItem>();
	}
}
