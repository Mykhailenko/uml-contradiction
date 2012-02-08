package com.uml.contradiction.converter.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.gui.models.DiagramForChoise;

public class CoreParserImpl implements CoreParser{
	
	protected List<String> IdElementsInDIagramm;

	public List<Object> parse(DiagramForChoise diagrForSearch, Element umlModelEl){
		return null;
	}
	
	//получения списка Id элементов для выбранной диаграммы
	protected List<String> getIdElementsInDiagramm(DiagramForChoise diagrForSearch, Element umlModelEl){
					//проходим по всем диаграммам с поиском выбранной
		NodeList diagramAll = umlModelEl.getElementsByTagName("uml:Diagram");
		
		String diagrType =null;
		List<String> listId = new ArrayList<String>();
		int flag=0;
		
		if(diagrForSearch.getType() == DiagramType.CLASS)
			diagrType = new String("ClassDiagram");
		
		for(int temp = 0; temp < diagramAll.getLength(); temp++){
			Element curDiagr = (Element)diagramAll.item(temp);
			
			if(curDiagr.getAttribute("name").equals(diagrForSearch.getName())
					&& curDiagr.getAttribute("diagramType").equals(diagrType))
			{
				NodeList mainElementOfDiagr = curDiagr.getElementsByTagName("uml:Diagram.element");
				NodeList listElementsOfDiagr = 
						((Element)mainElementOfDiagr.item(0)).getElementsByTagName("uml:DiagramElement");
				
				for(int i = 0; i < listElementsOfDiagr.getLength(); i++){
					Element curElem = (Element)listElementsOfDiagr.item(i);
					listId.add(curElem.getAttribute("subject"));
				}
				flag++;
			}
		}
		if(flag == 0)  //если не нашли заданную диаграмму
			return null;
		else
			return listId;
	}
	
	//относиться ли данный элемент к диаграмме
	protected boolean isElementInDiagrammByID(String idElem){		
		boolean present = false;		
		
		for(String curID : IdElementsInDIagramm){
			if(curID.equals(idElem)){
				present =true;
				break;
			}				
		}	
		return present;
	}
}
