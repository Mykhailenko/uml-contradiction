package com.uml.contradiction.converter.core.object;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.converter.core.CoreParser;
import com.uml.contradiction.converter.core.CoreParserImpl;
import com.uml.contradiction.converter.core.ParsersTool;
import com.uml.contradiction.converter.core.sequence.SequenceParsHelper;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassDiagram;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.common.Package;
import com.uml.contradiction.model.object.*;


public class ObjectParser 
extends CoreParserImpl implements CoreParser{
	private static final Logger logger = Logger.getLogger(ObjectParser.class);
	
	private Map<String, OObject> objectsWithId = new LinkedHashMap <String, OObject>();
	private Map<String, Link> linksWithId = new LinkedHashMap <String, Link>();
	
	public void makeResult() {
		List<OObject> object_s = ObjectGraph.getObjects();
		List<Link> link_s = ObjectGraph.getLinks();
		
		Collection<OObject> collectObs = objectsWithId.values();
		for(OObject obj : collectObs)		
			object_s.add(obj);
		
		Collection<Link> collectLnks = linksWithId.values();
		for(Link lnk : collectLnks)		
			link_s.add(lnk);
	}		
	
	public List<Object> parse(Element umlModelEl) {
		
		NodeList packNodes = umlModelEl.getElementsByTagName("packagedElement");
		
		for (int i = 0; i < packNodes.getLength(); i++) {
			Element packObjElem = (Element)packNodes.item(i);
			
			//рассматриваем packagedElement для объектов
			if(packObjElem.getAttribute("xmi:type").equals("uml:InstanceSpecification"))
			{
				NodeList listClassifiers = packObjElem.getElementsByTagName("classifier");
				if(listClassifiers.getLength() != 0)
				{			//рассматриваем именно объект
					OObject obj = new OObject();
					String idObj = packObjElem.getAttribute("xmi:id");
					String nameObj = packObjElem.getAttribute("name");				
					obj.setName(nameObj);
					
					//ссылка на класс
					String refOnClass = getAttrByNameAndTag(packObjElem, "classifier", "href");
					if(refOnClass == null)
						logger.error("For object no reference on class");
					else{
						String refIdClass = refOnClass.substring(1);
																		
						CClass referClass = ParsersTool.getInstanceClassParser().getClassesWithId().get(refIdClass);
						if(referClass == null)
							logger.error("The object has reference on class, but no such class");
						else{
							List<CClass> classesOfObj = obj.getClasses();
							
							if(classesOfObj == null){
								classesOfObj = new LinkedList<CClass>();
								obj.setClasses(classesOfObj);
							}
							classesOfObj.add(referClass);
						}
					}
					
					//ссылка на атрибут
					NodeList attrList = packObjElem.getElementsByTagName("slot");
					for (int j = 0; j < attrList.getLength(); j++) {
						Element attrElem = (Element)attrList.item(j);
						if(attrElem.getParentNode() == packObjElem 
								&& attrElem.getAttribute("xmi:type").equals("uml:Slot")){
							
							String idAttrInClass = attrElem.getAttribute("definingFeature");
							Attribute clAttr = ParsersTool.getInstanceClassParser().getAttributesWithId().get(idAttrInClass);
							if(clAttr == null){
								logger.error("For object no reference on class attribute");
							}
							else{
								AttributeObj attrObj = new AttributeObj();
								attrObj.setRefClassAttr(clAttr);
								attrObj.setName(clAttr.getName());
								
								String value = getAttrByNameAndTag(attrElem, "value", "body");
								if(value != null)
									attrObj.setValue(value);
								
								List<AttributeObj> attrsOfObj = obj.getAttributes();
								
								if(attrsOfObj == null){
									attrsOfObj = new LinkedList<AttributeObj>();
									obj.setAttributes(attrsOfObj);
								}
								attrsOfObj.add(attrObj);		
							}
						}
					}
					
					objectsWithId.put(idObj, obj);
				}
				else{	//packagedElement - связь объектов
					Link lnk = new Link();
					String idLink = packObjElem.getAttribute("xmi:id");
					String nameObj = packObjElem.getAttribute("name");	
				}
			}
		}
		
		return null;
	}

}
