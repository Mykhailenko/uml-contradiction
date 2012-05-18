package com.uml.contradiction.engine.model.mapping;

import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.object.OObject;

public class AttributeObject implements Mapping {

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List args) throws MappingException {
		assert args != null;
		assert args.size() == 1 : "bad size";
		Object element = args.get(0);
		if (element instanceof OObject) {
			OObject oobject = (OObject) element;
			return oobject.getAttributes();
		} else {
			throw new MappingException("Unexpected type: "
					+ element.getClass().toString());
		}
	}

}
