package com.uml.contradiction.converter;

//import com.uml.contradiction.converter.core.ClassParser;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassDiagram;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.SequenceGraph;
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
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\6.EntBean_Conrol in packNotRoot.uml");
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\Test xmi.uml");
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\8.ManyStereotypes_DefaultPack.uml");
		
		
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\4 ))) Test2 xmi.uml");
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\5.2 ))) Test3 xmi.uml");
		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\7))) Seq_s from frame and package.uml");
		
		
//		File file = new File("main\\Test xmi.uml");
		
		XMIConverter.reset();
		XMIConverter.setFile(file);
				
		
		try {
			XMIConverter.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//печатаем ClassDiagram
		printClassGraf();
		
		printSequenceGraf();
	}
	public static void printClassGraf () {
		List<CClass> class_s = ClassGraph.getClasses();
		List<Association> asssoc_s = ClassGraph.getAssociations();
		List<ClassDiagram> clDiagrams = ClassGraph.getClassDiagrams();
		
		for(CClass cls : class_s)			
			System.out.println(cls);
		
		for(Association ass : asssoc_s)			
			System.out.println(ass);	
		
//		for(ClassDiagram clds : clDiagrams)			
//			System.out.println(clds);
	}	
	public static void printSequenceGraf () {			
		List<Interaction> interList = SequenceGraph.getInteractions();
						
		for(Interaction intr : interList)			
			System.out.println(intr);
	}
	
}
