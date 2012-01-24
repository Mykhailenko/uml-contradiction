package com.uml.contradiction.engine.model.rightPart;
import java.util.*;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.mapping.Mapping;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;

public class ComplexRightPart implements QuantifierRightPart {
	private static final Logger LOGGER = Logger.getRootLogger();
	private Variable boundVariable;
	private List<Mapping> nestedMappings = new LinkedList<Mapping>();
	
	private List result;
	@Override
	public List<?> getSet(List<VariableValue> params) throws MappingException {
		Object firstValue = null;
		for(VariableValue vv : params){
			if(vv.variable.equals(boundVariable)){
				firstValue = vv.value;
			}
		}
		assert firstValue != null;
		result = new LinkedList();
		rek(nestedMappings.size()-1,firstValue);
		return result;
	}
	private void rek(int index, Object value) throws MappingException{
		if(index >= 0){
			Mapping mapping = nestedMappings.get(index);
			List list;
			try {
				list = mapping.map(Collections.singletonList(value));
			} catch (MappingException e) {
				LOGGER.error(e);
				throw e;
			}
			assert list != null;
			for(int i = 0; i < list.size(); ++i){
				rek(index-1, list.get(i));
			}
		}else{
			result.add(value);
		}
	}
	public Variable getBoundVariable() {
		return boundVariable;
	}
	public void setBoundVariable(Variable boundVariable) {
		this.boundVariable = boundVariable;
	}
	public List<Mapping> getNestedMappings() {
		return nestedMappings;
	}
	public void setNestedMappings(List<Mapping> nestedMappings) {
		this.nestedMappings = nestedMappings;
	}
	
}