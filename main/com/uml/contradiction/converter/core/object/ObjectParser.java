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
import com.uml.contradiction.model.cclass.Attribute;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.object.AttributeObj;
import com.uml.contradiction.model.object.Link;
import com.uml.contradiction.model.object.LinkEnd;
import com.uml.contradiction.model.object.OObject;
import com.uml.contradiction.model.object.ObjectDiagram;
import com.uml.contradiction.model.object.ObjectGraph;

public class ObjectParser extends CoreParserImpl implements CoreParser {
	private static final Logger logger = Logger.getLogger(ObjectParser.class);

	private Map<String, OObject> objectsWithId = new LinkedHashMap<String, OObject>();
	private Map<String, Link> linksWithId = new LinkedHashMap<String, Link>();

	public Map<String, OObject> getObjectsWithId() {
		return objectsWithId;
	}

	public Map<String, Link> getLinksWithId() {
		return linksWithId;
	}

	@Override
	public void makeResult() {
		List<OObject> object_s = ObjectGraph.getObjects();
		List<Link> link_s = ObjectGraph.getLinks();

		Collection<OObject> collectObs = objectsWithId.values();
		for (OObject obj : collectObs) {
			object_s.add(obj);
		}

		Collection<Link> collectLnks = linksWithId.values();
		for (Link lnk : collectLnks) {
			link_s.add(lnk);
		}
	}

	@Override
	public List<Object> parse(Element umlModelEl) {

		makeObjects(umlModelEl);

		makeLinks(umlModelEl);

		makeObjectDiagram(umlModelEl);

		return null;
	}

	private void makeObjects(Element umlModelEl) {
		NodeList packNodes = umlModelEl.getElementsByTagName("packagedElement");

		for (int i = 0; i < packNodes.getLength(); i++) {
			Element packObjElem = (Element) packNodes.item(i);

			// ������������� packagedElement ���
			// ��������
			if (packObjElem.getAttribute("xmi:type").equals(
					"uml:InstanceSpecification")) {
				NodeList listClassifiers = packObjElem
						.getElementsByTagName("classifier");
				NodeList listExtensions = packObjElem
						.getElementsByTagName("xmi:Extension");
				String typeMod = null;
				if (listExtensions.getLength() != 0) {
					typeMod = getAttrByNameAndTag(
							(Element) listExtensions.item(0), "modelType",
							"value");
				}
				if (typeMod == null) {
					typeMod = "";
				}

				if (!typeMod.equals("Link")) { // �������������
												// ������
												// ������
					OObject obj = new OObject();
					String idObj = packObjElem.getAttribute("xmi:id");
					String nameObj = packObjElem.getAttribute("name");
					if (nameObj.equals("")) {
						nameObj = null;
					}
					obj.setName(nameObj);

					if (listClassifiers.getLength() != 0) {

						// ������ �� �����
						NodeList classList = packObjElem
								.getElementsByTagName("classifier");
						for (int k = 0; k < classList.getLength(); k++) {
							Element classElem = (Element) classList.item(k);
							if (classElem.getParentNode() == packObjElem) {

								String refOnClass = classElem
										.getAttribute("href");
								if (refOnClass.equals("")) {
									logger.error("For object no reference on class");
								} else {
									String refIdClass = refOnClass.substring(1);

									CClass referClass = ParsersTool
											.getInstanceClassParser()
											.getClassesWithId().get(refIdClass);
									if (referClass == null) {
										logger.error("The object has reference on class, but no such class");
									} else {
										obj.setClasses(referClass);
									}
								}
							}

							// ������ �� �������
							NodeList attrList = packObjElem
									.getElementsByTagName("slot");
							for (int j = 0; j < attrList.getLength(); j++) {
								Element attrElem = (Element) attrList.item(j);
								if (attrElem.getParentNode() == packObjElem
										&& attrElem.getAttribute("xmi:type")
												.equals("uml:Slot")) {

									String idAttrInClass = attrElem
											.getAttribute("definingFeature");
									Attribute clAttr = ParsersTool
											.getInstanceClassParser()
											.getAttributesWithId()
											.get(idAttrInClass);
									if (clAttr == null) {
										logger.error("For object no reference on class attribute");
									} else {
										AttributeObj attrObj = new AttributeObj();
										attrObj.setRefClassAttr(clAttr);
										attrObj.setName(clAttr.getName());

										String value = getAttrByNameAndTag(
												attrElem, "value", "body");
										if (value != null) {
											attrObj.setValue(value);
										}

										List<AttributeObj> attrsOfObj = obj
												.getAttributes();

										if (attrsOfObj == null) {
											attrsOfObj = new LinkedList<AttributeObj>();
											obj.setAttributes(attrsOfObj);
										}
										attrsOfObj.add(attrObj);
									}
								}
							}
						}

						objectsWithId.put(idObj, obj);
					}
				}
			}
		}
	}

