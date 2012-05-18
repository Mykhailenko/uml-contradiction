package com.uml.contradiction.engine.model.rightPart.simple;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.engine.model.rightPart.QuantifierRightPart;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.InteractionElement;
import com.uml.contradiction.model.sequence.Message;
import com.uml.contradiction.model.sequence.SequenceGraph;

public class RPMessages implements QuantifierRightPart {
	@SuppressWarnings("rawtypes")
	@Override
	public List getSet(List<VariableValue> params) throws MappingException {
		List<Message> result = new LinkedList<Message>();
		for (Interaction interaction : SequenceGraph.getInteractions()) {
			for (InteractionElement element : interaction.getChilds()) {
				if (element.getType().equals(InteractionElement.Type.MESSAGE)) {
					result.add((Message) element);
				}
			}
		}

		return result;
	}

}
