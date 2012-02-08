package com.uml.contradiction.converter;

//import com.uml.contradiction.converter.core.ClassParser;
import com.uml.contradiction.engine.model.diagram.ClassDiagram;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.common.DiagramType;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Collections;

public class TestShell {
	public static void main(String []argc){
//		System.out.println("Hello world");
		
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\5.JustTestClass.uml");
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\10.compos.uml");
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\composMultipl_Role.uml");
		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\N-arn association.uml");
		
		
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
		//печатаем ClassDiagram
		printClassDiagr();
	}
	public static void printClassDiagr () {
		List<CClass> class_s = ClassDiagram.getClasses();
		List<Association> asssoc_s = ClassDiagram.getAssociations();
		
		for(CClass cls : class_s)			
			System.out.println(cls);
		
		for(Association ass : asssoc_s)			
			System.out.println(ass);	
	}	
}
