package com.uml.contradiction.converter.core.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.converter.core.CoreParserImpl;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationEnd;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.cclass.Multiplicity;
import com.uml.contradiction.model.cclass.NaryAssociationClass;
import com.uml.contradiction.model.cclass.Navigability;
import com.uml.contradiction.model.cclass.Parameter;
import com.uml.contradiction.model.cclass.Scope;
import com.uml.contradiction.model.cclass.Visibility;
import com.uml.contradiction.model.common.*;
import com.uml.contradiction.model.ocl.Constraint;

			//помощник разбирает атрибуты классов и ассоциаций
public class ClassParsHelper {
	private Map<String, CClass> classesWithId;
	private Map<String, Association> assocesWithId;
	private Map<String, Constraint> constraintsWithRef;
	private Map<String, Set<Stereotype>> stereotypesWithRefClass;
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public ClassParsHelper(Map<String, CClass> classesWithId,
			Map<String, Association> assocesWithId,
			Map<String, Constraint> constraintsWithRef,
			Map<String, Set<Stereotype>> stereotypesWithRefClass) {
		super();
		this.classesWithId = classesWithId;
		this.assocesWithId = assocesWithId;
		this.constraintsWithRef = constraintsWithRef;
		this.stereotypesWithRefClass = stereotypesWithRefClass;
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
	
	public CClass parseClass(Element curClElem) {
		CClass curCClass = new CClass();
		CoreParserImpl corePars = new CoreParserImpl();
		
		Element extension = (Element)curClElem.getElementsByTagName("xmi:Extension").item(0);
		//если N-арная ассоциация
		if(extension.getParentNode() == curClElem){
			String modelVal = corePars.getAttrByNameAndTag(extension, "modelType", "value");
			if(modelVal.equals("NARY"))
				curCClass = new NaryAssociationClass();
		}
		
							
		//заполняем поля CClass
		String id4class = curClElem.getAttribute("xmi:id");
		
		if(stereotypesWithRefClass.get(id4class) != null)
			curCClass.setStereotypes(stereotypesWithRefClass.get(id4class));
		
		curCClass.setName(curClElem.getAttribute("name"));					
		
		String visibty = curClElem.getAttribute("visibility");
		
		if(visibty.equals("public")) curCClass.setVisibility(Visibility.PUBLIC);
		if(visibty.equals("private")) curCClass.setVisibility(Visibility.PRIVATE);
		if(visibty.equals("protected")) curCClass.setVisibility(Visibility.PROTECTED);
	
		String isAbstract = curClElem.getAttribute("isAbstract");
		if(isAbstract.equals("false")) curCClass.setAbstract(false);
		if(isAbstract.equals("true")) curCClass.setAbstract(true);
		
		
		//разбор атрибутов		
		List<Attribute> attributes = getAttr4Class(curClElem);
		curCClass.setAttributes(attributes);
		
		//разбор методов
		List<MMethod> methods = getMethods4Class(curClElem);
		curCClass.setMethods(methods);
		
		//вставляем класс и его ID в map
		classesWithId.put(id4class, curCClass);

		return curCClass;	
	}
	
	public List<Attribute> getAttr4Class(Element classElement){
		NodeList attrList = classElement.getElementsByTagName("ownedAttribute");		
		
		List<Attribute> attributes = new ArrayList<Attribute>();
		
		CoreParserImpl corePars = new CoreParserImpl();
		
		for(int k=0; k<attrList.getLength(); k++){						
			
			Element curAttrElem = (Element)attrList.item(k);	
			
			//проверка что будем рассматривать только непосредственных потомков
			if((Element)curAttrElem.getParentNode() == classElement)
			{
				Attribute attr_1 = new Attribute();
							
					//проверка что это не ownedAttribute для роли
				if(!curAttrElem.hasAttribute("association"))
				{			
					String attrId = curAttrElem.getAttribute("xmi:id");
						
					attr_1.setName(curAttrElem.getAttribute("name"));
					
					String visibty = curAttrElem.getAttribute("visibility");
					if(visibty.equals("public")) attr_1.setVisibility(Visibility.PUBLIC);
					if(visibty.equals("private")) attr_1.setVisibility(Visibility.PRIVATE);
					if(visibty.equals("protected")) attr_1.setVisibility(Visibility.PROTECTED);
				
					String isDeriv = curAttrElem.getAttribute("isDerived");
					if(isDeriv.equals("false")) attr_1.setDerived(false);
					if(isDeriv.equals("true")) attr_1.setDerived(true);
					
					String scope = curAttrElem.getAttribute("ownerScope");
					if(scope.equals("instance")) attr_1.setScope(Scope.INSTANCE);
					if(scope.equals("classifier")) attr_1.setScope(Scope.CLASSIFIER);
					
					String defVal = corePars.getAttrByNameAndTag(curAttrElem, "defaultValue", "value");
					if(defVal != null)
						attr_1.setDdefault(defVal);
					
					Multiplicity mult = getMultiplicity(curAttrElem);
					if(mult != null)
						attr_1.setMultiplicity(mult);
					
					attr_1.setType(getType(curAttrElem));
										
					Constraint constr = constraintsWithRef.get(attrId);
					if(constr != null)
						attr_1.setConstraints(constr);
					
					attributes.add(attr_1);
				}
			}
		}
		return attributes;
	}
	
	private Type getType(Element elem) {
		Type type = null;
		
		if(elem.hasAttribute("type")){
			String typeVal = elem.getAttribute("type");
			if(typeVal != null){
				String[] arr = typeVal.split("_");
				if(arr.length != 2)
					LOGGER.error("Trouble with attribute type "+
							"in tag  ");
				if(arr != null){
					if(arr[0].equals("int")){
						type = UMLType.INTEGER;
					}else{
					if(arr[0].equals("boolean")){
						type = UMLType.BOOLEAN;
					}else{
					if(arr[0].equals("string")){
						type = UMLType.STRING;
					}else{
						type = new UserType(arr[0]);
					}	}	}					
				}	
			}
		}
		return type;
	}

	public List<MMethod> getMethods4Class(Element classElement){		
		NodeList methdsList = classElement.getElementsByTagName("ownedOperation");
				
		List<MMethod> methods = new ArrayList<MMethod>();
		
		for(int k=0; k<methdsList.getLength(); k++){			
			Element curMethElem = (Element)methdsList.item(k);
			
			//проверка что будем рассматривать только непосредственных потомков
			if((Element)curMethElem.getParentNode() == classElement)
			{
				MMethod meth_1 = new MMethod();
				
				String idMeth = curMethElem.getAttribute("xmi:id");
								
				meth_1.setName(curMethElem.getAttribute("name"));
				
				String visibty = curMethElem.getAttribute("visibility");
				if(visibty.equals("public")) meth_1.setVisibility(Visibility.PUBLIC);
				if(visibty.equals("private")) meth_1.setVisibility(Visibility.PRIVATE);
				if(visibty.equals("protected")) meth_1.setVisibility(Visibility.PROTECTED);
			
				String scope = curMethElem.getAttribute("ownerScope");
				if(scope.equals("instance")) meth_1.setScope(Scope.INSTANCE);
				if(scope.equals("classifier")) meth_1.setScope(Scope.CLASSIFIER);
				
				if(stereotypesWithRefClass.get(idMeth) != null)
					meth_1.setStereotypes(stereotypesWithRefClass.get(idMeth));
				
				//разбор параметров
				List<Parameter> params = null;
				NodeList parametersList = curMethElem.getElementsByTagName("ownedParameter");
				for (int i = 0; i < parametersList.getLength(); i++) {
					Element curParam = (Element)parametersList.item(i);
					
					//возвращаемое значение
					if(curParam.getAttribute("direction").equals("return")){
						if(curParam.hasAttribute("type")){
							String typeVal = curParam.getAttribute("type");
							if(typeVal != null){
								String[] arr = typeVal.split("_");
								if(arr.length != 2)
									LOGGER.error("Trouble with parameter type "+
											"in tag  ");
								if(arr != null)
									meth_1.setReturnResult(arr[0]);														
							}
						}
					}
					if(curParam.hasAttribute("kind")){
						if(params == null)
							params = new LinkedList<Parameter>();
						
						Parameter par = new Parameter();
						
						if(curParam.hasAttribute("name"))
							par.setName(curParam.getAttribute("name"));
						
						if(curParam.hasAttribute("type"))
							par.setType(getType(curParam));
						
						params.add(par);
					}					
				}
				meth_1.setParameters(params);
								
				methods.add(meth_1);
			}
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
		Multiplicity multipl = getMultiplicity(endElement); 
		
		if(multipl != null)
		assEnd.setMultiplicity(multipl);	
		
		return assEnd;
	}
	
	private Multiplicity getMultiplicity(Element el){
		Multiplicity multipl = null;
		try {			
			NodeList nlistLow = el.getElementsByTagName("lowerValue");
			if(nlistLow.getLength() > 0){
				String lowValue = ((Element)nlistLow.item(0)).getAttribute("value");
				NodeList nlistHi = el.getElementsByTagName("upperValue");
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
				
				multipl = new Multiplicity(lowerBound, upperBound); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			multipl = null;
		}
		return multipl;
	}
}
