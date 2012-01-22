package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.diagram.ClassDiagram;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.OObject;

public class ClassObjectMapping implements Mapping {
	private static final Logger LOGGER = Logger.getRootLogger();
	
	@Override
	public List map(Object element) throws MappingException {
		assert element != null;
		if(element instanceof OObject){
			OObject oObject = (OObject) element;
			String className = oObject.getName();
			List<CClass> listClass = ClassDiagram.getClasses();
			for(CClass cClass : listClass){
				if(cClass.getName().equals(className)){
					List<CClass> result = new LinkedList<CClass>();
					result.add(cClass);
					return result;
				}
			}
			LOGGER.info("Can't find class with name: " + className);
			return null;
		}else{
			LOGGER.error("Unexpected type: " + element.getClass().toString());
			throw new MappingException("Unexpected type: " + element.getClass().toString());
		}
	}

}
