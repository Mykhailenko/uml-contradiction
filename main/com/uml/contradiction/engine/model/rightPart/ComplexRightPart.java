package com.uml.contradiction.engine.model.rightPart;
import java.util.*;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.mapping.Mapping;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;

public class ComplexRightPart implements QuantifierRightPart {
	private static final Logger LOGGER = Logger.getRootLogger();
	protected Variable boundVariable;
	protected List<Mapping> nestedMappings;
	
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
		rek(0,firstValue);
		return null;
	}
	private void rek(int index, Object value) throws MappingException{
		assert index > 0;
		if(index < nestedMappings.size()){
			Mapping mapping = nestedMappings.get(index);
			try {
				List list = mapping.map(value);
			} catch (MappingException e) {
				LOGGER.error(e);
				throw e;
			}
		}else{
			
		}
	}

}