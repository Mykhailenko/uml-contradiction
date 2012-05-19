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

import com.uml.contradiction.converter.core.CoreParserImpl;
import com.uml.contradiction.converter.core.ParsersTool;
import com.uml.contradiction.converter.core.classes.ClassParser;
import com.uml.contradiction.converter.core.object.ObjectParser;
import com.uml.contradiction.converter.core.sequence.SequenceParser;
import com.uml.contradiction.converter.core.statemachine.StatemachineParser;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.model.MetaData;

public class XMIConverter {
	private static File file = null;

	public static boolean setFileAndParse(File file) {
		reset();
		setFile(file);
		try {
			parse();
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void setFile(File file) {
		assert file != null;
		XMIConverter.file = file;
	}

	private static final Logger LOGGER = Logger.getRootLogger();

	// пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
	// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ
	private static Node startParse(File fXmlFile) {
		NodeList nList;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			LOGGER.debug("Tree from xmi has been builded");

			nList = doc.getElementsByTagName("uml:Model");
			return nList.item(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<DiagramForChoise> getAvailableDiagram() throws Exception {
		assert XMIConverter.file != null : "file should be assigned before";
		return Collections.emptyList();
	}

	public static List<Object> parse() throws Exception {
		assert XMIConverter.file != null : "file should be assigned before";

		Element umlModelEl = (Element) startParse(file); // пїЅпїЅпїЅпїЅпїЅпїЅ
															// пїЅпїЅпїЅпїЅпїЅпїЅпїЅ

		// метаданные
		String name, value;
		MetaData.setProjectName(umlModelEl.getAttribute("name"));
		CoreParserImpl corePars = new CoreParserImpl();
		MetaData.setExporter(corePars.getAttrByNameAndTag(umlModelEl,
				"xmi:Documentation", "xmi:Exporter"));
		NodeList listExtens = umlModelEl.getElementsByTagName("xmi:Extension");
		if(listExtens.getLength() > 0){
			Element extens = (Element) listExtens.item(0);
			NodeList propertList = extens.getElementsByTagName("projectProperties");
			//разбор автора, компании, описания 
			if(propertList.getLength()>0){
				Element projProp = (Element) propertList.item(0);
				NodeList tagsByName = projProp.getElementsByTagName("projectProperty");				
				for (int i = 0; i < tagsByName.getLength(); i++) {
					Element curTag = (Element) tagsByName.item(i);
					if (curTag != null && (curTag.getParentNode() == projProp)) {
						name = curTag.getAttribute("name");
						value = curTag.getAttribute("value");
						if (!value.equals("")) {
							if (name.equals("company")) {
								MetaData.setCompany(value);
							}
							if (name.equals("author")) {
								MetaData.setAuthor(value);
							}
							if (name.equals("description")) {
								MetaData.setDescription(value);
							}
						}
					}
				}
			}
			NodeList listVPmodels = extens.getElementsByTagName("vpumlChildModels");
			if(listVPmodels.getLength()>0){
				Element childModels = (Element) listVPmodels.item(0);
				Element propEl = (Element) ((Element) (childModels
						.getElementsByTagName("vpumlModel").item(0)))
						.getElementsByTagName("properties").item(0);
				NodeList tagsByN = propEl.getElementsByTagName("property");
				for (int i = 0; i < tagsByN.getLength(); i++) {
					Element curTag = (Element) tagsByN.item(i);
					if (curTag != null && (curTag.getParentNode() == propEl)) {
						name = curTag.getAttribute("name");
						value = curTag.getAttribute("value");
						if (!value.equals("")) {
							if (name.equals("pmCreateDateTime")) {
								MetaData.setPmCreateDateTime(value);
							}
							if (name.equals("pmLastModified")) {
								MetaData.setPmLastModified(value);
							}
						}
					}
				}
			}
		}

		// распарсивание по типам диаграмм
		ClassParser clPars = ParsersTool.getInstanceClassParser();
		clPars.parse(umlModelEl);
		clPars.makeResult();

		ObjectParser objPars = ParsersTool.getInstanceObjectParser();
		objPars.parse(umlModelEl);
		objPars.makeResult();

		SequenceParser seqPars = ParsersTool.getInstanceSequenceParser();
		seqPars.parse(umlModelEl);
		seqPars.makeResult();

		StatemachineParser stMPars = ParsersTool
				.getInstanceStatemachineParser();
		stMPars.parse(umlModelEl);
		stMPars.makeResult();

		return Collections.emptyList();
	}

	public static void reset() {
		XMIConverter.file = null;
	}
}
