package com.uml.contradiction.converter.core.classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassDiagram;
import com.uml.contradiction.model.common.PackageElement;
import com.uml.contradiction.model.common.Stereotype;
import com.uml.contradiction.model.common.UMLClassStereotype;
import com.uml.contradiction.model.common.UserStereotype;
import com.uml.contradiction.model.ocl.Constraint;

public class CommonClDiagrHelper {
	private static final Logger logger = Logger.getLogger(CommonClDiagrHelper.class);

			//получаем стереотипы со ссылками на классы
	public Map<String, Set<Stereotype>> getStereotWithId(Element umlModel) {
			
		Map<String, Set<Stereotype>> streotypeWithRefClass = new LinkedHashMap <String, Set<Stereotype>>();
	
		if(umlModel.getNextSibling() == null)
			return null;
					
		Node sterNode = umlModel.getNextSibling();
		String tagName = null;
					
		while(sterNode != null){		//находим все элементов типа стреотип
					
			if(sterNode.getNodeType()==Node.ELEMENT_NODE){		
				Element stereotypeEl = (Element)sterNode;
					
				tagName = stereotypeEl.getTagName();
				
				String stereotypeName = getStereotypeName(tagName);
				
				//если точно тег-стереотип
				if(stereotypeName != null){
					boolean flagIsUmlSter = false;
					boolean isRefOnIdClass = false;
					
					Stereotype curStereot = null;					
					String refOnIdClass = null;
					
					// получение ссылки на класс
					if(stereotypeEl.hasAttribute("base_Class")){
						refOnIdClass =  stereotypeEl.getAttribute("base_Class");
						isRefOnIdClass = true;
					}else{
						if(stereotypeEl.hasAttribute("base_Interface")){
							refOnIdClass =  stereotypeEl.getAttribute("base_Interface");
							isRefOnIdClass = true;
						}else{
							if(stereotypeEl.hasAttribute("base_Enumeration")){
								refOnIdClass =  stereotypeEl.getAttribute("base_Enumeration");
								isRefOnIdClass = true;
							}else{
								if(stereotypeEl.hasAttribute("base_PrimitiveType")){
									refOnIdClass =  stereotypeEl.getAttribute("base_PrimitiveType");
									isRefOnIdClass = true;
								}else{
									if(stereotypeEl.hasAttribute("base_Dependency")){
										refOnIdClass =  stereotypeEl.getAttribute("base_Dependency");
										isRefOnIdClass = true;
									}
									else
										logger.warn("Can't take from steretype reference on class:" +
												" because unknown reference name");									
								}									
							}
						}
					}
					
						//если UMLClassStereotype
					if(stereotypeName.equals("control") ){
						curStereot = UMLClassStereotype.CONTROL;
						flagIsUmlSter = true;
					}
					if(stereotypeName.equals("entity") ){
						curStereot = UMLClassStereotype.ENTITY;
						flagIsUmlSter = true;
					}
					if(stereotypeName.equals("utility") ){
						curStereot = UMLClassStereotype.UTILITY;
						flagIsUmlSter = true;
					}
					if(stereotypeName.equals("implementationClass") ){
						curStereot = UMLClassStereotype.IMPLEMENTATION_CLASS;
						flagIsUmlSter = true;
					}
						//если UserStereotype
					if(!flagIsUmlSter)
						curStereot = new UserStereotype(stereotypeName);
					
					assert isRefOnIdClass == false : "unknown reference name on class in strerotype";
					
					if(refOnIdClass!=null && curStereot!=null)	{
						Set<Stereotype> setSters;
						//если ссылки на класс с ID refOnIdClass ещё не было
						if(streotypeWithRefClass.get(refOnIdClass) == null){
							
							setSters = new HashSet<Stereotype>();
							setSters.add(curStereot);
							streotypeWithRefClass.put(refOnIdClass, setSters);
						}else{
							setSters = streotypeWithRefClass.get(refOnIdClass);
							setSters.add(curStereot);
						}						
					}						
				}			
			}
			if(sterNode.getNextSibling() == null)
				break;
				
			//перешли на следующий узел
			sterNode = sterNode.getNextSibling();
		} 
				
		return streotypeWithRefClass;
	}
	
	private String getStereotypeName(String tagName) {
		
		boolean checked = false;
		String[] arrPartColon = tagName.split(":");
		
				//проверка что это тег с нужным именем
		if(tagName.split(":") != null){
			String profileName = arrPartColon[0];
			int lenProf = profileName.length();
			//отнимаем длину слова profile
			String prof = profileName.substring(lenProf - 7);
			if(prof.equals("profile"))
				checked = true;
		}
		
		if(checked)
			return arrPartColon[1];
		else
			return null;
	}
	
