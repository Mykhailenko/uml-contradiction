package com.uml.contradiction.engine.model.predicate.exception;

public class PredicatException extends Throwable {
	private static final long serialVersionUID = -8547742848997946758L;

	public PredicatException() {
		super();
	}

	public PredicatException(String reason) {
		super(reason);
	}
}
