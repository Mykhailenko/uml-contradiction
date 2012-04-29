package com.uml.contradiction.engine.model.predicate;

import java.util.Arrays;
import java.util.List;

import com.uml.contradiction.engine.model.mapping.AttributeClass;
import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.Scope;
import com.uml.contradiction.model.common.UMLClassStereotype;

public class IsStaticAttribute implements Predicate{

	@Override
	public boolean predict(List params) throws PredicatException {
		if(params.get(0) == null) {
			return false;
		}
		
		Attribute attr = (Attribute)(params.get(0));
		if(attr.getScope() == Scope.CLASSIFIER)
			return true;
		else
			return false;
	}

	@Override
	public String getFailDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}