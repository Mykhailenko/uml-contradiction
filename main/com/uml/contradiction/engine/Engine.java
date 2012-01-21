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
		
		VerificationResult result = analysHistory();
		return null;
	}
	
	private VerificationResult analysHistory() {
		VerificationResult result = new VerificationResult();
		Quantifier quantifier = criterion.getQuantifiers().get(0);
		int counter = 0;
		for(int i = 0; i < wholeHistory.size(); ++i){
			if(analyse(wholeHistory.get(i))){
				++counter;
			}
		}
		switch (quantifier.getType()) {
		case ALL:
			if(counter == wholeHistory.size()){
				result.setGood(true);
			}else{
				result.setGood(false);
			}
			break;
		case EXIST:
			if(counter > 0){
				result.setGood(true);
			}else{
				result.setGood(false);
			}
			break;
		case ALONE:
			if(counter == 1){
				result.setGood(true);
			}else{
				result.setGood(false);
			}
			break;
		}
		return result;
	}

	private boolean analyse(HistoryItem historyItem) {
		return false;
	}

	private void verify(HistoryItem parentHistoryItem, int currentIndex) {
		assert currentIndex > 0 : "Bad index";
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
		List<Object> vars = new LinkedList<Object>();
		for(int i = 0; i < boundedPredicate.getBoundVariable().size(); ++i){
			vars.add(findVV(currentVariables, boundedPredicate.getBoundVariable().get(i)));
		}
		return boundedPredicate.getPredicate().predict(vars);
	}
	private Object findVV(List<VariableValue> list, Variable variable){
		for(VariableValue variableValue : list){
			if(variableValue.variable.equals(variable)){
				return variableValue.value;
			}
		}
		return null;
	}
	public Engine(Criterion criterion) {
		this.criterion = criterion;
		this.wholeHistory = new LinkedList<HistoryItem>();
	}
}











