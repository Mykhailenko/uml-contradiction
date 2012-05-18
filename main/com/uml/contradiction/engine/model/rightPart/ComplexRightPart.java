package com.uml.contradiction.engine.model.rightPart;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.mapping.Mapping;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;

public class ComplexRightPart implements QuantifierRightPart {
	private static final Logger LOGGER = Logger.getRootLogger();
	private List<Variable> boundVariables = new LinkedList<Variable>();
	private List<Mapping> nestedMappings = new LinkedList<Mapping>();

	@SuppressWarnings("rawtypes")
	private List result;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<?> getSet(List<VariableValue> params) throws MappingException {
		List firstValues = new LinkedList();
		for (Variable variable : boundVariables) {
			Object value = null;
			for (VariableValue vv : params) {
				if (vv.variable.equals(variable)) {
					value = vv.value;
					break;
				}
			}
			if (value == null) {
				throw new MappingException("Ololo variable " + variable
						+ " is null");
			} else {
				firstValues.add(value);
			}
		}
		assert firstValues.isEmpty() == false;
		result = new LinkedList();
		rek(nestedMappings.size() - 1, firstValues);
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void rek(int index, List values) throws MappingException {
		if (index >= 0) {
			Mapping mapping = nestedMappings.get(index);
			List list;
			try {
				list = mapping.map(values);
			} catch (MappingException e) {
				LOGGER.error(e);
				throw e;
			}
			if (list != null) {
				for (int i = 0; i < list.size(); ++i) {
					rek(index - 1, Collections.singletonList(list.get(i)));
				}
			}
		} else {
			result.add(values.get(0));
		}
	}

	public List<Variable> getBoundVariables() {
		return boundVariables;
	}

	public void setBoundVariables(List<Variable> boundVariables) {
		this.boundVariables = boundVariables;
	}

	public void setBoundVariables(Variable... boundVariables) {
		for (Variable v : boundVariables) {
			this.boundVariables.add(v);
		}
	}

	public List<Mapping> getNestedMappings() {
		return nestedMappings;
	}

	public void setNestedMappings(List<Mapping> nestedMappings) {
		this.nestedMappings = nestedMappings;
	}

}
