package com.uml.contradiction.converter.core;

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.engine.model.diagram.*;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.model.cclass.*;

import org.apache.log4j.Logger;
import org.w3c.dom.*;

public class ClassParser 
extends CoreParserImpl implements CoreParser{
	
	private static final Logger LOGGER = Logger.getRootLogger();
		
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
		int temp;
		
		if(IdElementsInDIagramm == null)
			throw new Exception("Couldn't select the diagramm");
		
							
		NodeList pack_nodes = umlModelEl.getElementsByTagName("packagedElement");
		
		for (temp = 0; temp < pack_nodes.getLength(); temp++) {
												//разбор одного элемента
			Element curPackEl = (Element)pack_nodes.item(temp);
			
			boolean present = false;
							//элемент диаграммы классов - класс
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
			
			boolean pres2 = false;
			
			//элемент диаграммы - ассоциация
			if(curPackEl.getAttribute("xmi:type").equals("uml:Association")){

				String id4assoc = curPackEl.getAttribute("xmi:id");
				
				//относиться ли данная ассоциация к диаграмме
				for(String curID : IdElementsInDIagramm){
					if(curID.equals(id4assoc)){
						pres2 =true;
						break;
					}				
				}		
				if(pres2){
					
				Association curAssoc = new Association();
				
				if(curPackEl.hasAttribute("memberEnd")){
					//разбор конца ассоциации
					NodeList endsList = curPackEl.getElementsByTagName("ownedEnd");
					
					assert endsList.getLength() <= 2 : "assosiation must have 2 ends";
					
					for(int z=0; z < endsList.getLength(); z++){
						
						AssociationEnd end = getEnd4Assoc((Element)endsList.item(z));
						if(z == 0)
							curAssoc.setEnd1(end);
						if(z == 1)
							curAssoc.setEnd2(end);
					}
				}
				assocesWithId.put(id4assoc, curAssoc);
				}
//				System.out.println(assocesWithId.get(id4assoc));
			}//закончили с ассоциацией
			
		}
		//закончили первый проход по packageElements
		
		secondParse(diagrForSearch, umlModelEl);
		
		Collection<Association> colectAssocs = assocesWithId.values();
		for(Association ass : colectAssocs)			
		System.out.println(ass);
		
		}catch (Exception e) {
			e.printStackTrace();
		  }
		return null;
	}
	
	//второй проход по дереву (основные элементы уже положены)
	private void secondParse(DiagramForChoise diagrForSearch, Element umlModelEl){
		try{
										
		NodeList pack_nodes = umlModelEl.getElementsByTagName("packagedElement");
		int temp;
		
		for (temp = 0; temp < pack_nodes.getLength(); temp++) {
												//разбор одного элемента
			Element curPackEl = (Element)pack_nodes.item(temp);
//			Map<String, AssociationEnd> watchedOwnAttrRole = new HashMap<String, AssociationEnd>();
			
			boolean present = false;
							//элемент диаграммы классов - класс
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
					NodeList ownedAttrList = curPackEl.getElementsByTagName("ownedAttribute");
					
					for(int k=0; k < ownedAttrList.getLength(); k++){
							//просматриваем все ownedAttribute для поиска с ролями
						Element curOwnAttrElem = (Element)ownedAttrList.item(k);
						
						//проверка что это ownedAttribute для роли
						if(curOwnAttrElem.hasAttribute("association")){
							
//							String idOwnedAttrRole = curOwnAttrElem.getAttribute("xmi:id");
							
							//получили AsocPackElem  ассоциацию, чей это конец
							String idAsocPackElem = curOwnAttrElem.getAttribute("association");
							Association assocCur = assocesWithId.get(idAsocPackElem);
							
//							getOppEndId(); //нужно будет искать элемент 
							
							if(assocCur.getEnd1() != null){//с ролью второй конец
													//первый конец уже есть
								
								AssociationEnd end_2 = getEnd4Assoc(curOwnAttrElem);
								end_2.setRole(curOwnAttrElem.getAttribute("name"));
								
								assocCur.setEnd2(end_2);
								
							}else{
								
								AssociationEnd end_1 = getEnd4Assoc(curOwnAttrElem);
								end_1.setRole(curOwnAttrElem.getAttribute("name"));
								
								assocCur.setEnd1(end_1);								
							}
							
//							if(watchedOwnAttrRole.get(idOwnedAttrRole) == null){
//								
//								AssociationEnd end_1 = getEnd4Assoc(curOwnAttrElem);
//								end_1.setRole(curOwnAttrElem.getAttribute("name"));
//								
////								watchedOwnAttrRole.put(, true);
//															
//							}
						}
							
					}
				
				}
			}
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		  }
	}
	
	private List<Attribute> getAttr4Class(NodeList attrList){
		List<Attribute> attributes = new ArrayList<Attribute>();
		
		for(int k=0; k<attrList.getLength(); k++){
			Attribute attr_1 = new Attribute();
			Element curAttrElem = (Element)attrList.item(k);
			
				//проверка что это не ownedAttribute для роли
			if(!curAttrElem.hasAttribute("association"))
			{			
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
				//конец для ассоциации
	private AssociationEnd getEnd4Assoc(Element endElement){
		AssociationEnd assEnd = new AssociationEnd();
		
		String aggreg = endElement.getAttribute("aggregation");
		if(aggreg.equals("none")) assEnd.setAggregationKind(AggregationKind.NONE);
		if(aggreg.equals("shared")) assEnd.setAggregationKind(AggregationKind.SHARED);
		if(aggreg.equals("composite")) assEnd.setAggregationKind(AggregationKind.COMPOSITE);
	
		String navig = endElement.getAttribute("isNavigable");
		if(navig.equals("true")) assEnd.setNavigability(Navigability.NAVIGABLE);
		if(navig.equals("false")) assEnd.setNavigability(Navigability.NON_NAVIGABLE);
		
					//ассоциируемый класс
		String idAsocedClass = endElement.getAttribute("type");
		CClass assCClass = classesWithId.get(idAsocedClass);
		assEnd.setAssociatedClass(assCClass);
		
					//кратности
		try {			
			NodeList nlistLow = endElement.getElementsByTagName("lowerValue");
			if(nlistLow.getLength() > 0){
				String lowValue = ((Element)nlistLow.item(0)).getAttribute("value");
				NodeList nlistHi = endElement.getElementsByTagName("upperValue");
				String highValue = ((Element)nlistHi.item(0)).getAttribute("value");
				
				Integer lowerBound =  new Integer(0);
				Double upperBound =  new Double(0);
							
				if(lowValue.equals("*")) lowerBound = 0;
				else
					lowerBound = Integer.valueOf(lowValue);
					
				if(highValue.equals("*")) {
					upperBound = Double.POSITIVE_INFINITY;
					LOGGER.info("We have upper value *");
				}
				else
					upperBound = Double.valueOf(highValue);
				
				Multiplicity multipl = new Multiplicity(lowerBound, upperBound); 
					
				assEnd.setMultiplicity(multipl);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return assEnd;
	}
}
