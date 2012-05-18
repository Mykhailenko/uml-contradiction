package com.uml.contradiction.converter.core;

import com.uml.contradiction.converter.core.classes.ClassParser;
import com.uml.contradiction.converter.core.object.ObjectParser;
import com.uml.contradiction.converter.core.sequence.SequenceParser;
import com.uml.contradiction.converter.core.statemachine.StatemachineParser;

public class ParsersTool {
	private static ClassParser classParser;
	private static SequenceParser sequenceParser;
	private static ObjectParser objectParser;
	private static StatemachineParser statemachineParser;

	public static ClassParser getInstanceClassParser() {
		if (classParser == null) {
			classParser = new ClassParser();
		}
		return classParser;
	}

	public static void setClassParser(ClassParser classParser) {
		ParsersTool.classParser = classParser;
	}

	public static SequenceParser getInstanceSequenceParser() {
		if (sequenceParser == null) {
			sequenceParser = new SequenceParser();
		}
		return sequenceParser;
	}

	public static ObjectParser getInstanceObjectParser() {
		if (objectParser == null) {
			objectParser = new ObjectParser();
		}
		return objectParser;
	}

	public static StatemachineParser getInstanceStatemachineParser() {
		if (statemachineParser == null) {
			statemachineParser = new StatemachineParser();
		}
		return statemachineParser;
	}

	public static void setSequenceParser(SequenceParser sequenceParser) {
		ParsersTool.sequenceParser = sequenceParser;
	}

}
