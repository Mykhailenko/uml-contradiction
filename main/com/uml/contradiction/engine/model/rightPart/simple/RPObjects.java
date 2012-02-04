package com.uml.contradiction.engine.model.rightPart.simple;

import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.diagram.ObjectDiagram;
import com.uml.contradiction.engine.model.rightPart.SimpleRightPart;

public class RPObjects extends SimpleRightPart {

	@Override
	public List getSet(List<VariableValue> params) {
		return ObjectDiagram.getObjects();
	}

}
