package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;

public class CompMultCorrect implements Predicate {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean predict(List params) throws PredicatException {
		Association as = (Association) (params.get(0));
		boolean correct1 = false;
		boolean correct2 = false;

		System.out.println("Check for classes "
				+ as.getEnd1().getAssociatedClass() + " and "
				+ as.getEnd2().getAssociatedClass());
		if (as.getEnd1().getMultiplicity() != null) {
			System.out.println("Multipl: class: "
					+ as.getEnd1().getAssociatedClass().getName() + " "
					+ as.getEnd1().getMultiplicity().toString());
		}
		if (as.getEnd2().getMultiplicity() != null) {
			System.out.println("Multipl: class: "
					+ as.getEnd2().getAssociatedClass().getName() + " "
					+ as.getEnd2().getMultiplicity().toString());
			System.out.println(as.getEnd2().getMultiplicity().getUpperBound() <= 1);
		}
		if (as.getEnd1().getAggregationKind() != null) {
			System.out.println("Aggr: class: "
					+ as.getEnd1().getAggregationKind()+"\n"+ as.getEnd1().getAssociatedClass());
		}
		if (as.getEnd2().getAggregationKind() != null) {
			System.out.println("Aggr: class: "
					+ as.getEnd2().getAggregationKind() +"\n"+ as.getEnd2().getAssociatedClass());
		}
		
		if (as.getEnd1().getAggregationKind() != null
				&& as.getEnd1().getAggregationKind() == AggregationKind.COMPOSITE) {

			if (as.getEnd1().getMultiplicity() == null
					|| as.getEnd1().getMultiplicity().getUpperBound() <= 1) {
				
				correct1 = true;
			}
		} else {
			correct1 = true;
		}

		if (as.getEnd2().getAggregationKind() != null
				&& as.getEnd2().getAggregationKind() == AggregationKind.COMPOSITE) {

			if (as.getEnd2().getMultiplicity() == null
					|| as.getEnd2().getMultiplicity().getUpperBound() <= 1) {
				correct2 = true;
			}
		} else {
			correct2 = true;
		}
System.out.println(correct1 + " " + correct2);
		return correct1 & correct2;
	}

	@Override
	public String getFailDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}