package com.uml.contradiction.engine;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.BoundedPredicate;
import com.uml.contradiction.engine.model.HistoryItem;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.engine.model.predicate.exception.PredicatException;

public class Engine {
	private static final Logger LOGGER = Logger.getRootLogger();
	private Criterion criterion;
	private List<HistoryItem> wholeHistory;
	private List<HistoryItem> failHistory;
	@SuppressWarnings("unchecked")
	public VerificationResult verify(){
		Quantifier quantifier = criterion.getQuantifiers().get(0);
		List<Object> firstSet = null;;
		try {
			firstSet = quantifier.getRightPart().getSet(null);
		} catch (MappingException e) {
			e.printStackTrace();
		}
		assert firstSet != null;
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
		return result;
	}
	///olololo
	@SuppressWarnings("unchecked")
	private void verify(HistoryItem parentHistoryItem, int currentIndex) {
		assert currentIndex > 0 : "Bad index";
		if(currentIndex < criterion.getQuantifiers().size()){
			Quantifier quantifier = criterion.getQuantifiers().get(currentIndex);
			List<Object> set = null;
			try {
				set = quantifier.getRightPart().getSet(parentHistoryItem.getPlainHistory());
			} catch (MappingException e) {
				e.printStackTrace();
			} 
			assert set != null;
			if(set.isEmpty()){
				parentHistoryItem.setSuccess(false);
			}
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
			if(criterion.isNegative()){
				result = !result;
			}
			if(result == false && criterion.getBoundedPredicates().size() == 1){
				parentHistoryItem.setFailDescription(criterion.getBoundedPredicates().get(0).getPredicate().getFailDescription());
			}
			parentHistoryItem.setSuccess(result);
		}
	}
	private VerificationResult analyseHistory() {
		VerificationResult result = new VerificationResult();
		result.setCriterion(criterion);
		Quantifier quantifier = criterion.getQuantifiers().get(0);
		int counter = 0;
		failHistory = new LinkedList<HistoryItem>();
		for(int i = 0; i < wholeHistory.size(); ++i){
			if(analyseHistory(wholeHistory.get(i))){
				++counter;
			}else{
				failHistory.add(wholeHistory.get(i));
			}
		}
		result.setGood(false);
		switch (quantifier.getType()) {
		case ALL:
			if(counter == wholeHistory.size()){
				result.setGood(true);
			}else{
				LOGGER.debug("!ALL " + counter + " ; " + wholeHistory.size());
			}
			break;
		case EXIST:
			if(counter > 0){
				result.setGood(true);
			}else{
				LOGGER.debug("!EXI " + counter + " ; " + wholeHistory.size());
			}
			break;
		case ALONE:
			if(counter == 1){
				result.setGood(true);
			}else{
				LOGGER.debug("!ALO " + counter + " ; " + wholeHistory.size());
			}
			break;
		}
		// а тут мы должны в случае плохого исхода составить историю лишь из плохих результатов
		// пока сделаем бедово
		if(result.isFail()){
			result.setFailHistory(createPlainFailHistory());
		}
		return result;
	}

	private List<HistoryPlainItem> createPlainFailHistory() {
		List<HistoryPlainItem> result = new LinkedList<HistoryPlainItem>();
		for(HistoryItem historyItem : failHistory){
			if(historyItem.getChildren().isEmpty() && historyItem.isFail()){
//				result.add(new HistoryPlainItem(historyItem.getPlainHistory()));
				result.add(historyItem.getHistoryPlainItem());
			}else{
				createPlainFailHistory(result, historyItem);
			}
		}
		return result;
	}
	private void createPlainFailHistory(List<HistoryPlainItem> result,
			HistoryItem parentHistoryItem) {
		for(HistoryItem historyItem : parentHistoryItem.getChildren()){
			if(historyItem.getChildren().isEmpty() && historyItem.isFail()){
//				result.add(new HistoryPlainItem(historyItem.getPlainHistory()));
				result.add(historyItem.getHistoryPlainItem());
			}else{
				createPlainFailHistory(result, historyItem);
			}
		}
		
	}
	private boolean analyseHistory(HistoryItem historyItem) {
		int d = historyItem.getDepth();
		if(d < criterion.getQuantifiers().size()){
			Quantifier quantifier = criterion.getQuantifiers().get(d);
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
				}else{
					LOGGER.debug("!ALL " + counter + " ; " + historyItem.getChildren().size());
				}
				break;
			case EXIST:
				if(counter > 0){
					result = true;
				}else{
					LOGGER.debug("!EXI " + counter + " ; " + historyItem.getChildren().size());
				}
				break;
			case ALONE:
				if(counter == 1){
					result = true;
				}else{
					LOGGER.debug("!ALO " + counter + " ; " + historyItem.getChildren().size());
				}
				break;
			}
			return result;
		}else{
			return historyItem.isSuccess();
		}
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
		if(boundedPredicate.isNegative()){
			result = !result;
		}
		return result;
	}
	private Object findVV(List<VariableValue> list, Variable variable){
		for(VariableValue variableValue : list){
			LOGGER.debug("" + variableValue.variable + " = " + variableValue.value.getClass().toString());
		}
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











