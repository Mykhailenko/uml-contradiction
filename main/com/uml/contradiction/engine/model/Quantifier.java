package com.uml.contradiction.engine.model;

import com.uml.contradiction.engine.model.rightPart.QuantifierRightPart;

public class Quantifier {
	private Variable boundVariable;
	private QuantifierType type;
	private QuantifierRightPart rightPart;
	public Variable getBoundVariable() {
		return boundVariable;
	}
	public void setBoundVariable(Variable boundVariable) {
		this.boundVariable = boundVariable;
	}
	public QuantifierType getType() {
		return type;
	}
	public void setType(QuantifierType type) {
		this.type = type;
	}
	public QuantifierRightPart getRightPart() {
		return rightPart;
	}
	public void setRightPart(QuantifierRightPart rightPart) {
		this.rightPart = rightPart;
	}
	

}