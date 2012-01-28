package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.common.Type;
import com.uml.contradiction.model.common.UMLType;
import com.uml.contradiction.model.object.Attribute;

public class PredicateValueCorrectType implements Predicate {
	private static final Logger LOGGER = Logger.getRootLogger();
	/**
	 * Method recieve 2 items in list; 
	 * first  - attribute from object
	 * second - attribute from class
	 */
	@Override
	public boolean predict(List params) throws PredicatException {
		assert params != null : "params == null";
		if(params.size() != 2){
			LOGGER.error("param size should equal 2, non " + params.size());
			throw new PredicatException("param size should equal 2, non " + params.size());
		}
		Object first = params.get(0);
		if((first instanceof com.uml.contradiction.model.object.Attribute) == false){
			LOGGER.error("first element : unexpected type " + first.getClass().toString());
			throw new PredicatException("first element : unexpected type " + first.getClass().toString());
		}
		Object second = params.get(1);
		if((second instanceof com.uml.contradiction.model.cclass.Attribute) == false){
			LOGGER.error("second element : unexpected type " + second.getClass().toString());
			throw new PredicatException("second element : unexpected type " + second.getClass().toString());
		}
		com.uml.contradiction.model.object.Attribute objAttribute = (Attribute) first;
		com.uml.contradiction.model.cclass.Attribute classAttribute = (com.uml.contradiction.model.cclass.Attribute) second;
		boolean result;
		result = valueChecker(classAttribute.getType(), objAttribute.getValue());
		return result;
	}
	private boolean valueChecker(Type type, String value){
		if(type instanceof UMLType){
			switch ((UMLType)type) {
			case BOOLEAN:
				return booleanRecognizer(value);
			case INTEGER:
				return intRecognizer(value);
			default:
				return true;
			}
		}else{
			return true;
		}
	}
	private boolean booleanRecognizer(String value){
		if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")){
			return true;
		}else{
			return false;
		}
	}
	private boolean intRecognizer(String value){
		int s = 0;
		if(value.charAt(0) == '-' || value.charAt(0) == '+'){
			s = 1;
		}
		for(; s < value.length(); ++s){
			if(!Character.isDigit(value.charAt(s))){
				return false;
			}
		}
		return true;
		
	}
}
