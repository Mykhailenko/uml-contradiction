package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationEnd;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.object.OObject;

public class ClassAssociationMapping implements Mapping {
	
	@Override
	public List map(List args) throws MappingException {
		assert args != null ;
		assert args.size() == 1 : "bad size";
		
		Object element = args.get(0);
		
		if(element instanceof CClass){
			OObject oObject = (OObject) element;
			List<Association> associations = ClassGraph.getAssociations();
			List<Association> res = new LinkedList<Association>();
			
			for(Association association : associations) {
				AssociationEnd end1 = association.getEnd1();
				AssociationEnd end2 = association.getEnd2();
				
				if(end1.getAssociatedClass().equals(element) || end2.getAssociatedClass().equals(element)){
					res.add(association);
				}
			}
			
			return res;
		}
		
		else{
			throw new MappingException("Unexpected type: " + element.getClass().toString());
		}
	}

}