package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.gui.models.DisplayedCriterion;

public class CriterionSuite {
	public static List<Criterion> getAllCriterion(){
		List<Criterion> result = new LinkedList<Criterion>();
		result.add(new CheckEntryMethodsCriterion());
		result.add(new CheckNoStaticMethod());
		result.add(new CheckSequenceCriterion());
		result.add(new CheckStateMachineCriterion());
		result.add(new CorrectTypeCriterion());
		result.add(new MustExistClassCriterion());
		result.add(new SimpleCriterion());
		result.add(new MustExistMethodCriterion());
		result.add(new PartOfSingleComposite());
		result.add(new ExecutableSequenceCriterion());
		result.add(new NoUtilityInstances());
		return result;
	}

	public static List<DisplayedCriterion> getDisplayedCriterions(){
		List<DisplayedCriterion> result = new LinkedList<DisplayedCriterion>();
		result.add(new DisplayedCriterion(new CheckSequenceCriterion(), "CheckSequenceCriterion", "CheckSequenceCriterion"));
		result.add(new DisplayedCriterion(new CheckStateMachineCriterion(), "CheckStateMachineCriterion", "CheckStateMachineCriterion"));
		result.add(new DisplayedCriterion(new CorrectTypeCriterion(), "CorrectTypeCriterion", "CorrectTypeCriterion"));
		result.add(new DisplayedCriterion(new MustExistClassCriterion(), "MustExistClassCriterion", "MustExistClassCriterion"));
		result.add(new DisplayedCriterion(new SimpleCriterion(), "SimpleCriterion", "SimpleCriterion"));
		result.add(new DisplayedCriterion(new MustExistMethodCriterion(), "MustExistMethodCriterion", "MustExistMethodCriterion"));
		result.add(new DisplayedCriterion(new PartOfSingleComposite(), "PartOfSingleComposite", "PartOfSingleComposite"));
		result.add(new DisplayedCriterion(new ExecutableSequenceCriterion(), "ExecutableSequenceCriterion", "ExecutableSequenceCriterion"));
		result.add(new DisplayedCriterion(new NoUtilityInstances(), "NoUtilityInstances", "NoUtilityInstances"));
		
		return result;
	}
}
