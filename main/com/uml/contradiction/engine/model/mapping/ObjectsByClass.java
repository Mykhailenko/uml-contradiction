package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.OObject;
import com.uml.contradiction.model.object.ObjectGraph;

public class ObjectsByClass implements Mapping {
	private static final Logger LOGGER = Logger.getRootLogger();

	@Override
	@SuppressWarnings("rawtypes")
	public List map(List args) throws MappingException {
		assert args != null;
		assert args.size() == 1 : "bad size";
		Object element = args.get(0);
		if (element instanceof CClass) {
			CClass cls = (CClass) element;
//			if (cls == null) {
//				LOGGER.info("Can't find class!");
//				return null;
//			}
			List<OObject> result = new LinkedList<OObject>();
			for (OObject ob : ObjectGraph.getObjects()) {
				if (ob.getClasses().equals(cls)) {
					result.add(ob);
				}
			}
			return result;
		} else {
			LOGGER.error("Unexpected type: " + element.getClass().toString());
			throw new MappingException("Unexpected type: "
					+ element.getClass().toString());
		}
	}

}
