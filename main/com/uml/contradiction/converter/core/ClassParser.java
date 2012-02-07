package com.uml.contradiction.converter.core;

import java.io.File;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.Visibility;
import com.uml.contradiction.engine.model.diagram.*;
import com.uml.contradiction.gui.models.DiagramForChoise;

import org.apache.log4j.Logger;
import org.w3c.dom.*;

public class ClassParser implements CoreParser {
	
	private Map<String, CClass> classesWithId = new LinkedHashMap <String, CClass>();
	private Map<String, Association> assocesWithId = new LinkedHashMap <String, Association>();
	
	private Boolean AddToClassDiagram(Map<String, CClass> classesWithId) {
		return null;
	}
	
	@Override
	public List<Object> parse(DiagramForChoise diagrForSearch, Element umlModelEl) {

		LinkedHashMap<String, ArrayList<String>> clas_s;
		ArrayList<String> info_cls;
		try{
					
		NodeList pack_nodes = umlModelEl.getElementsByTagName("packagedElement");
//		System.out.println(pack_nodes.item(0).getLastChild().getNodeName());
		
		for (int temp = 0; temp < pack_nodes.getLength(); temp++) {
												//разбор одного класса
			Element curPackEl = (Element)pack_nodes.item(temp);
			
			if(curPackEl.getAttribute("xmi:type").equals("uml:Class")){
				
			CClass curCClass = new CClass();
			CClass testCClass;
			String id4class;
											//заполняем поля CClass
			curCClass.setName(curPackEl.getAttribute("name"));
			
			String visibty = curPackEl.getAttribute("visibility");
			
			if(visibty.equals("public")) curCClass.setVisibility(Visibility.PUBLIC);
			if(visibty.equals("private")) curCClass.setVisibility(Visibility.PRIVATE);
			if(visibty.equals("protected")) curCClass.setVisibility(Visibility.PROTECTED);
		
			id4class = curPackEl.getAttribute("xmi:id");
			classesWithId.put(id4class, curCClass);
			
			testCClass = classesWithId.get(id4class);
			System.out.println(testCClass.toString());
			
//			 clas_s = new LinkedHashMap<String, ArrayList<String>>();
//			 info_cls = new ArrayList<String>();
//			 
//			System.out.println(curPackEl.getAttribute("name"));
//			
//			info_cls.add(curPackEl.getAttribute("visibility"));
//			
//			info_cls=null;
//			 clas_s.put(curPackEl.getAttribute("xmi:id"), info_cls);
//			
//			 info_cls = clas_s.get(curPackEl.getAttribute("xmi:id"));
////			 System.out.println(info_cls.get(0));
											
						//разбор атрибутов
			curPackEl.getElementsByTagName("ownedAttribute");
			
			}
			
		}
		}catch (Exception e) {
			e.printStackTrace();
		  }

		return null;
	}

}
