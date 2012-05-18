package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.InteractionElement;
import com.uml.contradiction.model.sequence.Message;
import com.uml.contradiction.model.sequence.SequenceGraph;
import com.uml.contradiction.model.statemachine.Transition;

public class MessagesForTransition implements Mapping {

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List list) throws MappingException {
		assert list != null;
		assert list.size() == 1;
		Object first = list.get(0);
		if ((first instanceof Transition) == false) {
			throw new MappingException("Unexpected type: "
					+ first.getClass().toString());
		}
		List<Message> result = new LinkedList<Message>();
		Transition transition = (Transition) first;
		CClass transitionClass = transition.getStateMachine().getcClass();
		for (Interaction interaction : SequenceGraph.getInteractions()) {
			for (InteractionElement ie : interaction.getChilds()) {
				if (ie.isMessage()) {
					Message message = (Message) ie;
					CClass messageClass = message.getTarget().getcClass();
					if (messageClass.equals(transitionClass)) {
						result.add(message);
					}
				}
			}
		}
		return result;
	}

}
