package com.uml.contradiction.converter.core;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.*;

public class ClassParser implements CoreParser {
	private static final Logger LOGGER = Logger.getRootLogger();
	
	// перенести в конвертор
			//возврат родителя смысловой части
	private Node startParse (String filename) {		
		NodeList nList;		
		try{
			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			LOGGER.debug("Tree from xmi has been builded");
				
			nList = doc.getElementsByTagName("uml:Model");
			return (Node)nList.item(0);			
			}
			catch (Exception e) {
				e.printStackTrace();
			  }
		return null;
		}

	@Override
	public List<Object> parse(String filename) {

		LinkedHashMap<String, ArrayList<String>> clas_s;
		ArrayList<String> info_cls;
		try{
		
		Element umlModelEl = (Element)startParse(filename); //начало разбора
			
		NodeList pack_nodes = umlModelEl.getElementsByTagName("packagedElement");
//		System.out.println(pack_nodes.item(0).getLastChild().getNodeName());
		
		for (int temp = 0; temp < pack_nodes.getLength(); temp++) {
												//разбор одного класса
			Element curPackEl = (Element)pack_nodes.item(temp);
			
			 clas_s = new LinkedHashMap<String, ArrayList<String>>();
			 info_cls = new ArrayList<String>();
			 
			System.out.println(curPackEl.getAttribute("name"));
			
			info_cls.add(curPackEl.getAttribute("visibility"));
			
			info_cls=null;
			 clas_s.put(curPackEl.getAttribute("xmi:id"), info_cls);
			
			 info_cls = clas_s.get(curPackEl.getAttribute("xmi:id"));
//			 System.out.println(info_cls.get(0));
											
						//разбор атрибутов
			curPackEl.getElementsByTagName("ownedAttribute");
			
		}
		}catch (Exception e) {
			e.printStackTrace();
		  }

		return null;
	}

}
