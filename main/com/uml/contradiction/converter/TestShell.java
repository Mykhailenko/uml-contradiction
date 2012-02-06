package com.uml.contradiction.converter;

import com.uml.contradiction.converter.core.ClassParser;

public class TestShell {
	public static void main(String []argc){
		System.out.println("Hello world");
		
		ClassParser cp = new ClassParser();
		cp.parse("E:\\Programming\\Work_spaces\\workspace3_java\\5.JustTestClass.uml");
	}
}
