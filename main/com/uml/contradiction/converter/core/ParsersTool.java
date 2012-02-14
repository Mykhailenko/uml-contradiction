package com.uml.contradiction.converter.core;

import com.uml.contradiction.converter.core.classes.ClassParser;
import com.uml.contradiction.converter.core.sequence.SequenceParser;

public class ParsersTool {
	private static ClassParser classParser;
	private static SequenceParser sequenceParser;

	public static ClassParser getInstanceClassParser() {
		if(classParser == null){
			classParser = new ClassParser();
		}
		return classParser;
	}

	public static void setClassParser(ClassParser classParser) {
		ParsersTool.classParser = classParser;
	}

	public static SequenceParser getInstanceSequenceParser() {
		if(sequenceParser == null){
			sequenceParser = new SequenceParser();
		}
		return sequenceParser;
	}

	public static void setSequenceParser(SequenceParser sequenceParser) {
		ParsersTool.sequenceParser = sequenceParser;
	}
	
}
