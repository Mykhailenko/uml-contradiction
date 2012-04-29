package com.uml.contradiction.converter;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.model.MetaData;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.converter.core.*;
import com.uml.contradiction.converter.core.classes.ClassParser;
import com.uml.contradiction.converter.core.sequence.SequenceParser;
import com.uml.contradiction.converter.core.object.ObjectParser;
import com.uml.contradiction.converter.core.statemachine.StatemachineParser;

public class XMIConverter {
	private static File file = null;
	public static boolean setFileAndParse(File file){
		reset();
		setFile(file);
		try {
			parse();
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void setFile(File file){
		assert file != null;
		XMIConverter.file = file;
	}
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
				//пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ
	private static Node startParse (File fXmlFile) {		
		NodeList nList;		
		try{
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
	
	public static List<DiagramForChoise> getAvailableDiagram() throws Exception{
		assert XMIConverter.file != null : "file should be assigned before";
		return Collections.emptyList();
	}
	
	public static List<Object> parse() throws Exception{
		assert XMIConverter.file != null : "file should be assigned before";
		
		Element umlModelEl = (Element)startParse(file); //пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
		
		//метаданные
		MetaData.setProjectName(umlModelEl.getAttribute("name"));
		CoreParserImpl corePars = new CoreParserImpl();
		corePars.getAttrByNameAndTag(umlModelEl, "xmi:Documentation", "xmi:Exporter");
		
		//распарсивание по типам диаграмм
		ClassParser clPars = ParsersTool.getInstanceClassParser();
		clPars.parse(umlModelEl);	
		clPars.makeResult();
		
		ObjectParser objPars = ParsersTool.getInstanceObjectParser();
		objPars.parse(umlModelEl);
		objPars.makeResult();
		
		SequenceParser seqPars = ParsersTool.getInstanceSequenceParser();
		seqPars.parse(umlModelEl);		
		seqPars.makeResult();
		
		StatemachineParser stMPars = ParsersTool.getInstanceStatemachineParser();
		stMPars.parse(umlModelEl);		
		stMPars.makeResult();			
		
		return Collections.emptyList();
	}
	public static void reset(){
		XMIConverter.file = null;
	}
}