	public void getConstraintsWithRef(Element umlModel, Map<String, Constraint> constraintsWithRef){
		
		try{
			
			NodeList rules = umlModel.getElementsByTagName("ownedRule");
			int temp;
			
			for (temp = 0; temp < rules.getLength(); temp++) {
													//разбор одного элемента
				Element curRule = (Element)rules.item(temp);
														
				String strOfRefs = curRule.getAttribute("constrainedElement");
				String[] idElems = strOfRefs.split(" ");
								
				Element commentEl = (Element)curRule.getElementsByTagName("ownedComment").item(0);
				Node body = commentEl.getElementsByTagName("body").item(0);
				Constraint constr = new Constraint(body.getTextContent());
				
				for(int i =0; i< idElems.length; i++)
					constraintsWithRef.put(idElems[i], constr);
			}
		}
	catch (Exception e) {
		e.printStackTrace();		
	  }			
	}
	
	//добавление к класс диаграмме пакета, в котором она содержиться
	public boolean putParentPackInClassDiadramm(Map<String, ClassDiagram> diagrClassWithId, Element umlModelEl, PackageElement curUmlPackage)
	{
		boolean hasSubDiagrams = false;
		
		NodeList extensionList = umlModelEl.getElementsByTagName("xmi:Extension");
		Element firstExt = (Element)extensionList.item(0);
		
		if(firstExt.getParentNode() == umlModelEl){
			NodeList subDiagrams = firstExt.getElementsByTagName("subdiagram");
			
			//для всех тегов- ссылок на ID диаграммы классов
			for(int i=0; i < subDiagrams.getLength(); i++){
				Element curSubDiagram = (Element)subDiagrams.item(i);
				
				if(curSubDiagram.getParentNode() == firstExt)
				{					
					String idSub = curSubDiagram.getAttribute("xmi:value");
					ClassDiagram clDiagr = diagrClassWithId.get(idSub);
					
					if(clDiagr != null){	//устанавливаем пакет для диаграммы
						clDiagr.setParentPackageElement(curUmlPackage);
						hasSubDiagrams = true;
					}
				}
				else
					break;
			}
		}
		
		return hasSubDiagrams;
	}
	
	//добавляем ссылки на классы и ассоциации к диаграмме классов
	public void putClassesAssocInClDiagramm(Map<String, CClass> classesWithId, Map<String, Association> assocesWithId,
			Map<String, ClassDiagram> diagrClassWithId, Element umlModelEl) {
		
		//проходим по всем диаграммам с поиском диаграмм классов
	NodeList diagramAll = umlModelEl.getElementsByTagName("uml:Diagram");
	
	String diagrType = new String("ClassDiagram");
	
		for(int temp = 0; temp < diagramAll.getLength(); temp++){
			Element curDiagr = (Element)diagramAll.item(temp);
			
			if(curDiagr.getAttribute("diagramType").equals(diagrType))
			{
				String idCurDiagram = curDiagr.getAttribute("xmi:id");
				ClassDiagram diagram = diagrClassWithId.get(idCurDiagram);
				
				diagram.setName(curDiagr.getAttribute("name"));
				
				NodeList mainElementOfDiagr = curDiagr.getElementsByTagName("uml:Diagram.element");
				NodeList listElementsOfDiagr = 
						((Element)mainElementOfDiagr.item(0)).getElementsByTagName("uml:DiagramElement");
				
				for(int i = 0; i < listElementsOfDiagr.getLength(); i++){
					Element curElem = (Element)listElementsOfDiagr.item(i);
					String refOnObject = curElem.getAttribute("subject");
					String typeElem = curElem.getAttribute("preferredShapeType");
					
					//если элемент - класс
					if(typeElem.equals("Class")){
						CClass classCur = classesWithId.get(refOnObject);
						
						if(classCur != null){
							List<CClass> classes = diagram.getClasses();
							
							if(classes == null){
								classes = new LinkedList<CClass>();
								diagram.setClasses(classes);
							}							
							classes.add(classCur);							
						}
					}					
					//если элемент - ассоциация
					if(typeElem.equals("Association")){
						Association assocCur = assocesWithId.get(refOnObject);
						
						if(assocCur != null){
							List<Association> assoces = diagram.getAssociations();
							
							if(assoces == null){
								assoces = new LinkedList<Association>();
								diagram.setAssociations(assoces);
							}							
							assoces.add(assocCur);							
						}
					}					
				}			
			}
		}
	}
	
}
