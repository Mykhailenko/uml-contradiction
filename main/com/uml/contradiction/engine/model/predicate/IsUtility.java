package com.uml.contradiction.engine.model.predicate;

import java.util.Arrays;
import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.common.Stereotype;
import com.uml.contradiction.model.common.UMLClassStereotype;
import com.uml.contradiction.model.object.OObject;

public class IsUtility implements Predicate{

	@Override
	public boolean predict(List params) throws PredicatException {
		CClass cl = (CClass)(params.get(0));
		if(cl.getStereotypes() == null)
			return false;
		List<Object> strs = Arrays.asList(cl.getStereotypes().toArray());
		for(Object str:strs) {
			if(str.equals(UMLClassStereotype.UTILITY)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String getFailDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
