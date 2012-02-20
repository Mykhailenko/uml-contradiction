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
import com.uml.contradiction.model.common.Package;
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
	private Map<String, Package> packagesWithId = new LinkedHashMap <String, Package>();
	
	private Map<String, Set<Stereotype>> stereotypesWithRefClass = new LinkedHashMap <String, Set<Stereotype>>();
	private Map<String, Constraint> constraintsWithRef = new LinkedHashMap <String, Constraint>();
	private List<Dependency> dependencies = new LinkedList<Dependency>();
	private List<Realization> realizations = new LinkedList<Realization>();
	private List<Generalization> generalizations = new LinkedList<Generalization>();
	
	private Package rootPackage;
	Element umlModelElRoot;
	
	ClassParsHelper classParsHelper;  //�������� ��������� ��� ������� ������
	CommonClDiagrHelper commonClDiagrHelper; //�������� ������ ��������� ��� ����� ��������
	
	public ClassParser() {
		super();
		
		classParsHelper = new ClassParsHelper(classesWithId, assocesWithId, 
				constraintsWithRef, stereotypesWithRefClass);
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
	
	private Boolean addToClassGraf(Package rootPackage) {
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
			commonClDiagrHelper.getStereotWithId(umlModelEl, stereotypesWithRefClass);
			
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
			

//			Package rootPackage;
			//������ uml ������, ������� � ��������� ��������
			rootPackage = parsePackage(umlModelEl);			
						
//			printPackHierarchy(rootPackage);
			
			for(Dependency dep : dependencies)
				System.out.println(dep);
			
			for(Realization real : realizations)
				System.out.println(real);
			
			for(Generalization gen : generalizations)
				System.out.println(gen);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void makeResult(){
		//���������� ��������� ������� (��� ���������������)
		commonClDiagrHelper.putClassesAssocInClDiagramm(classesWithId, assocesWithId, diagrClassWithId, umlModelElRoot);
	
		//������ �������� � ����������� ���������
		addToClassGraf(rootPackage);

	}

	public Package parsePackage(Element umlModelEl){
		//������ ������ �� packageElements
		Package rootPackage;			

		rootPackage = firstParsePackage(umlModelEl, null);
		
		//������ ������ �� packageElements 
		//		��� ������ � ������, �������������
		secondParsePackage(umlModelEl);
		
		return rootPackage;
	}
	
	//������ ������ ������
	private Package firstParsePackage(Element umlModelEl, Package parentPack) {
		
		//umlModelEl - �������-����, ������ �������� ����� ������
		
		int temp;
		Package curUmlPackage = null;
		
		try{
		curUmlPackage = new Package();
		
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
				
					CClass curCClass = classParsHelper.parseClass(curPackEl);
						
					//������������� ������ ������������ �����
					curCClass.setParent(curUmlPackage);
					
					NodeList nestedList = curPackEl.getElementsByTagName("nestedClassifier");
					
					//������ ���������� �������
					for (int i = 0; i < nestedList.getLength(); i++) {
						 Element curNestEl = (Element)nestedList.item(i);
						 
						 CClass curNestedClass = classParsHelper.parseClass(curNestEl);
						 //��������� ����������� ������ ����� ������� �����
						 curNestedClass.setParent(curCClass);
						 
						 List<CClass> childNest = curCClass.getNestedCClasses();
							
						if(childNest == null){
							childNest = new LinkedList<CClass>();
							curCClass.setNestedCClasses(childNest);
						}		//���������� � ��������� ���������� �������
						childNest.add(curNestedClass);
					}
					
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
					List<Package> childsPack = curUmlPackage.getChildrenPackages();
					
					if(childsPack == null){
						childsPack = new LinkedList<Package>();
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
		//������ �� ���� packagedElements
		for (temp = 0; temp < pack_nodes.getLength(); temp++) {
												//������ ������ ��������
			Element curPackEl = (Element)pack_nodes.item(temp);
						
							//������� ��������� ������� - �����
			//��� ���������� ������ ���������� � ������
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
			//������ ����������� Dependency
			if(curPackEl.getAttribute("xmi:type").equals("uml:Dependency")){
				Dependency depend = new Dependency();
				
				String id4Dep = curPackEl.getAttribute("xmi:id");
				
				if(stereotypesWithRefClass.get(id4Dep) != null){
					Set<Stereotype> sterSet = stereotypesWithRefClass.get(id4Dep);
					List<Stereotype> listSter = new LinkedList<Stereotype>(sterSet);
					depend.setStereotypes(listSter);
				}
				List<CClass> suppliers = new LinkedList<CClass>();
				List<CClass> clients = new LinkedList<CClass>();
				
				String id4Supl = curPackEl.getAttribute("supplier");
				String id4Clint = curPackEl.getAttribute("client");
				if(classesWithId.get(id4Supl) != null){
					suppliers.add(classesWithId.get(id4Supl));
					if(classesWithId.get(id4Clint) != null){
						clients.add(classesWithId.get(id4Clint));
						//������ ���u ��� ����� ������������
						depend.setClients(clients);
						depend.setSuppliers(suppliers);
						dependencies.add(depend);
					}
				}
			}
			//������ ����������� Realization
			if(curPackEl.getAttribute("xmi:type").equals("uml:Realization")){
				Realization realiz = new Realization();
								
				CClass supplier;	//� ������ ������ abstraction
				CClass client;		//realization
				
				String id4Supl = curPackEl.getAttribute("supplier");
				String id4Clint = curPackEl.getAttribute("client");
				if(classesWithId.get(id4Supl) != null){
					supplier = classesWithId.get(id4Supl);
					if(classesWithId.get(id4Clint) != null){
						client = classesWithId.get(id4Clint);
						//������ ���u ��� ����� ������������
						realiz.setAbstraction(supplier);
						realiz.setRealization(client);
						realizations.add(realiz);
					}
				}
			}
					
		}
		NodeList gen_nodes = umlModelEl.getElementsByTagName("generalization");
		
		//������ �� ���� generalization
		for (temp = 0; temp < gen_nodes.getLength(); temp++) {
		Element curGenEl = (Element)gen_nodes.item(temp);
						
			//������ ����������� Generalization
			if(curGenEl.getAttribute("xmi:type").equals("uml:Generalization")){
				Generalization gener = new Generalization();
								
				CClass general;	//� ������ ������ abstraction
				CClass specific;		//realization
				
				if(curGenEl.hasAttribute("isSubstitutable")){
					String subst = curGenEl.getAttribute("isSubstitutable");
					if(subst.equals("true"))	gener.setSubstitutable(true);
					if(subst.equals("false"))	gener.setSubstitutable(false);
				}
				else
					gener.setSubstitutable(false);
												
				String id4General = curGenEl.getAttribute("general");	
				Element specEl = (Element)curGenEl.getParentNode();				
				String id4Specif = specEl.getAttribute("xmi:id");
								
				if(classesWithId.get(id4General) != null){
					general = classesWithId.get(id4General);
					if(classesWithId.get(id4Specif) != null){
						specific = classesWithId.get(id4Specif);
						//������ ���u ��� ����� ������������
						gener.setGeneral(general);
						gener.setSpecific(specific);
						generalizations.add(gener);
					}
				}
			}
		}
//		LOGGER.debug("Finished second parse");
		}
		catch (Exception e) {
			e.printStackTrace();
		  }
	}
	
	private void printPackHierarchy(Package curPack){
		
		System.out.println("Pack name : " + curPack.getName());
		
		List<Package> children = curPack.getChildrenPackages();
		if(children != null && !(children.isEmpty())){
			for(Package pe : children){
				System.out.println("In package " + curPack.getName() + " -> ");
				printPackHierarchy(pe);
			}
				
		}
			
	}
}
