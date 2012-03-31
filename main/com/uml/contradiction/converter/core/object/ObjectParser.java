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
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassDiagram;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.common.Package;
import com.uml.contradiction.model.object.OObject;
import com.uml.contradiction.model.object.ObjectGraph;

public class ObjectParser 
extends CoreParserImpl implements CoreParser{
	private static final Logger logger = Logger.getLogger(ObjectParser.class);
	
	private Map<String, OObject> objectsWithId = new LinkedHashMap <String, OObject>();
	
	public void makeResult() {
		List<OObject> object_s = ObjectGraph.getObjects();
		
		Collection<OObject> collectObs = objectsWithId.values();
		for(OObject obj : collectObs)		
			object_s.add(obj);
	}		
	
	public List<Object> parse(Element umlModelEl) {
		
		NodeList packNodes = umlModelEl.getElementsByTagName("packagedElement");
		
		for (int i = 0; i < packNodes.getLength(); i++) {
			Element packObjElem = (Element)packNodes.item(i);
			
			//рассматриваем packagedElement для объектов
			if(packObjElem.getAttribute("xmi:type").equals("uml:InstanceSpecification"))
			{
				OObject obj = new OObject();
				String idObj = packObjElem.getAttribute("xmi:id");
				String nameObj = packObjElem.getAttribute("name");				
				obj.setName(nameObj);
								
				String refOnClass = getAttrByNameAndTag(packObjElem, "classifier", "href");
				if(refOnClass == null)
					logger.error("For object no reference on class");
				else{
					String refIdClass = refOnClass.substring(1);
					
					List<CClass> classes = new LinkedList<CClass>();
					
					CClass referClass = ParsersTool.getInstanceClassParser().getClassesWithId().get(refIdClass);
					if(referClass == null)
						logger.error("The object has reference on class, but no such class");
					else{
						classes.add(referClass);
						obj.setClasses(classes);
					}
				}
				
				objectsWithId.put(idObj, obj);
			}
		}
		
		return null;
	}

}
