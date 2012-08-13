package com.uml.contradiction.converter.core.classes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.converter.core.CoreParser;
import com.uml.contradiction.converter.core.CoreParserImpl;
import com.uml.contradiction.model.VertexType;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationClass;
import com.uml.contradiction.model.cclass.AssociationEnd;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassDiagram;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.cclass.Dependency;
import com.uml.contradiction.model.cclass.Generalization;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.cclass.Realization;
import com.uml.contradiction.model.common.Package;
import com.uml.contradiction.model.common.Stereotype;

//������������ ��� ���������� � �������� �������� ��������� �����������
public class ClassParser extends CoreParserImpl implements CoreParser {

	private static final Logger LOGGER = Logger.getRootLogger();

	private Map<String, CClass> classesWithId = new LinkedHashMap<String, CClass>();
	private Map<String, Association> assocesWithId = new LinkedHashMap<String, Association>();
	private Map<String, ClassDiagram> diagrClassWithId = new LinkedHashMap<String, ClassDiagram>();
	private Map<String, Package> packagesWithId = new LinkedHashMap<String, Package>();

	private Map<String, Set<Stereotype>> stereotypesWithRefClass = new LinkedHashMap<String, Set<Stereotype>>();
	// private Map<String, Constraint> constraintsWithRef = new LinkedHashMap
	// <String, Constraint>();
	private Map<String, Dependency> dependenciesWithId = new LinkedHashMap<String, Dependency>();
	private Map<String, Realization> realizationsWithId = new LinkedHashMap<String, Realization>();
	private Map<String, Generalization> generalizationsWithId = new LinkedHashMap<String, Generalization>();
	private Map<String, MMethod> methodsWithId = new LinkedHashMap<String, MMethod>();
	private Map<String, Attribute> attributesWithId = new LinkedHashMap<String, Attribute>();

	private Package rootPackage;
	Element umlModelElRoot;

	ClassParsHelper classParsHelper; // �������� ��������� ��� ������� ������
	CommonClDiagrHelper commonClDiagrHelper; // �������� ������ ��������� ���
												// ����� ��������

	public Map<String, CClass> getClassesWithId() {
		return classesWithId;
	}

	public Map<String, MMethod> getMethodsWithId() {
		return methodsWithId;
	}

	public Map<String, Attribute> getAttributesWithId() {
		return attributesWithId;
	}

	public ClassParser() {
		super();

		classParsHelper = new ClassParsHelper(classesWithId, assocesWithId,
				stereotypesWithRefClass, methodsWithId, attributesWithId);
		commonClDiagrHelper = new CommonClDiagrHelper();
	}

	// ������� ClassDiagram ��� ������� id
	private void createDiagrmsClass(List<String> diarmsId) {
		if (!diarmsId.isEmpty()) {
			for (String curID : diarmsId) {
				diagrClassWithId.put(curID, new ClassDiagram());
			}
		}
	}

	private Boolean addToClassGraf(Package rootPackage) {
		List<CClass> class_s = ClassGraph.getClasses();
		List<Association> asssoc_s = ClassGraph.getAssociations();
		List<ClassDiagram> clDiagrams = ClassGraph.getClassDiagrams();

		Collection<CClass> colectCls = classesWithId.values();
		for (CClass cls : colectCls) {
			class_s.add(cls);
		}

		Collection<Association> colectAssocs = assocesWithId.values();
		for (Association ass : colectAssocs) {
			asssoc_s.add(ass);
		}

		Collection<ClassDiagram> colectDiagr = diagrClassWithId.values();
		for (ClassDiagram cld : colectDiagr) {
			if (cld.getParentPackageElement() == null) {
				cld.setParentPackageElement(rootPackage);
			}
			clDiagrams.add(cld);
		}

		List<Dependency> depens = ClassGraph.getDependencies();
		Collection<Dependency> dependencs = dependenciesWithId.values();
		for (Dependency dep : dependencs) {
			depens.add(dep);
		}

		List<Realization> reals = ClassGraph.getRealizations();
		Collection<Realization> realizs = realizationsWithId.values();
		for (Realization rel : realizs) {
			reals.add(rel);
		}

		List<Generalization> geners = ClassGraph.getGeneralizations();
		Collection<Generalization> generalizs = generalizationsWithId.values();
		for (Generalization gen : generalizs) {
			geners.add(gen);
		}

		return true;
	}

