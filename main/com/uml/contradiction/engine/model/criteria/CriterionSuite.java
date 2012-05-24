package com.uml.contradiction.engine.model.criteria;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.gui.models.DisplayedCriterion;

public class CriterionSuite {
	public static List<DisplayedCriterion> getDisplayedCriterions() {
		List<DisplayedCriterion> result = new LinkedList<DisplayedCriterion>();
		result.add(new DisplayedCriterion(new CheckMessages(), "CheckMessages",
				"Each message must refers to the appropriate class method"));
		result.add(new DisplayedCriterion(new CheckMessageVisibility(), "CheckMessageVisibility",
				"Each message must call the method with the appropriate scope"));
		result.add(new DisplayedCriterion(
				new CheckTriggers(),
				"CheckTriggers",
				"Each transition must refers to the appropriate class method"));
		result.add(new DisplayedCriterion(new CorrectType(), "CorrectType",
				"Value of each object's attribute should belong to appropriate type"));
		result.add(new DisplayedCriterion(new MustExistClassCriterion(),
				"MustExistClassCriterion", "MustExistClassCriterion"));
		result.add(new DisplayedCriterion(new PartOfSingleComposite(),
				"PartOfSingleComposite", "PartOfSingleComposite"));
//		result.add(new DisplayedCriterion(new ExecutableSequence(),
//				"ExecutableSequenceCriterion", "ExecutableSequenceCriterion"));
		result.add(new DisplayedCriterion(new NoUtilityInstances(),
				"NoUtilityInstances", "NoUtilityInstances"));
		result.add(new DisplayedCriterion(new UtilityStaticAttrOnly(),
				"UtilityStaticAttrOnly",
				"A class with stereotype \"utility\" may have only static attributes"));
		result.add(new DisplayedCriterion(new UtilityStaticMthdOnly(),
				"UtilityStaticMthdOnly",
				"A class with stereotype \"utility\" may have only static methods"));
		result.add(new DisplayedCriterion(new CorrectLifeLines(),
				"CorrectLifeLines",
				"Each lifeline should belong to exist class or object"));
		result.add(new DisplayedCriterion(new CompositeMultOneOnly(),
				"CompositeMultOneOnly",
				"The multiplicity of a composite must be equal or less than 1."));

		return result;
	}

	public static String getDescripritionOfCriterion(Criterion criterion) {
		for (DisplayedCriterion dc : getDisplayedCriterions()) {
			if (dc.getCriterion().equals(criterion)) {
				return dc.getDesctiption();
			}
		}
		return "";
	}

	public static String getNameOfCriterion(Criterion criterion) {
		for (DisplayedCriterion dc : getDisplayedCriterions()) {
			if (dc.getCriterion().equals(criterion)) {
				return dc.getName();
			}
		}
		return "";
	}

	public static List<Criterion> getAllCriterion() {
		List<Criterion> result = new LinkedList<Criterion>();
		for (DisplayedCriterion dc : getDisplayedCriterions()) {
			result.add(dc.getCriterion());
		}
		return result;
	}
}
