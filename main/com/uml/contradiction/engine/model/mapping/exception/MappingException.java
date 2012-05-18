package com.uml.contradiction.engine.model.mapping.exception;

public class MappingException extends Throwable {
	private static final long serialVersionUID = -6765651509069780015L;

	public MappingException() {
		super();
	}

	public MappingException(String reason) {
		super(reason);
	}
}
