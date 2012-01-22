package com.uml.contradiction.engine.model.predicate.exception;

public class PredicatException extends Throwable{
	public PredicatException() {
		super();
	}
	public PredicatException(String reason){
		super(reason);
	}
}
