package com.uml.contradiction.engine.model.rightPart.simple;

import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.diagram.ClassDiagram;
import com.uml.contradiction.engine.model.rightPart.SimpleRightPart;

public class RPClasses extends SimpleRightPart{
	@Override
	public List getSet(List<VariableValue> params) {
		return ClassDiagram.getClasses();
	}
}
