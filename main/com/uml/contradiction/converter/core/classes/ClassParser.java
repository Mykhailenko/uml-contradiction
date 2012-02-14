package com.uml.contradiction.converter.core.classes;

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.model.cclass.*;
import com.uml.contradiction.model.common.*;
import com.uml.contradiction.converter.core.*;
import com.uml.contradiction.model.ocl.*;

import org.apache.log4j.Logger;
import org.w3c.dom.*;

//������������ ��� ���������� � �������� �������� ��������� �����������
public class ClassParser 
extends CoreParserImpl implements CoreParser{
	
	private static final Logger LOGGER = Logger.getRootLogger();
		
	private Map<String, CClass> classesWithId = new LinkedHashMap <String, CClass>();
	private Map<String, Association> assocesWithId = new LinkedHashMap <String, Association>();
	private Map<String, ClassDiagram> diagrClassWithId = new LinkedHashMap <String, ClassDiagram>();
	private Map<String, PackageElement> packagesWithId = new LinkedHashMap <String, PackageElement>();
	
	private Map<String, Set<Stereotype>> stereotypesWithRefClass = new LinkedHashMap <String, Set<Stereotype>>();
	private Map<String, Constraint> constraintsWithRef = new LinkedHashMap <String, Constraint>();
	private PackageElement rootPackage;
	Element umlModelElRoot;
	
	ClassParsHelper classParsHelper;  //�������� ��������� ��� ������� ������
	CommonClDiagrHelper commonClDiagrHelper; //�������� ������ ��������� ��� ����� ��������
	
	public ClassParser() {
		super();
		
		classParsHelper = new ClassParsHelper(classesWithId, assocesWithId, constraintsWithRef);
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
	
	private Boolean addToClassGraf(PackageElement rootPackage) {
		List<CClass> class_s = ClassGraph.getClasses();
		List<Association> asssoc_s = ClassGraph.getAssociations();
		List<ClassDiagram> clDiagrams = ClassGraph.getClassDiagrams();
		
		Collection<CClass> colectCls = classesWithId.values();
		for(CClass cls : colectCls)			
			class_s.add(cls);
		
		Collection<Association> colectAssocs = assocesWithId.values();
		for(Association ass : colectAssocs)			
			asssoc_s.add(ass);	
		
		Collection<ClassDiagram> colectDiagr = diagrClassWithId.values();
		for(ClassDiagram cld : colectDiagr)	{
			if(cld.getParentPackageElement() == null)
				cld.setParentPackageElement(rootPackage);
			clDiagrams.add(cld);
		}
				
		
		return true;
	}
	
	public List<Object> parse(Element umlModelEl) {
		try {
			umlModelElRoot = umlModelEl;	
			
			//�������� id���� �������� �������
			getAllIdDiagrams(DiagramType.CLASS, umlModelEl);
								
			//������ � ��������� ����� ��������� � Id
			createDiagrmsClass(idDiagrams);
						
			//�������� ���������� �� �������� �� ������
			stereotypesWithRefClass = commonClDiagrHelper.getStereotWithId(umlModelEl);
			
			//�������� ����������� �� �������� �� �������� (�� �� ��������� � ��������)
			commonClDiagrHelper.getConstraintsWithRef(umlModelEl, constraintsWithRef);
			
			//������ �� ���� ���������  HashMap
//			if(!stereotypesWithRefClass.isEmpty()){
//				
//				System.out.println("Size " + stereotypesWithRefClass.size());
//						
//				for(String key : stereotypesWithRefClass.keySet()){
//					System.out.println("Key : " + key + "\n Stereotypes:");
//					Set<Stereotype> sts = stereotypesWithRefClass.get(key);
//					for(Stereotype cls : sts)
//						System.out.println(cls);
//				}		
//			}
			
			//������ uml ������, ������� � ��������� ��������
			PackageElement rootPackage;			
			rootPackage = parsePackage(umlModelEl);			
						
//			printPackHierarchy(rootPackage);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
//	
	public void makeResult(){
		//���������� ��������� ������� (��� ���������������)
		commonClDiagrHelper.putClassesAssocInClDiagramm(classesWithId, assocesWithId, diagrClassWithId, umlModelElRoot);
		
		//������ �������� � ����������� ���������
		addToClassGraf(rootPackage);
	}
		
	public PackageElement parsePackage(Element umlModelEl){
		//������ ������ �� packageElements
		PackageElement rootPackage;			
		rootPackage = firstParsePackage(umlModelEl, null);
		
		//������ ������ �� packageElements 
		//		��� ������ � ������, �������������
		secondParsePackage(umlModelEl);
		
		return rootPackage;
	}
	
	//������ ������ ������
	private PackageElement firstParsePackage(Element umlModelEl, PackageElement parentPack) {
		
		//umlModelEl - �������-����, ������ �������� ����� ������
		
		int temp;
		PackageElement curUmlPackage = null;
		
		try{
		curUmlPackage = new PackageElement();
		
		//���� ��������� �� �������� ����� (default ���)
		if(parentPack != null){
			curUmlPackage.setName(umlModelEl.getAttribute("name"));
			
			//���������� � ����� ��������� ������ � ������� ��� �����������
			commonClDiagrHelper.putParentPackInClassDiadramm(diagrClassWithId, umlModelEl, curUmlPackage);
		}
		else
			curUmlPackage.setName("[[default package]]");
		
		//������������� ������������ �����
		curUmlPackage.setParentPackageElement(parentPack);
		
		//��������� ����� � �� � ���������
		String id4pack = umlModelEl.getAttribute("xmi:id");
		packagesWithId.put(id4pack, curUmlPackage);
				
		//�������� � ���������� ����������� ������
		NodeList pack_nodes = umlModelEl.getElementsByTagName("packagedElement");
			
		for (temp = 0; temp < pack_nodes.getLength(); temp++) {
			
									//������ ������ �������� packagedElement
			Element curPackEl = (Element)pack_nodes.item(temp);
			
			//�������� ��� ����� ������������� ������ ���������������� ��������
			if((Element)curPackEl.getParentNode() == umlModelEl)
			{
				
				//������� - �����
				if(curPackEl.getAttribute("xmi:type").equals("uml:Class")
						|| curPackEl.getAttribute("xmi:type").equals("uml:Interface")
						|| curPackEl.getAttribute("xmi:type").equals("uml:Enumeration")
						|| curPackEl.getAttribute("xmi:type").equals("uml:PrimitiveType")){
				
					String id4class = curPackEl.getAttribute("xmi:id");
													
					CClass curCClass = new CClass();
		
						//��������� ���� CClass
					
					if(stereotypesWithRefClass.get(id4class) != null)
						curCClass.setStereotypes(stereotypesWithRefClass.get(id4class));
					
					curCClass.setName(curPackEl.getAttribute("name"));
					
					curCClass.setParentPackageElement(curUmlPackage);
					
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
					
					//� ����� ��������� ��������� �����
					List<CClass> childrens = curUmlPackage.getChildrenClass();
					
					if(childrens == null){
						childrens = new LinkedList<CClass>();
						curUmlPackage.setChildrenClass(childrens);
					}
					childrens.add(curCClass);												
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
				
				//���� ������� ������� uml:Package
				if(curPackEl.getAttribute("xmi:type").equals("uml:Package")){
					List<PackageElement> childsPack = curUmlPackage.getChildrenPackages();
					
					if(childsPack == null){
						childsPack = new LinkedList<PackageElement>();
						curUmlPackage.setChildrenPackages(childsPack);
					}
					//������-������� ��������� � �������� ������
					//���������� ������ ������� ������� ������ ��� �������
					childsPack.add(firstParsePackage(curPackEl, curUmlPackage));
				}
			}
//			else{	//��� ������ ��� ������ ������������� �� ���������������� �������� 
//				break;
//			}
				
		}
		//���� ����������
//		LOGGER.debug("Finished first parse");
		}catch (Exception e) {
			e.printStackTrace();
		  }
			
		return curUmlPackage;
	}
	
	private void secondParsePackage(Element umlModelEl){
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
			
//			//���� ������� ������� uml:Package
//			if(curPackEl.getAttribute("xmi:type").equals("uml:Package")){
//				secondParsePackage(curPackEl);
//			}
		}
//		LOGGER.debug("Finished second parse");
		}
		catch (Exception e) {
			e.printStackTrace();
		  }
	}
	
	private void printPackHierarchy(PackageElement curPack){
		
		System.out.println("Pack name : " + curPack.getName());
		
		List<PackageElement> children = curPack.getChildrenPackages();
		if(children != null && !(children.isEmpty())){
			for(PackageElement pe : children){
				System.out.println("In package " + curPack.getName() + " -> ");
				printPackHierarchy(pe);
			}
				
		}
			
	}
}
