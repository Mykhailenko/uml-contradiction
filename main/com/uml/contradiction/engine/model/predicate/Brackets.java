package com.uml.contradiction.engine.model.predicate;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;

public class Brackets implements Formula {
	public enum OperationType {
		AND, OR;
	};

	private OperationType type;
	private List<Formula> formulas;
	private boolean negative;

	public Brackets() {
		type = OperationType.OR;
		formulas = new LinkedList<Formula>();
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}

	public List<Formula> getFormulas() {
		return formulas;
	}

	public void setFormulas(List<Formula> formulas) {
		this.formulas = formulas;
	}

	public boolean isNegative() {
		return negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}

	@Override
	public boolean predict(List<VariableValue> variableValues) {
		switch (type) {
		case OR:
			return predictOr(variableValues);
		case AND:
		default:
			return predictAnd(variableValues);
		}
	}

	private boolean predictOr(List<VariableValue> variableValues) {
		for (Formula formula : formulas) {
			if (formula.predict(variableValues)) {
				return true;
			}
		}
		return false;
	}

	private boolean predictAnd(List<VariableValue> variableValues) {
		for (Formula formula : formulas) {
			if (formula.predict(variableValues) == false) {
				return false;
			}
		}
		return true;
	}

}
