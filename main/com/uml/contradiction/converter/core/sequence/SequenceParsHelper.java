package com.uml.contradiction.converter.core.sequence;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.converter.core.classes.CommonClDiagrHelper;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.common.*;
import com.uml.contradiction.model.sequence.*;

public class SequenceParsHelper {
	private static final Logger logger = Logger.getLogger(SequenceParsHelper.class);
	
	private Map<String, Interaction> interactionsFrWithId;
	private Map<String, LifeLine> lifelinesWithId;
	private Map<String, Event> eventsWithId;
	private Map<String, Message> messagesWithId;
	
	public SequenceParsHelper(Map<String, Interaction> interactionsFrWithId,
			Map<String, LifeLine> lifelinesWithId,
			Map<String, Event> eventsWithId,
			Map<String, Message> messagesWithId) {
		super();
		this.interactionsFrWithId = interactionsFrWithId;
		this.lifelinesWithId = lifelinesWithId;
		this.eventsWithId = eventsWithId;
		this.messagesWithId = messagesWithId;
	}
	
	public LifeLine parseLifeline(Element lifeLine) {
		LifeLine lifeln = new LifeLine();
		CClass includedClass;
		lifeln.setName(lifeLine.getAttribute("name"));
		
		//lifeln.setCclass(includedClass);		
		return lifeln;
	}
	
	public void parseAllLifelines(Element frameEl) {
		NodeList includedLifelines = frameEl.getElementsByTagName("lifeline");
		//работаем с вложенными жизненными лини€ми
		for (int k = 0; k < includedLifelines.getLength(); k++) {
			Element lifelineEl = (Element)includedLifelines.item(k);
			
				//проверка что точно Lifeline
			if(lifelineEl.getAttribute("xmi:type").equals("uml:Lifeline"))
			{
				//получаем LifeLine
				LifeLine curLifeLine = parseLifeline(lifelineEl);
				lifelinesWithId.put(lifelineEl.getAttribute("xmi:id"), curLifeLine);				
			}			
		}
	}
	
	public Event parseEvent(Element eventEL) {
		Event curEvent  = new Event();
		String covd = eventEL.getAttribute("covered");
		curEvent.setCovered(lifelinesWithId.get(covd));
		
		return curEvent;
	}
	public void parseAllInlaidEvents(Element frameEl) {
		
		NodeList events = frameEl.getElementsByTagName("fragment");
		//работаем с вложенными фрагментами Events
		for (int k = 0; k < events.getLength(); k++) {
			Element eventEl = (Element)events.item(k);
							
			//получаем Event
			Event curEvent  = new Event();
			String covd = eventEl.getAttribute("covered");
			curEvent.setCovered(lifelinesWithId.get(covd));
			
			eventsWithId.put(eventEl.getAttribute("xmi:id"), curEvent);			
		}
	}
	
	public Message parseMessage(Element messElem) {
		Message curMess = new Message();
		
		String messageValue = messElem.getAttribute("name");
		curMess.parseStr(messageValue);
		
		//получили Id получающего Event
		String resEvId = messElem.getAttribute("receiveEvent");
		Event resEvent = eventsWithId.get(resEvId);
		
		if(resEvent != null)
			resEvent.setMessage(curMess);		//” Event сослались на сообщение
		else
			logger.warn("recieve Event was not find before message");
		curMess.setRecieveEvent(resEvent);	///¬ сообщение сослались на пол Event
		
		//получили Id отправл€ющего Event
		String sendEvId = messElem.getAttribute("sendEvent");
		Event sendEvent = eventsWithId.get(sendEvId);
		
		if(sendEvent != null)
			sendEvent.setMessage(curMess);	
		else
			logger.warn("send Event was not find before message");	
		curMess.setSendEvent(sendEvent);
		
		String messSort = messElem.getAttribute("messageSort");
		if(messSort.equals("synchCall"))  
			curMess.setMessageSort(MessageSort.SYNCH_CALL);
		
		
		return curMess;
	}
	public boolean checkedConnections(){
		boolean flag = true;
		Collection<Event> allEvs = eventsWithId.values();
		Collection<Message> allMes = messagesWithId.values();
		
		for(Event eva : allEvs){
			if(eva.getCovered() == null){
				logger.error("not all events has covered lifeline");
				flag = false;
			}
			if(eva.getMessage() == null){
				logger.error("not all events has messages");
				flag = false;
			}
		}
		for(Message mes : allMes){
			if(mes.getRecieveEvent() == null){
				logger.error("not all messages has RecieveEvent");
				flag = false;
			}
			if(mes.getSendEvent() == null){
				logger.error("not all events has SendEvent");
				flag = false;
			}
		}
		
		return flag;
	}
}
