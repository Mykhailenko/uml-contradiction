package com.uml.contradiction.converter.core.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationEnd;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.cclass.Multiplicity;
import com.uml.contradiction.model.cclass.Navigability;
import com.uml.contradiction.model.cclass.Scope;
import com.uml.contradiction.model.cclass.Visibility;

			//помощник разбирает атрибуты классов и ассоциаций
public class ClassParsHelper {
	private Map<String, CClass> classesWithId;
	private Map<String, Association> assocesWithId;
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public ClassParsHelper(Map<String, CClass> classesWithId,
			Map<String, Association> assocesWithId) {
		super();
		this.classesWithId = classesWithId;
		this.assocesWithId = assocesWithId;
	}		
	
	//get set
	public Map<String, CClass> getClassesWithId() {
		return classesWithId;
	}
	public void setClassesWithId(Map<String, CClass> classesWithId) {
		this.classesWithId = classesWithId;
	}
	public Map<String, Association> getAssocesWithId() {
		return assocesWithId;
	}
	public void setAssocesWithId(Map<String, Association> assocesWithId) {
		this.assocesWithId = assocesWithId;
	}
	
	public List<Attribute> getAttr4Class(NodeList attrList){
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

	public List<MMethod> getMethods4Class(NodeList methdsList){
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
	public AssociationEnd getEnd4Assoc(Element endElement){
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
