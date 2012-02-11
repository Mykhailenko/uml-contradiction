package com.uml.contradiction.converter;

//import com.uml.contradiction.converter.core.ClassParser;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
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
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\N-arn association.uml");
		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\6.EntBean_Conrol in packNotRoot.uml");
		
		
		XMIConverter.reset();
		XMIConverter.setFile(file);
				
		
		try {
			XMIConverter.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//печатаем ClassDiagram
		printClassGraf();
	}
	public static void printClassGraf () {
		List<CClass> class_s = ClassGraph.getClasses();
		List<Association> asssoc_s = ClassGraph.getAssociations();
		
		for(CClass cls : class_s)			
			System.out.println(cls);
		
		for(Association ass : asssoc_s)			
			System.out.println(ass);	
	}	
}
