package com.uml.contradiction.engine.model.mapping;

import java.util.Collections;
import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.sequence.LifeLine;

public class ClassOfLifeLine implements Mapping {

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List list) throws MappingException {
		assert list != null;
		assert list.size() == 1;
		Object first = list.get(0);
		if ((first instanceof LifeLine) == false) {
			throw new MappingException("Unexpected type: "
					+ first.getClass().toString());
		}
		LifeLine lifeLine = (LifeLine) first;
		return Collections.singletonList(lifeLine.getcClass());
	}
}
