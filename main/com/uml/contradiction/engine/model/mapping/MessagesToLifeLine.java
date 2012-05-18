package com.uml.contradiction.engine.model.mapping;

import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.engine.model.predicate.IsExecutableLifeLine;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.LifeLine;

public class MessagesToLifeLine implements Mapping {

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List list) throws MappingException {
		assert list != null;
		assert list.size() == 2;
		Object first = list.get(0);
		if ((first instanceof LifeLine) == false) {
			throw new MappingException("Unexpected type: "
					+ first.getClass().toString());
		}
		Object second = list.get(1);
		if ((second instanceof Interaction) == false) {
			throw new MappingException("Unexpected type: "
					+ second.getClass().toString());
		}
		LifeLine lifeLine = (LifeLine) first;
		Interaction interaction = (Interaction) second;
		return IsExecutableLifeLine.findAllMessagesTargetLifeLine(interaction,
				lifeLine);
	}

}
