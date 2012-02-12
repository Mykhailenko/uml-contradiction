package com.uml.contradiction.tests;

import java.io.File;

import org.junit.Test;

import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;

public class UNTest {
	@Test
	public void t(){
		File file = new File("g.uml");
		
		XMIConverter.reset();
		XMIConverter.setFile(file);
				
		
		try {
			XMIConverter.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(CClass cClass : ClassGraph.getClasses()){
			System.out.println(cClass.getFullName());
		}
	}
}
