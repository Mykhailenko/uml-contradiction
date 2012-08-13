package com.uml.contradiction.converter.core.classes;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassDiagram;
import com.uml.contradiction.model.cclass.Dependency;
import com.uml.contradiction.model.cclass.Generalization;
import com.uml.contradiction.model.cclass.Realization;
import com.uml.contradiction.model.common.Package;
import com.uml.contradiction.model.common.Stereotype;
import com.uml.contradiction.model.common.UMLClassStereotype;
import com.uml.contradiction.model.common.UserStereotype;

public class CommonClDiagrHelper {
	private static final Logger logger = Logger
			.getLogger(CommonClDiagrHelper.class);

	// �������� ���������� �� �������� �� ������
	public void getStereotWithId(Element umlModel,
			Map<String, Set<Stereotype>> streotypeWithRefClass) {

		if (umlModel.getNextSibling() == null) {
			return;
		}

		Node sterNode = umlModel.getNextSibling();
		String tagName = null;

		while (sterNode != null) { // ������� ��� ��������� ���� ��������

			if (sterNode.getNodeType() == Node.ELEMENT_NODE) {
				Element stereotypeEl = (Element) sterNode;

				tagName = stereotypeEl.getTagName();

				String stereotypeName = getStereotypeName(tagName);

				// ���� ����� ���-���������
				if (stereotypeName != null) {
					boolean flagIsUmlSter = false;
					boolean isRefOnIdClass = false;

					Stereotype curStereot = null;
					String refOnIdClass = null;

					// ��������� ������ �� �����
					if (stereotypeEl.hasAttribute("base_Class")) {
						refOnIdClass = stereotypeEl.getAttribute("base_Class");
						isRefOnIdClass = true;
					} else {
						if (stereotypeEl.hasAttribute("base_Interface")) {
							refOnIdClass = stereotypeEl
									.getAttribute("base_Interface");
							isRefOnIdClass = true;
						} else {
							if (stereotypeEl.hasAttribute("base_Enumeration")) {
								refOnIdClass = stereotypeEl
										.getAttribute("base_Enumeration");
								isRefOnIdClass = true;
							} else {
								if (stereotypeEl
										.hasAttribute("base_PrimitiveType")) {
									refOnIdClass = stereotypeEl
											.getAttribute("base_PrimitiveType");
									isRefOnIdClass = true;
								} else {
									if (stereotypeEl
											.hasAttribute("base_Dependency")) {
										refOnIdClass = stereotypeEl
												.getAttribute("base_Dependency");
										isRefOnIdClass = true;
									} else {
										if (stereotypeEl
												.hasAttribute("base_Operation")) {
											refOnIdClass = stereotypeEl
													.getAttribute("base_Operation");
											isRefOnIdClass = true;
										} else {
											logger.warn("Can't take from steretype reference on class:"
													+ " because unknown reference name");
										}
									}
								}
							}
						}
					}

					// ���� UMLClassStereotype
					if (stereotypeName.equals("control")) {
						curStereot = UMLClassStereotype.CONTROL;
						flagIsUmlSter = true;
					}
					if (stereotypeName.equals("entity")) {
						curStereot = UMLClassStereotype.ENTITY;
						flagIsUmlSter = true;
					}
					if (stereotypeName.equals("utility")) {
						curStereot = UMLClassStereotype.UTILITY;
						flagIsUmlSter = true;
					}
					if (stereotypeName.equals("implementationClass")) {
						curStereot = UMLClassStereotype.IMPLEMENTATION_CLASS;
						flagIsUmlSter = true;
					}
					// ���� UserStereotype
					if (!flagIsUmlSter) {
						curStereot = new UserStereotype(stereotypeName);
					}

					assert isRefOnIdClass == false : "unknown reference name on class in strerotype";

					if (refOnIdClass != null && curStereot != null) {
						Set<Stereotype> setSters;
						// ���� ������ �� ����� � ID refOnIdClass �� �� ����
						if (streotypeWithRefClass.get(refOnIdClass) == null) {

							setSters = new HashSet<Stereotype>();
							setSters.add(curStereot);
							streotypeWithRefClass.put(refOnIdClass, setSters);
						} else {
							setSters = streotypeWithRefClass.get(refOnIdClass);
							setSters.add(curStereot);
						}
					}
				}
			}
			if (sterNode.getNextSibling() == null) {
				break;
			}

			// ������� �� ��������� ����
			sterNode = sterNode.getNextSibling();
		}
	}

	private String getStereotypeName(String tagName) {

		boolean checked = false;
		String[] arrPartColon = tagName.split(":");

		// �������� ��� ��� ��� � ������ ������
		if (tagName.split(":") != null) {
			String profileName = arrPartColon[0];
			int lenProf = profileName.length();
			// �������� ����� ����� profile
			String prof = profileName.substring(lenProf - 7);
			if (prof.equals("profile")) {
				checked = true;
			}
		}

		if (checked) {
			return arrPartColon[1];
		} else {
			return null;
		}
	}

