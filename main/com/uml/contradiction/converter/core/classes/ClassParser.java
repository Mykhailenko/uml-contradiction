package com.uml.contradiction.converter.core.classes;

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.model.cclass.*;
import com.uml.contradiction.model.common.*;
import com.uml.contradiction.converter.core.*;

import org.apache.log4j.Logger;
import org.w3c.dom.*;

//������������ ��� ���������� � �������� �������� ��������� �����������
public class ClassParser 
extends CoreParserImpl implements CoreParser{
	
	private static final Logger LOGGER = Logger.getRootLogger();
		
	private Map<String, CClass> classesWithId = new LinkedHashMap <String, CClass>();
	private Map<String, Association> assocesWithId = new LinkedHashMap <String, Association>();
	private Map<String, ClassDiagram> diagrClassWithId = new LinkedHashMap <String, ClassDiagram>();
	
	private Map<String, Stereotype> streotypeWithRefClass = new LinkedHashMap <String, Stereotype>();
	
	ClassParsHelper classParsHelper;  //�������� ��������� ��� ������� ������
	CommonClDiagrHelper commonClDiagrHelper; //�������� ������ ��������� ��� ����� ��������
	
	public ClassParser() {
		super();
		
		classParsHelper = new ClassParsHelper(classesWithId, assocesWithId);
		commonClDiagrHelper = new CommonClDiagrHelper();
	}
	//������� ClassDiagram ��� ������� id
	private void createDiagrmsClass(List<String> diarmsId) {			
		if(!diarmsId.isEmpty()){
			for(String curID : diarmsId){
				diagrClassWithId.put(curID, new ClassDiagram());	
			}	
		}
		
	}
	
	private Boolean addToClassDiagram() {
		List<CClass> class_s = ClassGraph.getClasses();
		List<Association> asssoc_s = ClassGraph.getAssociations();
		
		Collection<CClass> colectCls = classesWithId.values();
		for(CClass cls : colectCls)			
			class_s.add(cls);
		
		Collection<Association> colectAssocs = assocesWithId.values();
		for(Association ass : colectAssocs)			
			asssoc_s.add(ass);		
		
		return true;
	}
	
	public List<Object> parse(Element umlModelEl) {
		try {
						
			//�������� id���� �������� �������
			getAllIdDiagrams(DiagramType.CLASS, umlModelEl);
								
			//������ � ��������� ����� ��������� � Id
			createDiagrmsClass(idDiagrams);
						
			//�������� ���������� �� �������� �� ������
			streotypeWithRefClass = commonClDiagrHelper.getStereotWithId(umlModelEl);
					
			if(!streotypeWithRefClass.isEmpty()){
				
				System.out.println("Size " + streotypeWithRefClass.size());
							
				Collection<Stereotype> colects =streotypeWithRefClass.values();;
				for(Stereotype cls : colects)			
					System.out.println(cls);		
		
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
		
		
	//��������� �� �������� ������
	public List<Object> parse2(Element umlModelEl) {
		
		try{
					
		//������ ������ �� packageElements				
		firstParse(umlModelEl);
		//�������� ������� ������� � �����-�� � Id
		
		//������ ������ �� packageElements 
		//		��� ������ � ������, �������������
		secondParsePackElems(umlModelEl);
		
		//������ �������� � ����������� ���������
		addToClassDiagram();
		
		}catch (Exception e) {
			e.printStackTrace();
		  }
		return null;
	}
	
	private void firstParse(Element umlModelEl){
		int temp;
		
		try{
		
		NodeList pack_nodes = umlModelEl.getElementsByTagName("packagedElement");
		
		for (temp = 0; temp < pack_nodes.getLength(); temp++) {
												//������ ������ ��������
			Element curPackEl = (Element)pack_nodes.item(temp);
			
			//������� ��������� ������� - �����
			if(curPackEl.getAttribute("xmi:type").equals("uml:Class")){
			
				String id4class = curPackEl.getAttribute("xmi:id");
				
								
				CClass curCClass = new CClass();
	
												//��������� ���� CClass
				curCClass.setName(curPackEl.getAttribute("name"));
				
				String visibty = curPackEl.getAttribute("visibility");
				
				if(visibty.equals("public")) curCClass.setVisibility(Visibility.PUBLIC);
				if(visibty.equals("private")) curCClass.setVisibility(Visibility.PRIVATE);
				if(visibty.equals("protected")) curCClass.setVisibility(Visibility.PROTECTED);
			
				String isAbstract = curPackEl.getAttribute("isAbstract");
				if(isAbstract.equals("false")) curCClass.setAbstract(false);
				if(isAbstract.equals("true")) curCClass.setAbstract(true);
				
				
				//������ ���������
				NodeList attrElemsList = curPackEl.getElementsByTagName("ownedAttribute");
				
				List<Attribute> attributes = classParsHelper.getAttr4Class(attrElemsList);
				curCClass.setAttributes(attributes);
				
				//������ �������
				NodeList methElemsList = curPackEl.getElementsByTagName("ownedOperation");
				
				List<MMethod> methods = classParsHelper.getMethods4Class(methElemsList);
				curCClass.setMethods(methods);
				
				//��������� ����� � ��� ID � map
				classesWithId.put(id4class, curCClass);
								
			}
			
			
			//������� ��������� - ����������
			if(curPackEl.getAttribute("xmi:type").equals("uml:Association")){

				String id4assoc = curPackEl.getAttribute("xmi:id");
				
//				//���������� �� ������ ���������� � ���������
//				if(isElementInDiagrammByID(id4assoc)){
					
				Association curAssoc = new Association();
				
				if(curPackEl.hasAttribute("memberEnd")){
					//������ ����� ����������
					NodeList endsList = curPackEl.getElementsByTagName("ownedEnd");
					
					assert endsList.getLength() <= 2 : "assosiation must have 2 ends";
					
					for(int z=0; z < endsList.getLength(); z++){
						
						AssociationEnd end = classParsHelper.getEnd4Assoc((Element)endsList.item(z));
						if(z == 0)
							curAssoc.setEnd1(end);
						if(z == 1)
							curAssoc.setEnd2(end);
					}
				}
				assocesWithId.put(id4assoc, curAssoc);
				
			}//��������� � �����������			
		}
		LOGGER.debug("Finished first parse");
		}catch (Exception e) {
			e.printStackTrace();
		  }
	}
	
	//������ ������ �� ������ (�������� �������� ��� ��������)
	private void secondParsePackElems(Element umlModelEl){
		try{
										
		NodeList pack_nodes = umlModelEl.getElementsByTagName("packagedElement");
		int temp;
		
		for (temp = 0; temp < pack_nodes.getLength(); temp++) {
												//������ ������ ��������
			Element curPackEl = (Element)pack_nodes.item(temp);
						
							//������� ��������� ������� - �����
			if(curPackEl.getAttribute("xmi:type").equals("uml:Class")){
			
								
				NodeList ownedAttrList = curPackEl.getElementsByTagName("ownedAttribute");
					
					for(int k=0; k < ownedAttrList.getLength(); k++){
							//������������� ��� ownedAttribute ��� ������ � ������
						Element curOwnAttrElem = (Element)ownedAttrList.item(k);
						
						//�������� ��� ��� ownedAttribute ��� ����
						if(curOwnAttrElem.hasAttribute("association")){
														
							//�������� AsocPackElem  ����������, ��� ��� �����
							String idAsocPackElem = curOwnAttrElem.getAttribute("association");
							Association assocCur = assocesWithId.get(idAsocPackElem);
								
							if(assocCur.getEnd1() != null){//� ����� ������ �����
													//������ ����� ��� ����
								
								AssociationEnd end_2 = classParsHelper.getEnd4Assoc(curOwnAttrElem);
								end_2.setRole(curOwnAttrElem.getAttribute("name"));
								
								assocCur.setEnd2(end_2);
								
							}else{
								
								AssociationEnd end_1 = classParsHelper.getEnd4Assoc(curOwnAttrElem);
								end_1.setRole(curOwnAttrElem.getAttribute("name"));
								
								assocCur.setEnd1(end_1);								
							}												
						}
							
					}	
			}
		}
		LOGGER.debug("Finished second parse");
		}
		catch (Exception e) {
			e.printStackTrace();
		  }
	}
}
