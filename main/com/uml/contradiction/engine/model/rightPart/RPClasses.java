package com.uml.contradiction.engine.model.rightPart;

import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.diagram.ClassDiagram;

public class RPClasses extends SimpleRightPart{
	@Override
	public List getSet(List<VariableValue> params) {
		return ClassDiagram.getClasses();
	}
}