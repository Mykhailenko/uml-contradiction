package com.uml.contradiction.engine.model.mapping;

import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.sequence.Interaction;

public class LifeLineFromInteraction implements Mapping {
	private static final Logger LOGGER = Logger.getRootLogger();

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List list) throws MappingException {
		assert list != null;
		assert list.size() == 1;
		Object element = list.get(0);
		if (element instanceof Interaction) {
			Interaction interaction = (Interaction) element;
			return interaction.getLifeLines();
		} else {
			LOGGER.error("Unexpected type: " + element.getClass().toString());
			throw new MappingException("Unexpected type: "
					+ element.getClass().toString());
		}
	}

}
