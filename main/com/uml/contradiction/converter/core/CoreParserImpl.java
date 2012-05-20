package com.uml.contradiction.converter.core;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.gui.models.DiagramForChoise;

public class CoreParserImpl implements CoreParser {

	protected List<String> idElementsInDiagramm;

	protected List<String> idDiagrams;

	@Override
	public List<Object> parse(Element umlModelEl) {
		return null;
	}

	@Override
	public void makeResult() {
	}
//лоролорлор
	protected List<String> getAllIdDiagrams(DiagramType t, Element umlModelEl) {
		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ С‹РІС„С‹С‰Р›Р’Р¤Р«Р—
		NodeList diagramAll = umlModelEl.getElementsByTagName("uml:Diagram");

		String diagrType = null;
		List<String> listId = new ArrayList<String>();

		if (t == DiagramType.CLASS) {
			diagrType = "ClassDiagram";
		}

		if (t == DiagramType.SEQUENCE) {
			diagrType = "InteractionDiagram";
		}

		if (t == DiagramType.STATE_MACHINE) {
			diagrType = "StateDiagram";
		}

		if (t == DiagramType.OBJECT) {
			diagrType = "ObjectDiagram";
		}

		for (int temp = 0; temp < diagramAll.getLength(); temp++) {
			Element curDiagr = (Element) diagramAll.item(temp);

			if (curDiagr.getAttribute("diagramType").equals(diagrType)) {
				listId.add(curDiagr.getAttribute("xmi:id"));
			}
		}
		idDiagrams = listId;
		return listId;
	}

	protected boolean isIdInDiagrams(String searchId) {
		boolean pres = false;

		for (String curID : idDiagrams) {
			if (curID.equals(searchId)) {
				pres = true;
				break;
			}
		}
		return pres;
	}

	// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ Id пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
	protected List<String> getIdElementsInDiagramm(
			DiagramForChoise diagrForSearch, Element umlModelEl) {
		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
		NodeList diagramAll = umlModelEl.getElementsByTagName("uml:Diagram");

		String diagrType = null;
		List<String> listId = new ArrayList<String>();
		int flag = 0;

		if (diagrForSearch.getType() == DiagramType.CLASS) {
			diagrType = "ClassDiagram";
		}

		for (int temp = 0; temp < diagramAll.getLength(); temp++) {
			Element curDiagr = (Element) diagramAll.item(temp);

			if (curDiagr.getAttribute("name").equals(diagrForSearch.getName())
					&& curDiagr.getAttribute("diagramType").equals(diagrType)) {
				NodeList mainElementOfDiagr = curDiagr
						.getElementsByTagName("uml:Diagram.element");
				NodeList listElementsOfDiagr = ((Element) mainElementOfDiagr
						.item(0)).getElementsByTagName("uml:DiagramElement");

				for (int i = 0; i < listElementsOfDiagr.getLength(); i++) {
					Element curElem = (Element) listElementsOfDiagr.item(i);
					listId.add(curElem.getAttribute("subject"));
				}
				flag++;
			}
		}
		if (flag == 0) {
			return null;
		} else {
			return listId;
		}
	}

	// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
	protected boolean isElementInDiagrammByID(String idElem) {
		boolean present = false;

		for (String curID : idElementsInDiagramm) {
			if (curID.equals(idElem)) {
				present = true;
				break;
			}
		}
		return present;
	}

	// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ
	public String getAttrByNameAndTag(Element elem, String tagName,
			String attrName) {
		NodeList tagsByName = elem.getElementsByTagName(tagName);

		Element curTag = (Element) tagsByName.item(0);

		if (curTag == null || (curTag.getParentNode() != elem)) {
			return null;
		}

		if (curTag.hasAttribute(attrName)) {
			return curTag.getAttribute(attrName);
		} else {
			return null;
		}
	}
}
