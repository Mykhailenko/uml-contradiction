package com.uml.contradiction.converter;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.diagram.*;
import com.uml.contradiction.converter.core.*;

public class XMIConverter {
	private static File file = null;
	public static void setFile(File file){
		assert file != null;
		XMIConverter.file = file;
	}
//	Hello world
	public static List<DiagramForChoise> getAvailableDiagram() throws Exception{
		assert XMIConverter.file != null : "file should be assigned before";
		return Collections.emptyList();
	}
	public static List<Object> parse(List<DiagramForChoise> selectedDiagram) throws Exception{
		assert XMIConverter.file != null : "file should be assigned before";
		
		List<CClass> class_s = ClassDiagram.getClasses();
		List<Association> asssoc_s = ClassDiagram.getAssociations();
				
		for(int i=0; i<selectedDiagram.size(); i++){
			if(selectedDiagram.get(i).getType() == DiagramType.CLASS){
				// Добавить название диаграммы 
				ClassParser clPars = new ClassParser();
				clPars.parse(file.toString());
			}
		}
		
		return Collections.emptyList();
	}
	public static void reset(){
		XMIConverter.file = null;
	}
}
