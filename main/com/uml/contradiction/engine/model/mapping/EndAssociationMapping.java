package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationEnd;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.object.OObject;

public class EndAssociationMapping implements Mapping {
	
	@Override
	public List map(List args) throws MappingException {
		assert args != null ;
		assert args.size() == 1 : "bad size";
		
		Object element = args.get(0);
		
		if(element instanceof Association){
			Association association = (Association) element;
			List<AssociationEnd> res = new LinkedList<AssociationEnd>();
			res.add(association.getEnd1());
			res.add(association.getEnd2());
			
			return res;
		}
		
		else{
			throw new MappingException("Unexpected type: " + element.getClass().toString());
		}
	}

}
