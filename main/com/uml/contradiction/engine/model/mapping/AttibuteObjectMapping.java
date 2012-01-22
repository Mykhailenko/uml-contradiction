package com.uml.contradiction.engine.model.mapping;

import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.object.OObject;

public class AttibuteObjectMapping implements Mapping {

	@Override
	public List map(Object element) throws MappingException{
		assert element != null;
		if(element instanceof OObject){
			OObject oobject = (OObject) element;
			return oobject.getAttributes();
		}else{
			throw new MappingException("Unexpected type: " + element.getClass().toString());
		}
	}

}
