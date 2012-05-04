package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.gui.models.DisplayedCriterion;

public class CriterionSuite {
	public static List<Criterion> getAllCriterion(){
		List<Criterion> result = new LinkedList<Criterion>();
		result.add(new CheckAttributeOrAssociate());
		result.add(new CheckEntryMethodsCriterion());
		result.add(new CheckNoStaticMethod());
		result.add(new CheckSequenceCriterion());
		result.add(new CheckStateMachineCriterion());
		result.add(new CorrectType());
		result.add(new MustExistClassCriterion());
		result.add(new SimpleCriterion());
		result.add(new MustExistMethodCriterion());
		result.add(new PartOfSingleComposite());
		result.add(new ExecutableSequenceCriterion());
		result.add(new NoUtilityInstances());
		result.add(new UtilityStaticAttrOnly());
		result.add(new UtilityStaticMthdOnly());
		return result;
	}

	public static List<DisplayedCriterion> getDisplayedCriterions(){
		List<DisplayedCriterion> result = new LinkedList<DisplayedCriterion>();
		result.add(new DisplayedCriterion(new CheckSequenceCriterion(), "CheckSequenceCriterion", "CheckSequenceCriterion"));
		result.add(new DisplayedCriterion(new CheckStateMachineCriterion(), "CheckStateMachineCriterion", "CheckStateMachineCriterion"));
		result.add(new DisplayedCriterion(new CorrectType(), "CorrectType", "Value of each object's attribute should belong to appropriate type"));
		result.add(new DisplayedCriterion(new MustExistClassCriterion(), "MustExistClassCriterion", "MustExistClassCriterion"));
		result.add(new DisplayedCriterion(new SimpleCriterion(), "SimpleCriterion", "SimpleCriterion"));
		result.add(new DisplayedCriterion(new MustExistMethodCriterion(), "MustExistMethodCriterion", "MustExistMethodCriterion"));
		result.add(new DisplayedCriterion(new PartOfSingleComposite(), "PartOfSingleComposite", "PartOfSingleComposite"));
		result.add(new DisplayedCriterion(new ExecutableSequenceCriterion(), "ExecutableSequenceCriterion", "ExecutableSequenceCriterion"));
		result.add(new DisplayedCriterion(new NoUtilityInstances(), "NoUtilityInstances", "NoUtilityInstances"));
		result.add(new DisplayedCriterion(new UtilityStaticAttrOnly(), "UtilityStaticAttrOnly", "A class with stereotype \"utility\" may have only static attributes"));
		result.add(new DisplayedCriterion(new UtilityStaticMthdOnly(), "UtilityStaticMthdOnly", "A class with stereotype \"utility\" may have only static methods"));
		result.add(new DisplayedCriterion(new CorrectLifeLines(), "CorrectLifeLines", "Each lifeline should belong to exist class or object"));
		return result;
	}
	public static String getDescrOfCriterion(Criterion criterion){
		for(DisplayedCriterion dc : getDisplayedCriterions()){
			if(dc.getCriterion().equals(criterion)){
				return dc.getDesctiption();
			}
		}
		return "";
	}
	public static String getNameOfCriterion(Criterion criterion){
		for(DisplayedCriterion dc : getDisplayedCriterions()){
			if(dc.getCriterion().equals(criterion)){
				return dc.getName();
			}
		}
		return "";
	}
	public static List<Criterion> getDisplayedCriterionTRICKY(){
		List<Criterion> result = new LinkedList<Criterion>();
		for(DisplayedCriterion dc : getDisplayedCriterions()){
			result.add(dc.getCriterion());
		}
		return result;
	}
}
