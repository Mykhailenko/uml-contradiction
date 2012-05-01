package com.uml.contradiction.engine.model.rightPart.simple;

import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.engine.model.rightPart.QuantifierRightPart;
import com.uml.contradiction.model.sequence.SequenceGraph;

public class RPInteractions implements QuantifierRightPart {

	@SuppressWarnings("rawtypes")
	@Override
	public List getSet(List<VariableValue> params) throws MappingException {
		return SequenceGraph.getInteractions();
	}

}
