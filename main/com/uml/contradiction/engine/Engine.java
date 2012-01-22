package com.uml.contradiction.engine;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.BoundedPredicate;
import com.uml.contradiction.engine.model.Criterion;
import com.uml.contradiction.engine.model.HistoryItem;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.exception.PredicatException;

public class Engine {
	private static final Logger LOGGER = Logger.getRootLogger();
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
		
		VerificationResult result = analyseHistory();
		return null;
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
	private VerificationResult analyseHistory() {
		VerificationResult result = new VerificationResult();
		Quantifier quantifier = criterion.getQuantifiers().get(0);
		int counter = 0;
		List<HistoryPlainItem> failHistory = new LinkedList<HistoryPlainItem>();
		for(int i = 0; i < wholeHistory.size(); ++i){
			if(analyseHistory(wholeHistory.get(i))){
				++counter;
			}else{
				HistoryPlainItem item = new HistoryPlainItem();
				HistoryItem hi = wholeHistory.get(i);
				item.getItems().add(hi.getVariableValue());;
				failHistory.add(item);
			}
		}
		result.setGood(false);
		switch (quantifier.getType()) {
		case ALL:
			if(counter == wholeHistory.size()){
				result.setGood(true);
			}
			break;
		case EXIST:
			if(counter > 0){
				result.setGood(true);
			}
			break;
		case ALONE:
			if(counter == 1){
				result.setGood(true);
			}
			break;
		}
		// а тут мы должны в случае плохого исхода составить историю лишь из плохих результатов
		// пока сделаем бедово
		if(result.isFail()){
			result.setFailHistory(failHistory);
		}
		return result;
	}

	private boolean analyseHistory(HistoryItem historyItem) {
		Quantifier quantifier = criterion.getQuantifiers().get(historyItem.getDepth());
		int counter = 0;
		for(int i = 0; i < historyItem.getChildren().size(); ++i){
			if(analyseHistory(historyItem.getChildren().get(i))){
				++counter;
			}
		}
		boolean result = false;
		switch (quantifier.getType()) {
		case ALL:
			if(counter == historyItem.getChildren().size()){
				result = true;
			}
			break;
		case EXIST:
			if(counter > 0){
				result = true;
			}
			break;
		case ALONE:
			if(counter == 1){
				result = true;
			}
			break;
		}
		return result;
	}

	
	private boolean evaluetePredicate(BoundedPredicate boundedPredicate, List<VariableValue> currentVariables){
		List<Object> vars = new LinkedList<Object>();
		for(int i = 0; i < boundedPredicate.getBoundVariable().size(); ++i){
			vars.add(findVV(currentVariables, boundedPredicate.getBoundVariable().get(i)));
		}
		boolean result = false;
		try {
			result = boundedPredicate.getPredicate().predict(vars);
		} catch (PredicatException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
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











