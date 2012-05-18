package com.uml.contradiction.engine.model;

import com.uml.contradiction.engine.model.rightPart.QuantifierRightPart;

public class Quantifier {
	private Variable boundVariable;
	private QuantifierType type;
	private QuantifierRightPart rightPart;

	public Variable getBoundVariable() {
		return boundVariable;
	}

	public Quantifier setBoundVariable(Variable boundVariable) {
		this.boundVariable = boundVariable;
		return this;
	}

	public QuantifierType getType() {
		return type;
	}

	public Quantifier setType(QuantifierType type) {
		this.type = type;
		return this;
	}

	public QuantifierRightPart getRightPart() {
		return rightPart;
	}

	public Quantifier setRightPart(QuantifierRightPart rightPart) {
		this.rightPart = rightPart;
		return this;
	}

}