	private void makeLinks(Element umlModelEl) {
		NodeList packNodes = umlModelEl.getElementsByTagName("packagedElement");

		for (int i = 0; i < packNodes.getLength(); i++) {
			Element packObjElem = (Element) packNodes.item(i);

			// ������������� packagedElement ���
			// ��������
			if (packObjElem.getAttribute("xmi:type").equals(
					"uml:InstanceSpecification")) {
				NodeList listExtensions = packObjElem
						.getElementsByTagName("xmi:Extension");
				Element elemExtens = (Element) listExtensions.item(0);
				String typeMod = null;
				if (listExtensions.getLength() != 0) {
					typeMod = getAttrByNameAndTag(elemExtens, "modelType",
							"value");
				}
				if (typeMod == null) {
					typeMod = "";
				}

				if (typeMod.equals("Link")) {
					// packagedElement - �����
					// ��������
					String idLink = packObjElem.getAttribute("xmi:id");
					String nameLnk = packObjElem.getAttribute("name");
					if (nameLnk.equals("")) {
						nameLnk = null;
					}
					String idRefCls1 = getAttrByNameAndTag(elemExtens, "from",
							"idref");
					String idRefCls2 = getAttrByNameAndTag(elemExtens, "to",
							"idref");

					OObject obj1 = objectsWithId.get(idRefCls1);
					OObject obj2 = objectsWithId.get(idRefCls2);

					if (obj1 == null || obj2 == null) {
						logger.error("We have link with end on no object (obj = null)");
					}

					Link link = new Link(new LinkEnd(obj1), new LinkEnd(obj2),
							nameLnk);
					linksWithId.put(idLink, link);
				}
			}
		}
	}

	// ��������� �������� � ��������� ��������
	private void makeObjectDiagram(Element umlModelEl) {
		NodeList diagramAll = umlModelEl.getElementsByTagName("uml:Diagram");
		List<ObjectDiagram> objDiagrams = ObjectGraph.getObjectDiagrams();
		String diagrType = "ObjectDiagram";

		for (int temp = 0; temp < diagramAll.getLength(); temp++) {
			Element curDiagr = (Element) diagramAll.item(temp);
			@SuppressWarnings("unused")
			String diagrId = curDiagr.getAttribute("xmi:id");

			// ������ �� ���� ��������� ��������
			if (curDiagr.getAttribute("diagramType").equals(diagrType)) {
				ObjectDiagram curObjDiagr = new ObjectDiagram();
				curObjDiagr.setName(curDiagr.getAttribute("name"));

				NodeList mainElementOfDiagr = curDiagr
						.getElementsByTagName("uml:Diagram.element");
				NodeList listElementsOfDiagr = ((Element) mainElementOfDiagr
						.item(0)).getElementsByTagName("uml:DiagramElement");

				for (int i = 0; i < listElementsOfDiagr.getLength(); i++) {
					Element curElem = (Element) listElementsOfDiagr.item(i);
					String refOnElemDiagr = curElem.getAttribute("subject");
					String typeElem = curElem
							.getAttribute("preferredShapeType");

					// ���� ������� - ������
					if (typeElem.equals("InstanceSpecification")) {
						OObject objectCur = objectsWithId.get(refOnElemDiagr);

						if (objectCur != null) {
							List<OObject> objects = curObjDiagr.getObjects();

							if (objects == null) {
								objects = new LinkedList<OObject>();
								curObjDiagr.setObjects(objects);
							}
							objects.add(objectCur);
						}
					}
					// ���� ������� - �����
					if (typeElem.equals("Link")) {
						Link linkCur = linksWithId.get(refOnElemDiagr);

						if (linkCur != null) {
							List<Link> links = curObjDiagr.getLinks();

							if (links == null) {
								links = new LinkedList<Link>();
								curObjDiagr.setLinks(links);
							}
							links.add(linkCur);
						}
					}
				}
				objDiagrams.add(curObjDiagr);
			}
		}
	}

}
