package com.uml.contradiction.converter.core;

import java.io.File;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.diagram.*;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.model.cclass.*;

import org.apache.log4j.Logger;
import org.w3c.dom.*;

public class ClassParser 
//implements CoreParser
extends CoreParserImpl implements CoreParser{
	
	private Map<String, CClass> classesWithId = new LinkedHashMap <String, CClass>();
	private Map<String, Association> assocesWithId = new LinkedHashMap <String, Association>();
	
	private Boolean AddToClassDiagram(Map<String, CClass> classesWithId) {
		return null;
	}
		
	@Override
	public List<Object> parse(DiagramForChoise diagrForSearch, Element umlModelEl) {

		try{
			//получения списка Id элементов для выбранной диаграммы
		IdElementsInDIagramm = getIdElementsInDiagramm(diagrForSearch, umlModelEl);
			
		if(IdElementsInDIagramm == null)
			throw new Exception("Couldn't select the diagramm");
		
							
		NodeList pack_nodes = umlModelEl.getElementsByTagName("packagedElement");
		
		for (int temp = 0; temp < pack_nodes.getLength(); temp++) {
												//разбор одного класса
			Element curPackEl = (Element)pack_nodes.item(temp);
			
			boolean present = false;
							//элемент диаграммы классов
			if(curPackEl.getAttribute("xmi:type").equals("uml:Class")){
			
			String id4class = curPackEl.getAttribute("xmi:id");
			
			//относиться ли данный класс к диаграмме
			for(String curID : IdElementsInDIagramm){
				if(curID.equals(id4class)){
					present =true;
					break;
				}				
			}		
			if(present){
				
			CClass curCClass = new CClass();
			CClass testCClass;
											//заполняем поля CClass
			curCClass.setName(curPackEl.getAttribute("name"));
			
			String visibty = curPackEl.getAttribute("visibility");
			
			if(visibty.equals("public")) curCClass.setVisibility(Visibility.PUBLIC);
			if(visibty.equals("private")) curCClass.setVisibility(Visibility.PRIVATE);
			if(visibty.equals("protected")) curCClass.setVisibility(Visibility.PROTECTED);
		
			String isAbstract = curPackEl.getAttribute("isAbstract");
			if(isAbstract.equals("false")) curCClass.setAbstract(false);
			if(isAbstract.equals("true")) curCClass.setAbstract(true);
			
			
			//разбор атрибутов
			NodeList attrElemsList = curPackEl.getElementsByTagName("ownedAttribute");
			
			List<Attribute> attributes = getAttr4Class(attrElemsList);
			curCClass.setAttributes(attributes);
			
			//разбор методов
			NodeList methElemsList = curPackEl.getElementsByTagName("ownedOperation");
			
			List<MMethod> methods = getMethods4Class(methElemsList);
			curCClass.setMethods(methods);
			
			//вставляем класс и его ID в map
			classesWithId.put(id4class, curCClass);
			
			testCClass = classesWithId.get(id4class);
			System.out.println(testCClass.toString());
			
			
			}
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		  }

		return null;
	}
	
	private List<Attribute> getAttr4Class(NodeList attrList){
		List<Attribute> attributes = new ArrayList<Attribute>();
		
		for(int k=0; k<attrList.getLength(); k++){
			Attribute attr_1 = new Attribute();
			Element curAttrElem = (Element)attrList.item(k);
			
			attr_1.setName(curAttrElem.getAttribute("name"));
			
			String visibty = curAttrElem.getAttribute("visibility");
			if(visibty.equals("public")) attr_1.setVisibility(Visibility.PUBLIC);
			if(visibty.equals("private")) attr_1.setVisibility(Visibility.PRIVATE);
			if(visibty.equals("protected")) attr_1.setVisibility(Visibility.PROTECTED);
		
			String isDeriv = curAttrElem.getAttribute("isAbstract");
			if(isDeriv.equals("false")) attr_1.setDerived(false);
			if(isDeriv.equals("true")) attr_1.setDerived(true);
			
			String scope = curAttrElem.getAttribute("ownerScope");
			if(scope.equals("instance")) attr_1.setScope(Scope.INSTANCE);
			if(scope.equals("classifier")) attr_1.setScope(Scope.CLASSIFIER);
			
			attributes.add(attr_1);
		}
		return attributes;
	}

	private List<MMethod> getMethods4Class(NodeList methdsList){
		List<MMethod> methods = new ArrayList<MMethod>();
		
		for(int k=0; k<methdsList.getLength(); k++){
			MMethod meth_1 = new MMethod();
			Element curMethElem = (Element)methdsList.item(k);
			
			meth_1.setName(curMethElem.getAttribute("name"));
			
			String visibty = curMethElem.getAttribute("visibility");
			if(visibty.equals("public")) meth_1.setVisibility(Visibility.PUBLIC);
			if(visibty.equals("private")) meth_1.setVisibility(Visibility.PRIVATE);
			if(visibty.equals("protected")) meth_1.setVisibility(Visibility.PROTECTED);
		
			String scope = curMethElem.getAttribute("ownerScope");
			if(scope.equals("instance")) meth_1.setScope(Scope.INSTANCE);
			if(scope.equals("classifier")) meth_1.setScope(Scope.CLASSIFIER);
			
			methods.add(meth_1);
		}
		return methods;
	}
	
}