	@Override
	public List<Object> parse(Element umlModelEl) {
		try {
			umlModelElRoot = umlModelEl;

			// �������� id���� �������� �������
			getAllIdDiagrams(DiagramType.CLASS, umlModelEl);

			// ������ � ��������� ����� ��������� � Id
			createDiagrmsClass(idDiagrams);

			// �������� ���������� �� �������� �� ������
			commonClDiagrHelper.getStereotWithId(umlModelEl,
					stereotypesWithRefClass);

			// �������� ����������� �� �������� �� �������� (�� �� ��������� �
			// ���������)
			// commonClDiagrHelper.getConstraintsWithRef(umlModelEl,
			// constraintsWithRef);

			// ������ �� ���� ��������� HashMap
			// if(!stereotypesWithRefClass.isEmpty()){
			//
			// System.out.println("Size " + stereotypesWithRefClass.size());
			//
			// for(String key : stereotypesWithRefClass.keySet()){
			// System.out.println("Key : " + key + "\n Stereotypes:");
			// Set<Stereotype> sts = stereotypesWithRefClass.get(key);
			// for(Stereotype cls : sts)
			// System.out.println(cls);
			// }
			// }

			// Package rootPackage;
			// ������ uml ������, ������� � ��������� ��������
			rootPackage = parsePackage(umlModelEl);

			// printPackHierarchy(rootPackage);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void makeResult() {
		// ���������� ��������� ������� (��� ���������������)
		commonClDiagrHelper.putClassesAssocInClDiagramm(classesWithId,
				assocesWithId, diagrClassWithId, dependenciesWithId,
				realizationsWithId, generalizationsWithId, umlModelElRoot);

		// ��������
		check();
		
		//��������� ����������� ���������
		changeDirrectionAggregation();
		
		//��������� ������ �� ����� �� ��� ��������, � ���� ���������� ������
		classParsHelper.changeNameTypeOfMethod();

		// ������ �������� � ����������� ���������
		addToClassGraf(rootPackage);

	}

	public Package parsePackage(Element umlModelEl) {
		// ������ ������ �� packageElements
		Package rootPackage;

		rootPackage = firstParsePackage(umlModelEl, null);

		// ������ ������ �� packageElements
		// ��� ������ � ������, �������������
		secondParsePackage(umlModelEl);

		return rootPackage;
	}

	// ������ ������ ������
	private Package firstParsePackage(Element umlModelEl, Package parentPack) {

		// umlModelEl - �������-����, ������ �������� ����� ������

		int temp;
		Package curUmlPackage = null;

		try {
			curUmlPackage = new Package();

			// ���� ��������� �� �������� ����� (default ���)
			if (parentPack != null) {
				curUmlPackage.setName(umlModelEl.getAttribute("name"));

				// ���������� � ����� ��������� ������ � ������� ��� �����������
				commonClDiagrHelper.putParentPackInClassDiadramm(
						diagrClassWithId, umlModelEl, curUmlPackage);
			} else {
				// �������� �����
				curUmlPackage.setName("[[default package]]");
			}

			// ������������� ������������ �����
			curUmlPackage.setParentPackageElement(parentPack);

			// ��������� ����� � �� � ���������
			String id4pack = umlModelEl.getAttribute("xmi:id");
			packagesWithId.put(id4pack, curUmlPackage);

			// �������� � ���������� ����������� ������
			NodeList pack_nodes = umlModelEl
					.getElementsByTagName("packagedElement");

			for (temp = 0; temp < pack_nodes.getLength(); temp++) {

				// ������ ������ �������� packagedElement
				Element curPackEl = (Element) pack_nodes.item(temp);

				// �������� ��� ����� ������������� ������ ����������������
				// ��������
				if ((Element) curPackEl.getParentNode() == umlModelEl) {

					// ������� - �����
					if (curPackEl.getAttribute("xmi:type").equals("uml:Class")
							|| curPackEl.getAttribute("xmi:type").equals(
									"uml:Interface")
							|| curPackEl.getAttribute("xmi:type").equals(
									"uml:Enumeration")
							|| curPackEl.getAttribute("xmi:type").equals(
									"uml:PrimitiveType")
							|| curPackEl.getAttribute("xmi:type").equals(
									"uml:AssociationClass")) {

						CClass curCClass = classParsHelper
								.parseClass(curPackEl);

						// ������������� ������ ������������ �����
						curCClass.setParent(curUmlPackage);

						NodeList nestedList = curPackEl
								.getElementsByTagName("nestedClassifier");

						// ������ ���������� �������
						for (int i = 0; i < nestedList.getLength(); i++) {
							Element curNestEl = (Element) nestedList.item(i);

							CClass curNestedClass = classParsHelper
									.parseClass(curNestEl);
							// ��������� ����������� ������ ����� ������� �����
							curNestedClass.setParent(curCClass);

							List<CClass> childNest = curCClass
									.getNestedCClasses();

							if (childNest == null) {
								childNest = new LinkedList<CClass>();
								curCClass.setNestedCClasses(childNest);
							} // ���������� � ��������� ���������� �������
							childNest.add(curNestedClass);
						}

						// � ����� ��������� ��������� �����
						List<CClass> childrens = curUmlPackage
								.getChildrenClass();

						if (childrens == null) {
							childrens = new LinkedList<CClass>();
							curUmlPackage.setChildrenClass(childrens);
						}
						childrens.add(curCClass);
					}

					// ������� ��������� - ����������
					if (curPackEl.getAttribute("xmi:type").equals(
							"uml:Association")) {

						String id4assoc = curPackEl.getAttribute("xmi:id");

						// //���������� �� ������ ���������� � ���������
						// if(isElementInDiagrammByID(id4assoc)){

						Association curAssoc = new Association();

						if (curPackEl.hasAttribute("name")) {
							curAssoc.setName(curPackEl.getAttribute("name"));
						}

						if (curPackEl.hasAttribute("memberEnd")) {
							// ������ ����� ����������
							NodeList endsList = curPackEl
									.getElementsByTagName("ownedEnd");

							assert endsList.getLength() <= 2 : "assosiation must have 2 ends";

							for (int z = 0; z < endsList.getLength(); z++) {

								AssociationEnd end = classParsHelper
										.getEnd4Assoc((Element) endsList
												.item(z));
								if (z == 0) {
									curAssoc.setEnd1(end);
								}
								if (z == 1) {
									curAssoc.setEnd2(end);
								}
							}
						}
						assocesWithId.put(id4assoc, curAssoc);

					}// ��������� � �����������

					// ���� ������� ������� uml:Package
					if (curPackEl.getAttribute("xmi:type")
							.equals("uml:Package")) {
						List<Package> childsPack = curUmlPackage
								.getChildrenPackages();

						if (childsPack == null) {
							childsPack = new LinkedList<Package>();
							curUmlPackage.setChildrenPackages(childsPack);
						}
						// ������-������� ��������� � �������� ������
						// ���������� ������ ������� ������� ������ ��� �������
						childsPack.add(firstParsePackage(curPackEl,
								curUmlPackage));
					}
				}
			}
			// ���� ����������
			// LOGGER.debug("Finished first parse");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return curUmlPackage;
	}

	private void secondParsePackage(Element umlModelEl) {
		try {

			NodeList pack_nodes = umlModelEl
					.getElementsByTagName("packagedElement");
			int temp;
			// ������ �� ���� packagedElements
			for (temp = 0; temp < pack_nodes.getLength(); temp++) {
				// ������ ������ ��������
				Element curPackEl = (Element) pack_nodes.item(temp);

				// ������� ��������� ������� - �����
				// ��� ���������� ������ ���������� � ������
				if (curPackEl.getAttribute("xmi:type").equals("uml:Class")) {

					NodeList ownedAttrList = curPackEl
							.getElementsByTagName("ownedAttribute");

					for (int k = 0; k < ownedAttrList.getLength(); k++) {
						// ������������� ��� ownedAttribute ��� ������ � ������
						Element curOwnAttrElem = (Element) ownedAttrList
								.item(k);

						// �������� ��� ��� ownedAttribute ��� ����
						if (curOwnAttrElem.hasAttribute("association")) {
							boolean flag = true;

							// �������� AsocPackElem ����������, ��� ��� �����
							String idAsocPackElem = curOwnAttrElem
									.getAttribute("association");
							Association assocCur = assocesWithId
									.get(idAsocPackElem);
							// ������������� ������ ������-����������
							if (assocCur == null) {
								CClass clAssoc = classesWithId
										.get(idAsocPackElem);
								if (clAssoc != null) {
									if (clAssoc.getType() == VertexType.ASSOCIATION_CLASS) {
										assocCur = ((AssociationClass) clAssoc)
												.getAssociation();
									} else {
										flag = false;
									}
								} else {
									flag = false;
								}
							}

							if (flag) {
								if (assocCur.getEnd1() != null) {// � �����
																	// ������
																	// �����
									// ������ ����� ��� ����

									AssociationEnd end_2 = classParsHelper
											.getEnd4Assoc(curOwnAttrElem);
									String role = curOwnAttrElem
											.getAttribute("name");
									if (!role.equals("")) {
										end_2.setRole(role);
									}

									assocCur.setEnd2(end_2);

								} else {

									AssociationEnd end_1 = classParsHelper
											.getEnd4Assoc(curOwnAttrElem);
									String role = curOwnAttrElem
											.getAttribute("name");
									if (!role.equals("")) {
										end_1.setRole(role);
									}

									assocCur.setEnd1(end_1);
								}
							}
						}

					}
				}
				// ������ ����������� Dependency
				if (curPackEl.getAttribute("xmi:type").equals("uml:Dependency")) {
					Dependency depend = new Dependency();

					String id4Dep = curPackEl.getAttribute("xmi:id");

					if (stereotypesWithRefClass.get(id4Dep) != null) {
						Set<Stereotype> sterSet = stereotypesWithRefClass
								.get(id4Dep);
						List<Stereotype> listSter = new LinkedList<Stereotype>(
								sterSet);
						depend.setStereotypes(listSter);
					}
					List<CClass> suppliers = new LinkedList<CClass>();
					List<CClass> clients = new LinkedList<CClass>();

					String id4Supl = curPackEl.getAttribute("supplier");
					String id4Clint = curPackEl.getAttribute("client");
					if (classesWithId.get(id4Supl) != null) {
						suppliers.add(classesWithId.get(id4Supl));
						if (classesWithId.get(id4Clint) != null) {
							clients.add(classesWithId.get(id4Clint));
							// ������ ���u ��� ����� ������������
							depend.setClients(clients);
							depend.setSuppliers(suppliers);
							dependenciesWithId.put(id4Dep, depend);
						}
					}
				}
				// ������ ����������� Realization
				if (curPackEl.getAttribute("xmi:type")
						.equals("uml:Realization")) {
					Realization realiz = new Realization();
					String id4Real = curPackEl.getAttribute("xmi:id");

					CClass supplier; // � ������ ������ abstraction
					CClass client; // realization

					String id4Supl = curPackEl.getAttribute("supplier");
					String id4Clint = curPackEl.getAttribute("client");
					if (classesWithId.get(id4Supl) != null) {
						supplier = classesWithId.get(id4Supl);
						if (classesWithId.get(id4Clint) != null) {
							client = classesWithId.get(id4Clint);
							// ������ ���u ��� ����� ������������
							realiz.setAbstraction(supplier);
							realiz.setRealization(client);
							realizationsWithId.put(id4Real, realiz);
						}
					}
				}

			}
			NodeList gen_nodes = umlModelEl
					.getElementsByTagName("generalization");

			// ������ �� ���� generalization
			for (temp = 0; temp < gen_nodes.getLength(); temp++) {
				Element curGenEl = (Element) gen_nodes.item(temp);

				// ������ ����������� Generalization
				if (curGenEl.getAttribute("xmi:type").equals(
						"uml:Generalization")) {
					Generalization gener = new Generalization();
					String id4Gen = curGenEl.getAttribute("xmi:id");

					CClass general; // � ������ ������ abstraction
					CClass specific; // realization

					if (curGenEl.hasAttribute("isSubstitutable")) {
						String subst = curGenEl.getAttribute("isSubstitutable");
						if (subst.equals("true")) {
							gener.setSubstitutable(true);
						}
						if (subst.equals("false")) {
							gener.setSubstitutable(false);
						}
					} else {
						gener.setSubstitutable(false);
					}

					String id4GeneralCl = curGenEl.getAttribute("general");
					Element specEl = (Element) curGenEl.getParentNode();
					String id4Specif = specEl.getAttribute("xmi:id");

					if (classesWithId.get(id4GeneralCl) != null) {
						general = classesWithId.get(id4GeneralCl);
						if (classesWithId.get(id4Specif) != null) {
							specific = classesWithId.get(id4Specif);
							// ������ ���u ��� ����� ������������
							gener.setGeneral(general);
							gener.setSpecific(specific);
							generalizationsWithId.put(id4Gen, gener);
						}
					}
				}
			}
			// LOGGER.debug("Finished second parse");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void printPackHierarchy(Package curPack) {

		System.out.println("Pack name : " + curPack.getName());

		List<Package> children = curPack.getChildrenPackages();
		if (children != null && !(children.isEmpty())) {
			for (Package pe : children) {
				System.out.println("In package " + curPack.getName() + " -> ");
				printPackHierarchy(pe);
			}

		}

	}

	// �������� �� ������� ����� ������ � ����������
	// � ��������� ������ ��� ��������� �� ��������� ����������
	// �� �� �� ������-���������� � ��������� �������
	private boolean check() {
		boolean okFlag = true;
		for (String key : assocesWithId.keySet()) {
			Association ass = assocesWithId.get(key);
			if (ass.getEnd1() == null || ass.getEnd2() == null) {
				LOGGER.error("Association have empty end");
				assocesWithId.remove(key);
				okFlag = false;
			}
		}
		return okFlag;
	}
	
	//��������� ����������� ���������
	private void changeDirrectionAggregation(){
		for (String key : assocesWithId.keySet()) {
			Association ass = assocesWithId.get(key);
			if (ass.getEnd1().getAggregationKind() == AggregationKind.COMPOSITE) {
				ass.getEnd2().setAggregationKind(AggregationKind.COMPOSITE);
				ass.getEnd1().setAggregationKind(AggregationKind.NONE);
			}else{
				if (ass.getEnd2().getAggregationKind() == AggregationKind.COMPOSITE) {
					ass.getEnd1().setAggregationKind(AggregationKind.COMPOSITE);
					ass.getEnd2().setAggregationKind(AggregationKind.NONE);
				}else{
					if (ass.getEnd2().getAggregationKind() == AggregationKind.SHARED) {
						ass.getEnd1().setAggregationKind(AggregationKind.SHARED);
						ass.getEnd2().setAggregationKind(AggregationKind.NONE);
					}else{
						if (ass.getEnd1().getAggregationKind() == AggregationKind.SHARED) {
							ass.getEnd2().setAggregationKind(AggregationKind.SHARED);
							ass.getEnd1().setAggregationKind(AggregationKind.NONE);
						}
					}
				}
			}
		}
	}
	
}