	// public void getConstraintsWithRef(Element umlModel, Map<String,
	// Constraint> constraintsWithRef){
	//
	// try{
	//
	// NodeList rules = umlModel.getElementsByTagName("ownedRule");
	// int temp;
	//
	// for (temp = 0; temp < rules.getLength(); temp++) {
	// //������ ������ ��������
	// Element curRule = (Element)rules.item(temp);
	//
	// String strOfRefs = curRule.getAttribute("constrainedElement");
	// String[] idElems = strOfRefs.split(" ");
	// Constraint constr = null;
	//
	// Element commentEl =
	// (Element)curRule.getElementsByTagName("ownedComment").item(0);
	// if(commentEl != null){
	// Node body = commentEl.getElementsByTagName("body").item(0);
	// if(body != null)
	// constr = new Constraint(body.getTextContent());
	// }
	// for(int i =0; i< idElems.length; i++)
	// constraintsWithRef.put(idElems[i], constr);
	// }
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// ���������� � ����� ��������� ������, � ������� ��� �����������
	public boolean putParentPackInClassDiadramm(
			Map<String, ClassDiagram> diagrClassWithId, Element umlModelEl,
			Package curUmlPackage) {
		boolean hasSubDiagrams = false;

		NodeList extensionList = umlModelEl
				.getElementsByTagName("xmi:Extension");
		Element firstExt = (Element) extensionList.item(0);

		if (firstExt.getParentNode() == umlModelEl) {
			NodeList subDiagrams = firstExt.getElementsByTagName("subdiagram");

			// ��� ���� �����- ������ �� ID ��������� �������
			for (int i = 0; i < subDiagrams.getLength(); i++) {
				Element curSubDiagram = (Element) subDiagrams.item(i);

				if (curSubDiagram.getParentNode() == firstExt) {
					String idSub = curSubDiagram.getAttribute("xmi:value");
					ClassDiagram clDiagr = diagrClassWithId.get(idSub);

					if (clDiagr != null) { // ������������� ����� ��� ���������
						clDiagr.setParentPackageElement(curUmlPackage);
						hasSubDiagrams = true;
					}
				} else {
					break;
				}
			}
		}

		return hasSubDiagrams;
	}

	// ��������� ������ �� ������ � ���������� � ��������� �������
	public void putClassesAssocInClDiagramm(Map<String, CClass> classesWithId,
			Map<String, Association> assocesWithId,
			Map<String, ClassDiagram> diagrClassWithId,
			Map<String, Dependency> dependenciesWithId,
			Map<String, Realization> realizationsWithId,
			Map<String, Generalization> generalizationsWithId,
			Element umlModelEl) {

		// �������� �� ���� ���������� � ������� �������� �������
		NodeList diagramAll = umlModelEl.getElementsByTagName("uml:Diagram");

		String diagrType = "ClassDiagram";

		for (int temp = 0; temp < diagramAll.getLength(); temp++) {
			Element curDiagr = (Element) diagramAll.item(temp);

			if (curDiagr.getAttribute("diagramType").equals(diagrType)) {
				String idCurDiagram = curDiagr.getAttribute("xmi:id");
				ClassDiagram diagram = diagrClassWithId.get(idCurDiagram);

				if (diagram != null) {
					diagram.setName(curDiagr.getAttribute("name"));

					NodeList mainElementOfDiagr = curDiagr
							.getElementsByTagName("uml:Diagram.element");
						
					if(mainElementOfDiagr != null){
							
						Element diagrEl = ((Element) mainElementOfDiagr.item(0));
								
						if(diagrEl != null)	{
							NodeList listElementsOfDiagr = diagrEl.getElementsByTagName("uml:DiagramElement");
							
							if(listElementsOfDiagr != null)
							{
							for (int i = 0; i < listElementsOfDiagr.getLength(); i++) {
								Element curElem = (Element) listElementsOfDiagr.item(i);
								String refOnObject = curElem.getAttribute("subject");
								String typeElem = curElem
										.getAttribute("preferredShapeType");
		
								// ���� ������� - �����
								if (typeElem.equals("Class")) {
									CClass classCur = classesWithId.get(refOnObject);
		
									if (classCur != null) {
										List<CClass> classes = diagram.getClasses();
		
										if (classes == null) {
											classes = new LinkedList<CClass>();
											diagram.setClasses(classes);
										}
										classes.add(classCur);
									}
								}
								// ���� ������� - ����������
								if (typeElem.equals("Association")) {
									Association assocCur = assocesWithId
											.get(refOnObject);
		
									if (assocCur != null) {
										List<Association> assoces = diagram
												.getAssociations();
		
										if (assoces == null) {
											assoces = new LinkedList<Association>();
											diagram.setAssociations(assoces);
										}
										assoces.add(assocCur);
									}
								}
								// ���� ������� - Generalization
								if (typeElem.equals("Generalization")) {
									Generalization genCur = generalizationsWithId
											.get(refOnObject);
		
									if (genCur != null) {
										List<Generalization> gens = diagram
												.getGeneralizations();
		
										if (gens == null) {
											gens = new LinkedList<Generalization>();
											diagram.setGeneralizations(gens);
										}
										gens.add(genCur);
									}
								}
								// ���� ������� - Realization
								if (typeElem.equals("Realization")) {
									Realization realCur = realizationsWithId
											.get(refOnObject);
		
									if (realCur != null) {
										List<Realization> reals = diagram
												.getRealizations();
		
										if (reals == null) {
											reals = new LinkedList<Realization>();
											diagram.setRealizations(reals);
										}
										reals.add(realCur);
									}
								}
								// ���� ������� - Dependency
								if (typeElem.equals("Dependency")) {
									Dependency depenCur = dependenciesWithId
											.get(refOnObject);
		
									if (depenCur != null) {
										List<Dependency> deps = diagram
												.getDependencies();
		
										if (deps == null) {
											deps = new LinkedList<Dependency>();
											diagram.setDependencies(deps);
										}
										deps.add(depenCur);
									}
								}
							}
						}
					}
					}
				}
			}
		}
	}

}
