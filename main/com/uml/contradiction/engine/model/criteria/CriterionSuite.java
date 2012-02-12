package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

public class CriterionSuite {
	public static List<Criterion> getAllCriterion(){
		List<Criterion> result = new LinkedList<Criterion>();
		result.add(new CheckStateMachineCriterion());
		result.add(new CorrectTypeCriterion());
		result.add(new MustExistClassCriterion());
		result.add(new SimpleCriterion());
		result.add(new MustExistMethodCriterion());
		result.add(new PartOfSingleComposite());
		result.add(new ExecutableSequenceCriterion());
		return result;
	}
}
