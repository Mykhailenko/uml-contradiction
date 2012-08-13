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

//предназначен для управления и хранения наиболее обширными структурами
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

	ClassParsHelper classParsHelper; // содержит помощника для разбора класса
	CommonClDiagrHelper commonClDiagrHelper; // содержит общего помощника для
												// класс диаграмм

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

	// создаем ClassDiagram для каждого id
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

			// получаем idвсех диаграмм классов
			getAllIdDiagrams(DiagramType.CLASS, umlModelEl);

			// кладем в коллекцию новые диаграммы и Id
			createDiagrmsClass(idDiagrams);

			// получаем стереотипы со ссылками на классы
			commonClDiagrHelper.getStereotWithId(umlModelEl,
					stereotypesWithRefClass);

			// получаем ограничения со ссылками на элементы (мы их добавляем к
			// атрибутам)
			// commonClDiagrHelper.getConstraintsWithRef(umlModelEl,
			// constraintsWithRef);

			// проход по всем элементам HashMap
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
			// разбор uml пакета, начиная с корневого элемента
			rootPackage = parsePackage(umlModelEl);

			// printPackHierarchy(rootPackage);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void makeResult() {
		// заполнение диаграммы классов (как визуализируется)
		commonClDiagrHelper.putClassesAssocInClDiagramm(classesWithId,
				assocesWithId, diagrClassWithId, dependenciesWithId,
				realizationsWithId, generalizationsWithId, umlModelElRoot);

		// проверка
		check();
		
		//изменение направления агрегации
		changeDirrectionAggregation();
		
		//изменение ссылки на класс на его название, в типе аргументов метода
		classParsHelper.changeNameTypeOfMethod();

		// запись значений в статические коллекции
		addToClassGraf(rootPackage);

	}

	public Package parsePackage(Element umlModelEl) {
		// первый проход по packageElements
		Package rootPackage;

		rootPackage = firstParsePackage(umlModelEl, null);

		// второй проход по packageElements
		// для работы с ролями, наследованием
		secondParsePackage(umlModelEl);

		return rootPackage;
	}

	// разбор одного пакета
	private Package firstParsePackage(Element umlModelEl, Package parentPack) {

		// umlModelEl - элемент-узел, внутри которого будет разбор

		int temp;
		Package curUmlPackage = null;

		try {
			curUmlPackage = new Package();

			// если разбираем не корневой пакет (default имя)
			if (parentPack != null) {
				curUmlPackage.setName(umlModelEl.getAttribute("name"));

				// добавление к класс диаграмме пакета в котором она содержиться
				commonClDiagrHelper.putParentPackInClassDiadramm(
						diagrClassWithId, umlModelEl, curUmlPackage);
			} else {
				// корневой пакет
				curUmlPackage.setName("[[default package]]");
			}

			// установливаем родительский пакет
			curUmlPackage.setParentPackageElement(parentPack);

			// добавляем пакет с Ид в коллекцию
			String id4pack = umlModelEl.getAttribute("xmi:id");
			packagesWithId.put(id4pack, curUmlPackage);

			// работаем с внутренним содержанием пакета
			NodeList pack_nodes = umlModelEl
					.getElementsByTagName("packagedElement");

			for (temp = 0; temp < pack_nodes.getLength(); temp++) {

				// разбор одного элемента packagedElement
				Element curPackEl = (Element) pack_nodes.item(temp);

				// проверка что будем рассматривать только непосредственных
				// потомков
				if ((Element) curPackEl.getParentNode() == umlModelEl) {

					// элемент - класс
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

						// устанавливаем классу родительский пакет
						curCClass.setParent(curUmlPackage);

						NodeList nestedList = curPackEl
								.getElementsByTagName("nestedClassifier");

						// разбор внутренних классов
						for (int i = 0; i < nestedList.getLength(); i++) {
							Element curNestEl = (Element) nestedList.item(i);

							CClass curNestedClass = classParsHelper
									.parseClass(curNestEl);
							// родителем внутреннего класса будет текущий класс
							curNestedClass.setParent(curCClass);

							List<CClass> childNest = curCClass
									.getNestedCClasses();

							if (childNest == null) {
								childNest = new LinkedList<CClass>();
								curCClass.setNestedCClasses(childNest);
							} // добавление к коллекции внутренних классов
							childNest.add(curNestedClass);
						}

						// в пакет добавляем собранный класс
						List<CClass> childrens = curUmlPackage
								.getChildrenClass();

						if (childrens == null) {
							childrens = new LinkedList<CClass>();
							curUmlPackage.setChildrenClass(childrens);
						}
						childrens.add(curCClass);
					}

					// элемент диаграммы - ассоциация
					if (curPackEl.getAttribute("xmi:type").equals(
							"uml:Association")) {

						String id4assoc = curPackEl.getAttribute("xmi:id");

						// //относиться ли данная ассоциация к диаграмме
						// if(isElementInDiagrammByID(id4assoc)){

						Association curAssoc = new Association();

						if (curPackEl.hasAttribute("name")) {
							curAssoc.setName(curPackEl.getAttribute("name"));
						}

						if (curPackEl.hasAttribute("memberEnd")) {
							// разбор конца ассоциации
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

					}// закончили с ассоциацией

					// если текущий элемент uml:Package
					if (curPackEl.getAttribute("xmi:type")
							.equals("uml:Package")) {
						List<Package> childsPack = curUmlPackage
								.getChildrenPackages();

						if (childsPack == null) {
							childsPack = new LinkedList<Package>();
							curUmlPackage.setChildrenPackages(childsPack);
						}
						// пакеты-потомки добавляем к текущему пакету
						// рекурсивно вызвав функцию разбора пакета для потомка
						childsPack.add(firstParsePackage(curPackEl,
								curUmlPackage));
					}
				}
			}
			// цикл закончился
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
			// проход по всем packagedElements
			for (temp = 0; temp < pack_nodes.getLength(); temp++) {
				// разбор одного элемента
				Element curPackEl = (Element) pack_nodes.item(temp);

				// элемент диаграммы классов - класс
				// для заполнения концов ассоциация с ролями
				if (curPackEl.getAttribute("xmi:type").equals("uml:Class")) {

					NodeList ownedAttrList = curPackEl
							.getElementsByTagName("ownedAttribute");

					for (int k = 0; k < ownedAttrList.getLength(); k++) {
						// просматриваем все ownedAttribute для поиска с ролями
						Element curOwnAttrElem = (Element) ownedAttrList
								.item(k);

						// проверка что это ownedAttribute для роли
						if (curOwnAttrElem.hasAttribute("association")) {
							boolean flag = true;

							// получили AsocPackElem ассоциацию, чей это конец
							String idAsocPackElem = curOwnAttrElem
									.getAttribute("association");
							Association assocCur = assocesWithId
									.get(idAsocPackElem);
							// рассматриваем случай класса-ассоциации
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
								if (assocCur.getEnd1() != null) {// с ролью
																	// второй
																	// конец
									// первый конец уже есть

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
				// разбор зависимости Dependency
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
							// только еслu оба конца присутствуют
							depend.setClients(clients);
							depend.setSuppliers(suppliers);
							dependenciesWithId.put(id4Dep, depend);
						}
					}
				}
				// разбор зависимости Realization
				if (curPackEl.getAttribute("xmi:type")
						.equals("uml:Realization")) {
					Realization realiz = new Realization();
					String id4Real = curPackEl.getAttribute("xmi:id");

					CClass supplier; // в данном случае abstraction
					CClass client; // realization

					String id4Supl = curPackEl.getAttribute("supplier");
					String id4Clint = curPackEl.getAttribute("client");
					if (classesWithId.get(id4Supl) != null) {
						supplier = classesWithId.get(id4Supl);
						if (classesWithId.get(id4Clint) != null) {
							client = classesWithId.get(id4Clint);
							// только еслu оба конца присутствуют
							realiz.setAbstraction(supplier);
							realiz.setRealization(client);
							realizationsWithId.put(id4Real, realiz);
						}
					}
				}

			}
			NodeList gen_nodes = umlModelEl
					.getElementsByTagName("generalization");

			// проход по всем generalization
			for (temp = 0; temp < gen_nodes.getLength(); temp++) {
				Element curGenEl = (Element) gen_nodes.item(temp);

				// разбор зависимости Generalization
				if (curGenEl.getAttribute("xmi:type").equals(
						"uml:Generalization")) {
					Generalization gener = new Generalization();
					String id4Gen = curGenEl.getAttribute("xmi:id");

					CClass general; // в данном случае abstraction
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
							// только еслu оба конца присутствуют
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

	// проверка на наличие обоих концов у ассоциации
	// в противном случае они улаляются из коллекции ассоциаций
	// но не из класса-ассоциации и диаграммы классов
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
	
	//изменение направления агрегации
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
