package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationEnd;

public class CompositeEndAssociation implements Mapping {

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List map(List args) throws MappingException {
		assert args != null;

		Object element = args.get(0);
		if (element instanceof Association) {
			List res = new LinkedList();
			AssociationEnd end1 = ((Association) element).getEnd1();
			AssociationEnd end2 = ((Association) element).getEnd2();

			if (end1.getAggregationKind() == AggregationKind.COMPOSITE) {
				res.add(end1);
			}
			if (end2.getAggregationKind() == AggregationKind.COMPOSITE) {
				res.add(end2);
			}

			return res;
		}

		else {
			throw new MappingException("Unexpected type: "
					+ element.getClass().toString());
		}

	}
}
