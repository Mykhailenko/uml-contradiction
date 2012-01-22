package com.uml.contradiction.engine.model.mapping.exception;

public class MappingException extends Throwable{
	public MappingException() {
		super();
	}
	public MappingException(String reason){
		super(reason);
	}
}
