package com.uml.contradiction.converter.core.classes;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.uml.contradiction.model.common.Stereotype;
import com.uml.contradiction.model.common.UMLClassStereotype;
import com.uml.contradiction.model.common.UserStereotype;

public class CommonClDiagrHelper {
	private static final Logger logger = Logger.getLogger(CommonClDiagrHelper.class);

			//получаем стереотипы со ссылками на классы
	public Map<String, Stereotype> getStereotWithId(Element umlModel) {
			
		Map<String, Stereotype> streotypeWithRefClass = new LinkedHashMap <String, Stereotype>();
	
		if(umlModel.getNextSibling() == null)
			return null;
					
		Node sterNode = umlModel.getNextSibling();
		String tagName = null;
					
		do{		//находим все элементов типа стреотип
					
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
								}
								else
									logger.warn("Can't take from steretype reference on class:" +
											" because unknown reference name");
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
					
					if(refOnIdClass!=null && curStereot!=null)					
						streotypeWithRefClass.put(refOnIdClass, curStereot);
				}			
			}
			//перешли на следующий узел
			sterNode = sterNode.getNextSibling();
		} while(sterNode.getNextSibling() != null);
				
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
}
