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
	public List map(List args) throws MappingException {
		assert args != null ;
		assert args.size() == 1 : "bad size";
		Object element = args.get(0);
		if(element instanceof OObject){
			OObject oObject = (OObject) element;
			String className = oObject.getClasses().get(0);
			CClass cls = ClassDiagram.findClassByName(className);
			if(cls == null){
				LOGGER.info("Can't find class with name: " + className);
				return null;
			}
			List<CClass> result = new LinkedList<CClass>();
			result.add(cls);
			return result;
		}else{
			LOGGER.error("Unexpected type: " + element.getClass().toString());
			throw new MappingException("Unexpected type: " + element.getClass().toString());
		}
	}

}
