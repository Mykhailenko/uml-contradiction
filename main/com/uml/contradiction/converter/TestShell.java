package com.uml.contradiction.converter;

//import com.uml.contradiction.converter.core.ClassParser;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.common.DiagramType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class TestShell {
	public static void main(String []argc){
//		System.out.println("Hello world");
		
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\5.JustTestClass.uml");
		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\10.compos.uml");
		
		XMIConverter.reset();
		XMIConverter.setFile(file);
		
		List<DiagramForChoise> selectedDiagram = new ArrayList<DiagramForChoise>();
		DiagramForChoise dfch = new DiagramForChoise();
		dfch.setId(1);
		dfch.setName("Class Diagram1");
		dfch.setType(DiagramType.CLASS);
		selectedDiagram.add(dfch);
		
		try {
			XMIConverter.parse(selectedDiagram);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
