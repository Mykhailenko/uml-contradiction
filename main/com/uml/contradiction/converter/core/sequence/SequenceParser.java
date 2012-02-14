package com.uml.contradiction.converter.core.sequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassDiagram;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.common.*;
import com.uml.contradiction.converter.core.*;
import com.uml.contradiction.model.sequence.*;

import org.apache.log4j.Logger;
import org.w3c.dom.*;

public class SequenceParser 
extends CoreParserImpl implements CoreParser{
	private static final Logger LOGGER = Logger.getRootLogger();
	
	private Map<String, Interaction> interactionsFrWithId = new LinkedHashMap <String, Interaction>();
	private Map<String, Interaction> interactsSequenceWithId = new LinkedHashMap <String, Interaction>();
	private Map<String, LifeLine> lifelinesWithId = new LinkedHashMap <String, LifeLine>();
	private Map<String, Event> eventsWithId = new LinkedHashMap <String, Event>();
	
	
	SequenceParsHelper sequenceParsHelper;  //�������� ���������
	
	public SequenceParser() {
		super();
		
		sequenceParsHelper = new SequenceParsHelper(interactionsFrWithId, lifelinesWithId, eventsWithId);
	}
	
	@Override
	public List<Object> parse(Element umlModelEl) {
		try {
			NodeList sequencesFrame = umlModelEl.getElementsByTagName("ownedBehavior");
			int temp;
							//������� ��� sequence, ���� ��������� ������ � ��������� �������
										//� ������� ���������
			for (temp = 0; temp < sequencesFrame.getLength(); temp++) {
												//������ ������ ��������
				Element curSequenceEl = (Element)sequencesFrame.item(temp);
									
				Interaction oneOfmainInter = parseFrame(curSequenceEl);
			}	
		
		//������������� ����� ��� ��������� ����������� �������� �������������������
//		if(curPackEl.getAttribute("xmi:type").equals("uml:Collaboration")){
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//���������� ������������ Sequence ���������- ��� ownedBehavior ��� frame
	public Interaction parseFrame(Element frameEl){
		Interaction curInteraction = null;
		String idFrameEl;
		
		try {	//�������� ��� ��������� sequence �������
			if(frameEl.getAttribute("xmi:type").equals("uml:Interaction")){
				curInteraction = new Interaction();
				
				idFrameEl = frameEl.getAttribute("xmi:id");
				
				String tagName = frameEl.getTagName();
				if(tagName.equals("ownedBehavior"))
					interactsSequenceWithId.put(idFrameEl, curInteraction);
					
				interactionsFrWithId.put(idFrameEl, curInteraction);
				curInteraction.setName(frameEl.getAttribute("name"));
				
				NodeList includedLifelines = frameEl.getElementsByTagName("lifeline");
				//�������� � ���������� ���������� �������
				for (int k = 0; k < includedLifelines.getLength(); k++) {
					Element lifelineEl = (Element)includedLifelines.item(k);
					
					//�������� ��� ����� ������������� ������ ���������������� ��������
					if(lifelineEl.getParentNode() == frameEl)
					{		//�������� ��� ����� Lifeline
						if(lifelineEl.getAttribute("xmi:type").equals("uml:Lifeline"))
						{
							//�������� LifeLine
							LifeLine curLifeLine = sequenceParsHelper.parseLifeline(lifelineEl);
							lifelinesWithId.put(lifelineEl.getAttribute("xmi:id"), curLifeLine);
							
							List<LifeLine> lifnes = curInteraction.getLifeLines();							
							if(lifnes == null){
								lifnes = new LinkedList<LifeLine>();
								curInteraction.setLifeLines(lifnes);
							}
							lifnes.add(curLifeLine); //���������� ������ ����� �����
						}
					}
				}
				
				NodeList events = frameEl.getElementsByTagName("fragment");
				//�������� � ���������� ����������� Events
				for (int k = 0; k < events.getLength(); k++) {
					Element eventEl = (Element)events.item(k);
					
					//�������� ��� ����� ������������� ������ ���������������� ��������
					if(eventEl.getParentNode() == frameEl)
					{		
						//�������� Event
						Event curEvent = sequenceParsHelper.parseEvent(eventEl);
						eventsWithId.put(eventEl.getAttribute("xmi:id"), curEvent);					
					}
				}
				
				//����� ������������������ 
				//������� ���� �� ���� ��������� ��������� ������
				// � �������� � �������� ��� � �����������
				NodeList childsOfFrameEl = frameEl.getChildNodes();
				
				for (int i = 0; i < childsOfFrameEl.getLength(); i++) {
					
					if(childsOfFrameEl.item(i).getNodeType() == Node.ELEMENT_NODE){
						Element includeInterInFrame = (Element)childsOfFrameEl.item(i);
					
						//�������� ��� ����� ������������� ������ ���������������� ��������
						if(includeInterInFrame.getParentNode() == frameEl){
							
							//�������� � ���������� ��������
							if(includeInterInFrame.getTagName().equals("frame")){					
								List<InteractionElement> childs = curInteraction.getChilds();
								
								if(childs == null){
									childs = new LinkedList<InteractionElement>();
									curInteraction.setChilds(childs);
								}
								childs.add(parseFrame(includeInterInFrame));
							}else{
								//�������� � ����������
								if(includeInterInFrame.getTagName().equals("message")){					
									List<InteractionElement> childs = curInteraction.getChilds();
									
									if(childs == null){
										childs = new LinkedList<InteractionElement>();
										curInteraction.setChilds(childs);
									}
									Message mess = sequenceParsHelper.parseMessage(includeInterInFrame);
									childs.add(mess);
								}
							}
							
						}					
					}				
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return curInteraction;
	}

	@Override
	public void makeResult() {
		Collection<Interaction> allFrames = interactionsFrWithId.values();
		Collection<Interaction> sequences = interactsSequenceWithId.values();
		
		List<Interaction> interList = SequenceGraph.getInteractions();
						
		for(Interaction intr : allFrames)			
			interList.add(intr);
		
//		System.out.println("All frames");
//		for(Interaction int1 : allFrames)
//			System.out.println(int1);
//		
//		System.out.println("\n Sequences frames");
//		for(Interaction int2 : sequences)
//			System.out.println(int2);
		
//		Collection<Event> cv= eventsWithId.values();
//		for(Event ev : cv)
//			System.out.println(ev);
	}
	
}
