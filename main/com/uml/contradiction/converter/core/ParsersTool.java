package com.uml.contradiction.converter.core;

import com.uml.contradiction.converter.core.classes.ClassParser;

public class ParsersTool {
	private static ClassParser classParser = new ClassParser();

	public static ClassParser getClassParser() {
		return classParser;
	}

	public static void setClassParser(ClassParser classParser) {
		ParsersTool.classParser = classParser;
	}
	
	
}
