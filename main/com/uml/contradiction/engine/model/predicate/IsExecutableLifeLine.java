package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.LifeLine;

public class IsExecutableLifeLine implements Predicate {
	private static final Logger LOGGER = Logger.getRootLogger();
	private String failString = null
			;
	@Override
	public boolean predict(List params) throws PredicatException {
		assert params != null;
		assert params.size() == 2;
		Object first = params.get(0);
		if((first instanceof Interaction) == false){
			LOGGER.error("Unexpected type : " + first.getClass().toString());
			throw new PredicatException("Unexpected type : " + first.getClass().toString());
		}
		Object second = params.get(1);
		if((second instanceof LifeLine) == false){
			LOGGER.error("Unexpected type : " + second.getClass().toString());
			throw new PredicatException("Unexpected type : " + second.getClass().toString());
		}
		Interaction interaction = (Interaction) first;
		LifeLine lifeLine = (LifeLine) second;
//////		
		return false;
	}
	@Override
	public String getFailDescription() {
		return null;
	}
}
