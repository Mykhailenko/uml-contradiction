package com.uml.contradiction.converter.core.classes;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.uml.contradiction.model.cclass.ClassDiagram;
import com.uml.contradiction.model.common.PackageElement;
import com.uml.contradiction.model.common.Stereotype;
import com.uml.contradiction.model.common.UMLClassStereotype;
import com.uml.contradiction.model.common.UserStereotype;

public class CommonClDiagrHelper {
	private static final Logger logger = Logger.getLogger(CommonClDiagrHelper.class);

			//�������� ���������� �� �������� �� ������
	public Map<String, Set<Stereotype>> getStereotWithId(Element umlModel) {
			
		Map<String, Set<Stereotype>> streotypeWithRefClass = new LinkedHashMap <String, Set<Stereotype>>();
	
		if(umlModel.getNextSibling() == null)
			return null;
					
		Node sterNode = umlModel.getNextSibling();
		String tagName = null;
					
		while(sterNode != null){		//������� ��� ��������� ���� ��������
					
			if(sterNode.getNodeType()==Node.ELEMENT_NODE){		
				Element stereotypeEl = (Element)sterNode;
					
				tagName = stereotypeEl.getTagName();
				
				String stereotypeName = getStereotypeName(tagName);
				
				//���� ����� ���-���������
				if(stereotypeName != null){
					boolean flagIsUmlSter = false;
					boolean isRefOnIdClass = false;
					
					Stereotype curStereot = null;					
					String refOnIdClass = null;
					
					// ��������� ������ �� �����
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
					
						//���� UMLClassStereotype
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
						//���� UserStereotype
					if(!flagIsUmlSter)
						curStereot = new UserStereotype(stereotypeName);
					
					assert isRefOnIdClass == false : "unknown reference name on class in strerotype";
					
					if(refOnIdClass!=null && curStereot!=null)	{
						Set<Stereotype> setSters;
						//���� ������ �� ����� � ID refOnIdClass ��� �� ����
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
				
			//������� �� ��������� ����
			sterNode = sterNode.getNextSibling();
		} 
				
		return streotypeWithRefClass;
	}
	
	private String getStereotypeName(String tagName) {
		
		boolean checked = false;
		String[] arrPartColon = tagName.split(":");
		
				//�������� ��� ��� ��� � ������ ������
		if(tagName.split(":") != null){
			String profileName = arrPartColon[0];
			int lenProf = profileName.length();
			//�������� ����� ����� profile
			String prof = profileName.substring(lenProf - 7);
			if(prof.equals("profile"))
				checked = true;
		}
		
		if(checked)
			return arrPartColon[1];
		else
			return null;
	}
	
	//���������� � ����� ��������� ������, � ������� ��� �����������
	public boolean putParentPackToClassDiadramm(Map<String, ClassDiagram> diagrClassWithId, Element umlModelEl, PackageElement curUmlPackage)
	{
		boolean hasSubDiagrams = false;
		
		NodeList extensionList = umlModelEl.getElementsByTagName("xmi:Extension");
		Element firstExt = (Element)extensionList.item(0);
		
		if(firstExt.getParentNode() == umlModelEl){
			NodeList subDiagrams = firstExt.getElementsByTagName("subdiagram");
			
			//��� ���� �����- ������ �� ID ��������� �������
			for(int i=0; i < subDiagrams.getLength(); i++){
				Element curSubDiagram = (Element)subDiagrams.item(i);
				
				if(curSubDiagram.getParentNode() == firstExt)
				{					
					String idSub = curSubDiagram.getAttribute("xmi:value");
					ClassDiagram clDiagr = diagrClassWithId.get(idSub);
					
					if(clDiagr != null){	//������������� ����� ��� ���������
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
}
