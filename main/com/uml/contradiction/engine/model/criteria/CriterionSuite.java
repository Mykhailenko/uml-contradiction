package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

public class CriterionSuite {
	public static List<Criterion> getAllCriterion(){
		List<Criterion> result = new LinkedList<Criterion>();
		result.add(new CheckSequenceCriterion());
		result.add(new CheckStateMachineCriterion());
		result.add(new CorrectTypeCriterion());
		result.add(new MustExistClassCriterion());
		result.add(new SimpleCriterion());
		result.add(new MustExistMethodCriterion());
		result.add(new PartOfSingleComposite());
		result.add(new ExecutableSequenceCriterion());
		return result;
	}
	public static String getCriterionName(Criterion criterion){
		switch (criterion.getInternalID()) {
		case -2:
			return "UNSPECIFIED YET";
		default:
			return "OLOLO";
		}
	}
	public static String getCriterionDescription(Criterion criterion){
		switch (criterion.getInternalID()) {
		case -2:
			return "UNSPECIFIED YET";
		default:
			return "OLOLO";
		}
	
	}
}
