package com.uml.contradiction.engine.model.predicate;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.predicate.exception.PredicatException;

public class BoundedPredicate implements Formula{
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
	public void setBoundVariable(Variable ... boundVariable) {
		for(Variable v : boundVariable){
			this.boundVariable.add(v);
		}
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
	@Override
	public boolean predict(List<VariableValue> variableValues) {
		List<Object> vars = new LinkedList<Object>();
		for(int i = 0; i < boundVariable.size(); ++i){
			vars.add(findVV(variableValues, boundVariable.get(i)));
		}
		boolean result = false;
		
		try {
			if(isNullExist(variableValues)){
				result = false;
			}
			else if(containsPermittedNullVar(variableValues)) {
				result = true;
			}
			else{
				result = predicate.predict(vars);
			}
		} catch (PredicatException e) {
			System.out.println(e);
		}
		if(negative){
			result = !result;
		}
		return result;
	}
	
	private boolean isNullExist(List<VariableValue> list){
		for(VariableValue o : list){
			if(o.value == null && !this.permittedNullVars.contains(o.variable)){
				System.out.println("CONTAINS!!!!!!!!");
				return true;
			}
		}
		return false;
	}

	private boolean containsPermittedNullVar(List<VariableValue> list){
		for(VariableValue o : list){
			if(o.value == null && 
					this.permittedNullVars.contains(o.variable)){
				System.out.println("CONTAINS PERMITTED!!!!!!!!");
				return true;
			}
		}
		return false;
	}
	
	private Object findVV(List<VariableValue> list, Variable variable){
		for(VariableValue variableValue : list){
			if(variableValue.variable.equals(variable)){
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
