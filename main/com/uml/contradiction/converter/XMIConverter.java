package com.uml.contradiction.converter;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.uml.contradiction.gui.models.DiagramForChoise;

public class XMIConverter {
	private static File file = null;
	public static void setFile(File file){
		assert file != null;
		XMIConverter.file = file;
	}
	public static List<DiagramForChoise> getAvailableDiagram() throws Exception{
		assert XMIConverter.file != null : "file should be assigned before";
		return Collections.emptyList();
	}
	public static List<Object> parse(List<DiagramForChoise> selectedDiagram) throws Exception{
		assert XMIConverter.file != null : "file should be assigned before";
		return Collections.emptyList();
	}
	public static void reset(){
		XMIConverter.file = null;
	}
}
