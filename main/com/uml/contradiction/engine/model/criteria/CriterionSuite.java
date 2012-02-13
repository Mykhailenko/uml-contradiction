package com.uml.contradiction.engine.model.criteria;



import java.util.LinkedList;
import java.util.List;n;
import com.uml.contradiction.gui.models.DisplayedCriterion;

public class CriterionSuite {
	public static List<Criterion> getAllCriterion(){
		List<Criterion> result = new LinkedList<Criterion>();
		result.add(new CorrectTypeCriterion());
		result.add(new MustExistClassCriterion());
		result.add(new SimpleCriterion());
		result.add(new MustExistMethodCriterion());
		result.add(new PartOfSingleComposite());
		result.add(new ExecutableSequenceCriterion());
		return result;
	}
	
	public static List<DisplayedCriterion> getDisplayedCriterions(){
		List<DisplayedCriterion> result = new LinkedList<DisplayedCriterion>();
		result.add(new DisplayedCriterion(new MustExistClassCriterion(), "Class must exist", "SOME DISCRIPTION"));		
		result.add(new DisplayedCriterion(new SimpleCriterion(), "Class must exist", "SOME DISCRIPTION"));		
		result.add(new DisplayedCriterion(new CorrectTypeCriterion(), "Class must exist", "SOME DISCRIPTION"));		
		result.add(new DisplayedCriterion(new MustExistMethodCriterion(), "Class must exist", "SOME DISCRIPTION"));		
		result.add(new DisplayedCriterion(new PartOfSingleComposite(), "Class must exist", "SOME DISCRIPTION"));		
		result.add(new DisplayedCriterion(new ExecutableSequenceCriterion(), "Class must exist", "SOME DISCRIPTION"));		
		return result;
	}
}


