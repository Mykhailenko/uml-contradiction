package com.uml.contradiction.converter.core.classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.converter.core.CoreParserImpl;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationClass;
import com.uml.contradiction.model.cclass.AssociationEnd;
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.cclass.Multiplicity;
import com.uml.contradiction.model.cclass.NaryAssociationClass;
import com.uml.contradiction.model.cclass.Navigability;
import com.uml.contradiction.model.cclass.Parameter;
import com.uml.contradiction.model.cclass.Scope;
import com.uml.contradiction.model.cclass.Visibility;
import com.uml.contradiction.model.common.Stereotype;
import com.uml.contradiction.model.common.Type;
import com.uml.contradiction.model.common.UMLType;
import com.uml.contradiction.model.common.UserType;

//пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
public class ClassParsHelper {
	private Map<String, CClass> classesWithId;
	private Map<String, Association> assocesWithId;
	// private Map<String, Constraint> constraintsWithRef;
	private Map<String, Set<Stereotype>> stereotypesWithRefClass;
	private Map<String, MMethod> methodsWithId;
	private Map<String, Attribute> attributesWithId;

	private static final Logger LOGGER = Logger.getRootLogger();

	public ClassParsHelper(
			Map<String, CClass> classesWithId,
			Map<String, Association> assocesWithId,
			// Map<String, Constraint> constraintsWithRef,
			Map<String, Set<Stereotype>> stereotypesWithRefClass,
			Map<String, MMethod> methodsWithId,
			Map<String, Attribute> attributesWithId) {
		super();
		this.classesWithId = classesWithId;
		this.assocesWithId = assocesWithId;
		// this.constraintsWithRef = constraintsWithRef;
		this.stereotypesWithRefClass = stereotypesWithRefClass;
		this.methodsWithId = methodsWithId;
		this.attributesWithId = attributesWithId;
	}

	// get set
	public Map<String, CClass> getClassesWithId() {
		return classesWithId;
	}

	public void setClassesWithId(Map<String, CClass> classesWithId) {
		this.classesWithId = classesWithId;
	}

	public Map<String, Association> getAssocesWithId() {
		return assocesWithId;
	}

	public void setAssocesWithId(Map<String, Association> assocesWithId) {
		this.assocesWithId = assocesWithId;
	}

	public CClass parseClass(Element curClElem) {
		CClass curCClass = new CClass();
		CoreParserImpl corePars = new CoreParserImpl();

		Element extension = (Element) curClElem.getElementsByTagName(
				"xmi:Extension").item(0);
		// пїЅпїЅпїЅпїЅ N-пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
		if (extension.getParentNode() == curClElem) {
			String modelVal = corePars.getAttrByNameAndTag(extension,
					"modelType", "value");
			if (modelVal.equals("NARY")) {
				curCClass = new NaryAssociationClass();
			}
		}
		// пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ- пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
		if (curClElem.getAttribute("xmi:type").equals("uml:AssociationClass")) {
			curCClass = new AssociationClass();
			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
			// пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
			if (extension.getParentNode() == curClElem) {
				String nameAssoc = corePars.getAttrByNameAndTag(extension,
						"association", "name");
				String idAssoc = corePars.getAttrByNameAndTag(extension,
						"association", "xmi:id");
				if (idAssoc != null) {
					Association ass = new Association();
					ass.setName(nameAssoc);
					// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
					// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅ
					// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅ
					// пїЅпїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
					assocesWithId.put(idAssoc, ass);
					((AssociationClass) curCClass).setAssociation(ass);
				}
			}
		}

		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ CClass
		String id4class = curClElem.getAttribute("xmi:id");

		if (stereotypesWithRefClass.get(id4class) != null) {
			curCClass.setStereotypes(stereotypesWithRefClass.get(id4class));
		}

		curCClass.setName(curClElem.getAttribute("name"));

		String visibty = curClElem.getAttribute("visibility");

		if (visibty.equals("public")) {
			curCClass.setVisibility(Visibility.PUBLIC);
		}
		if (visibty.equals("private")) {
			curCClass.setVisibility(Visibility.PRIVATE);
		}
		if (visibty.equals("protected")) {
			curCClass.setVisibility(Visibility.PROTECTED);
		}

		String isAbstract = curClElem.getAttribute("isAbstract");
		if (isAbstract.equals("false")) {
			curCClass.setAbstract(false);
		}
		if (isAbstract.equals("true")) {
			curCClass.setAbstract(true);
		}

		// пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
		List<Attribute> attributes = getAttr4Class(curClElem);
		curCClass.setAttributes(attributes);

		// пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
		List<MMethod> methods = getMethods4Class(curClElem);
		curCClass.setMethods(methods);

		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅ ID пїЅ map
		classesWithId.put(id4class, curCClass);

		return curCClass;
	}

