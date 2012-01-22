package com.uml.contradiction.engine.model.mapping;

import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.cclass.CClass;

public class AttributeClassMapping implements Mapping {

	@Override
	public List map(Object element) throws MappingException {
		assert element != null;
		if(element instanceof CClass){
			CClass cClass = (CClass) element;
			return cClass.getAttributes();
		}else{
			throw new MappingException("Unexpected type: " + element.getClass().toString());
		}
	}

}
