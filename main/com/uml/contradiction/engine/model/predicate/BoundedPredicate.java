package com.uml.contradiction.engine.model.predicate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.predicate.exception.PredicatException;

public class BoundedPredicate implements Formula {
//	private static final Logger LOGGER = Logger.getRootLogger();
	private List<Variable> boundVariable = new LinkedList<Variable>();
	private List<Variable> permittedNullVars = new LinkedList<Variable>();

	private boolean negative = false;
	private Predicate predicate;

	public boolean isNegative() {
		return this.negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}

	public List<Variable> getBoundVariable() {
		return boundVariable;
	}

	public void setBoundVariable(List<Variable> boundVariable) {
		this.boundVariable = boundVariable;
	}

	public void setBoundVariable(Variable... boundVariable) {
		for (Variable v : boundVariable) {
			this.boundVariable.add(v);
		}
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
	public int trickyMethod(List<VariableValue> lst){
		List<VariableValue> newLst = new ArrayList<VariableValue>(lst);
		for(int i = newLst.size() - 1; i >= 0; --i){
			boolean notFound = true;
			for(Variable v : boundVariable){
				if(newLst.get(i).variable == v){
					notFound = false;
					break;
				}
			}
			if(notFound){
				newLst.remove(i);
			}
		}
		int result = 0;
		for(int i = 0; i < newLst.size(); ++i){
			if(newLst.get(i).value == null){
				if(permittedNullVars.contains(newLst.get(i).variable)){
					result = 1;
				}else{
					result = -1;
				}
			}
		}
		return result;		
	}
	@Override
	public boolean predict(List<VariableValue> variableValues) {
		List<Object> vars = new LinkedList<Object>();
		for (int i = 0; i < boundVariable.size(); ++i) {
			vars.add(findVV(variableValues, boundVariable.get(i)));
		}
		int r = trickyMethod(variableValues);
		switch (r) {
		case 1:
			return true;
		case -1:
			return false;
		case 0:
		default:
		}
		boolean result = false;

		try {
			if (isNullExist(variableValues)) {
				result = false;
			} else if (containsPermittedNullVar(variableValues)) {
				result = true;
			} else {
				result = predicate.predict(vars);
			}
		} catch (PredicatException e) {
			System.out.println(e);
		}
		if (negative) {
			result = !result;
		}
		return result;
	}

	private boolean isNullExist(List<VariableValue> list) {
		for (VariableValue o : list) {
			if (o.value == null && !isVarialbePermitterToNull(o.variable)) {
//				System.out.println("CONTAINS!!!!!!!!");
				return true;
			}
		}
		return false;
	}

	private boolean containsPermittedNullVar(List<VariableValue> list) {
		for (VariableValue o : list) {
			if (o.value == null && permittedNullVars.contains(o.variable)) {
//				System.out.println("CONTAINS PERMITTED!!!!!!!!");
				return true;
			}
		}
		return false;
	}
	private boolean isVarialbePermitterToNull(Variable v){
		boolean result = permittedNullVars.contains(v);
		return result;
	}
	private Object findVV(List<VariableValue> list, Variable variable) {
		for (VariableValue variableValue : list) {
			if (variableValue.variable.equals(variable)) {
				return variableValue.value;
			}
		}
		return null;
	}

	public List<Variable> getPermittedNullVars() {
		return permittedNullVars;
	}

	public void setPermittedNullVars(List<Variable> permittedNullVars) {
		this.permittedNullVars = permittedNullVars;
	}

}