	public List<Attribute> getAttr4Class(Element classElement) {
		NodeList attrList = classElement.getElementsByTagName("ownedAttribute");

		List<Attribute> attributes = new ArrayList<Attribute>();

		CoreParserImpl corePars = new CoreParserImpl();

		for (int k = 0; k < attrList.getLength(); k++) {

			Element curAttrElem = (Element) attrList.item(k);

			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ
			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
			if ((Element) curAttrElem.getParentNode() == classElement) {
				Attribute attr_1 = new Attribute();

				// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅ
				// ownedAttribute пїЅпїЅпїЅ пїЅпїЅпїЅпїЅ
				if (!curAttrElem.hasAttribute("association")) {
					String attrId = curAttrElem.getAttribute("xmi:id");

					attr_1.setName(curAttrElem.getAttribute("name"));

					String visibty = curAttrElem.getAttribute("visibility");
					if (visibty.equals("public")) {
						attr_1.setVisibility(Visibility.PUBLIC);
					}
					if (visibty.equals("private")) {
						attr_1.setVisibility(Visibility.PRIVATE);
					}
					if (visibty.equals("protected")) {
						attr_1.setVisibility(Visibility.PROTECTED);
					}

					String isDeriv = curAttrElem.getAttribute("isDerived");
					if (isDeriv.equals("false")) {
						attr_1.setDerived(false);
					}
					if (isDeriv.equals("true")) {
						attr_1.setDerived(true);
					}

					String scope = curAttrElem.getAttribute("ownerScope");
					if (scope.equals("instance")) {
						attr_1.setScope(Scope.INSTANCE);
					}
					if (scope.equals("classifier")) {
						attr_1.setScope(Scope.CLASSIFIER);
					}

					String defVal = corePars.getAttrByNameAndTag(curAttrElem,
							"defaultValue", "value");
					if (defVal != null) {
						attr_1.setDdefault(defVal);
					}

					Multiplicity mult = getMultiplicity(curAttrElem);
					if (mult != null) {
						attr_1.setMultiplicity(mult);
					}

					attr_1.setType(getType(curAttrElem));

					// Constraint constr = constraintsWithRef.get(attrId);
					// if(constr != null)
					// attr_1.setConstraints(constr);

					attributes.add(attr_1);

					attributesWithId.put(attrId, attr_1);
				}
			}
		}
		return attributes;
	}

	private Type getType(Element elem) {
		Type type = null;

		if (elem.hasAttribute("type")) {
			String typeVal = elem.getAttribute("type");
			if (typeVal != null) {
				String[] arr = typeVal.split("_");
				if (arr.length != 2) {
					LOGGER.debug("Trouble with attribute type " + "in tag  ");
				}
				if (arr != null) {
					if (arr[0].equals("int")) {
						type = UMLType.INTEGER;
					} else {
						if (arr[0].equals("boolean")) {
							type = UMLType.BOOLEAN;
						} else {
							if (arr[0].equals("string")) {
								type = UMLType.STRING;
							} else {								
								if(arr.length >= 2 && arr[1].equals("id")){
									type = new UserType(arr[0]);
								}else
									type = new UserType(typeVal);
							}
						}
					}
				}
			}
		}
		return type;
	}

	public List<MMethod> getMethods4Class(Element classElement) {
		NodeList methdsList = classElement
				.getElementsByTagName("ownedOperation");

		List<MMethod> methods = new ArrayList<MMethod>();

		for (int k = 0; k < methdsList.getLength(); k++) {
			Element curMethElem = (Element) methdsList.item(k);

			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ
			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
			if ((Element) curMethElem.getParentNode() == classElement) {
				MMethod meth_1 = new MMethod();

				String idMeth = curMethElem.getAttribute("xmi:id");

				meth_1.setName(curMethElem.getAttribute("name"));

				String visibty = curMethElem.getAttribute("visibility");
				if (visibty.equals("public")) {
					meth_1.setVisibility(Visibility.PUBLIC);
				}
				if (visibty.equals("private")) {
					meth_1.setVisibility(Visibility.PRIVATE);
				}
				if (visibty.equals("protected")) {
					meth_1.setVisibility(Visibility.PROTECTED);
				}

				String scope = curMethElem.getAttribute("ownerScope");
				if (scope.equals("instance")) {
					meth_1.setScope(Scope.INSTANCE);
				}
				if (scope.equals("classifier")) {
					meth_1.setScope(Scope.CLASSIFIER);
				}

				if (stereotypesWithRefClass.get(idMeth) != null) {
					meth_1.setStereotypes(stereotypesWithRefClass.get(idMeth));
				}

				// пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
				List<Parameter> params = new LinkedList<Parameter>();
				NodeList parametersList = curMethElem
						.getElementsByTagName("ownedParameter");
				for (int i = 0; i < parametersList.getLength(); i++) {
					Element curParam = (Element) parametersList.item(i);

					// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
					// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
					if (curParam.getAttribute("direction").equals("return")) {
						if (curParam.hasAttribute("type")) {
							String typeVal = curParam.getAttribute("type");
							if (typeVal != null) {
								String[] arr = typeVal.split("_");
								if (arr.length != 2) {
									LOGGER.debug("Trouble with parameter type "
											+ "in tag  ");
								}
								if (arr != null) {
									if(arr.length >= 2 && arr[1].equals("id")){
										meth_1.setReturnResult(arr[0]);
									}else
										meth_1.setReturnResult(typeVal);
								}
							}
						}
					}
					if (curParam.hasAttribute("kind")) {
						Parameter par = new Parameter();

						if (curParam.hasAttribute("name")) {
							par.setName(curParam.getAttribute("name"));
						}

						if (curParam.hasAttribute("type")) {
							par.setType(getType(curParam));
						}

						params.add(par);
					}
				}
				meth_1.setParameters(params);
				methodsWithId.put(idMeth, meth_1);

				methods.add(meth_1);
			}
		}
		return methods;
	}

	// пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
	public AssociationEnd getEnd4Assoc(Element endElement) {
		AssociationEnd assEnd = new AssociationEnd();

		String aggreg = endElement.getAttribute("aggregation");
		if (aggreg.equals("none")) {
			assEnd.setAggregationKind(AggregationKind.NONE);
		}
		if (aggreg.equals("shared")) {
			assEnd.setAggregationKind(AggregationKind.SHARED);
		}
		if (aggreg.equals("composite")) {
			assEnd.setAggregationKind(AggregationKind.COMPOSITE);
		}

		String navig = endElement.getAttribute("isNavigable");
		if (navig.equals("true")) {
			assEnd.setNavigability(Navigability.NAVIGABLE);
		}
		if (navig.equals("false")) {
			assEnd.setNavigability(Navigability.NON_NAVIGABLE);
		}

		String derived = endElement.getAttribute("isDerived");
		if (derived.equals("true")) {
			assEnd.setDerived(true);
		}
		if (derived.equals("false")) {
			assEnd.setDerived(false);
		}

		String visibty = endElement.getAttribute("visibility");
		if (visibty.equals("public")) {
			assEnd.setVisibility(Visibility.PUBLIC);
		}
		if (visibty.equals("private")) {
			assEnd.setVisibility(Visibility.PRIVATE);
		}
		if (visibty.equals("protected")) {
			assEnd.setVisibility(Visibility.PROTECTED);
		}

		// Constraint constr =
		// constraintsWithRef.get(endElement.getAttribute("xmi:id"));
		// if(constr != null)
		// assEnd.setConstraint(constr);

		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ
		String idAsocedClass = endElement.getAttribute("type");
		CClass assCClass = classesWithId.get(idAsocedClass);
		if (assCClass != null) {
			assEnd.setAssociatedClass(assCClass);
		}

		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
		Multiplicity multipl = getMultiplicity(endElement);

		if (multipl != null) {
			assEnd.setMultiplicity(multipl);
		}

		return assEnd;
	}

	private Multiplicity getMultiplicity(Element el) {
		Multiplicity multipl = null;
		Integer lowerBound = null;
		Double upperBound = null;
		try {
			NodeList nlistLow = el.getElementsByTagName("lowerValue");
			NodeList nlistHi = el.getElementsByTagName("upperValue");
			if (nlistLow.getLength() > 0) { // разбор нижней кратности
				String lowValue = ((Element) nlistLow.item(0))
						.getAttribute("value");

				if (lowValue.equals("*")) {
					lowerBound = 0;
				} else {
					lowerBound = Integer.valueOf(lowValue);
				}
			}

			if (nlistHi.getLength() > 0) { // разбор верхней кратности
				String highValue = ((Element) nlistHi.item(0))
						.getAttribute("value");

				if (highValue.equals("*")) {
					upperBound = Double.POSITIVE_INFINITY;
					LOGGER.debug("We have upper value *");
					if (lowerBound == null) {
						lowerBound = new Integer(0);
					}
				} else {
					upperBound = Double.valueOf(highValue);
				}
			}
			if (lowerBound != null && upperBound != null) {
				multipl = new Multiplicity(lowerBound, upperBound);
			}

		} catch (Exception e) {
			e.printStackTrace();
			multipl = null;
		}
		return multipl;
	}
	
	//the return (argument) type of method can reference on user class type
	//change id-reference on name of class
	public void changeNameTypeOfMethod() {
		for(String key : methodsWithId.keySet()){
			MMethod curMeth = methodsWithId.get(key);
			for(Parameter param : curMeth.getParameters()){
				if(param.getType().getClass() == UserType.class){
					CClass refCclass = classesWithId.get(param.getType().getName());
					if(refCclass != null){
						System.out.println("Faaaaaaaaaaaaa " + refCclass.getName());
						((UserType)param.getType()).setName(refCclass.getName());
					}
				}
			}
			
			String retRes = curMeth.getReturnResult();
			if(retRes != null){
				CClass refCclass = classesWithId.get(retRes);
				if(refCclass != null)
					curMeth.setReturnResult(refCclass.getName());
			}
		}
	}
}
