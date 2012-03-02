package com.uml.contradiction.converter.core.sequence;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.converter.core.CoreParserImpl;
import com.uml.contradiction.converter.core.ParsersTool;
import com.uml.contradiction.converter.core.classes.CommonClDiagrHelper;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.cclass.Parameter;
import com.uml.contradiction.model.common.*;
import com.uml.contradiction.model.sequence.*;

public class SequenceParsHelper {
	private static final Logger logger = Logger.getLogger(SequenceParsHelper.class);
	
	private Map<String, Interaction> interactionsFrWithId;
	private Map<String, LifeLine> lifelinesWithId;
	private Map<String, Event> eventsWithId;
	private Map<String, Message> messagesWithId;
	private Map<String, String> referencesOnClassId;
	
	public SequenceParsHelper(Map<String, Interaction> interactionsFrWithId,
			Map<String, LifeLine> lifelinesWithId,
			Map<String, Event> eventsWithId,
			Map<String, Message> messagesWithId) {
		super();
		this.interactionsFrWithId = interactionsFrWithId;
		this.lifelinesWithId = lifelinesWithId;
		this.eventsWithId = eventsWithId;
		this.messagesWithId = messagesWithId;
		
		referencesOnClassId = new LinkedHashMap<String, String>();
	}
	
	public LifeLine parseLifeline(Element lifeLine) {
		LifeLine lifeln = new LifeLine();
		CClass referClass;
		String lifelnName = lifeLine.getAttribute("name");
		if(!lifelnName.equals(""))
			lifeln.setName(lifelnName);
		else{
			String idOwnAttr = lifeLine.getAttribute("represents");
			String refOnClass = referencesOnClassId.get(idOwnAttr);
			if(refOnClass != null){
				referClass = ParsersTool.getInstanceClassParser().getClassesWithId().get(refOnClass);
				if(referClass != null)
					lifeln.setCclass(referClass);
				else
					logger.info("reference on unknown cclass");
			}
		}
				
		return lifeln;
	}
	
	public void parseAllLifelines(Element frameEl) {
		
		NodeList ownedAttrs = frameEl.getElementsByTagName("ownedAttribute");
		//работаем с вложенными ссылками жизненных линий на классы
		for (int k = 0; k < ownedAttrs.getLength(); k++) {
			Element ownedAttrEl = (Element)ownedAttrs.item(k);			
			if(ownedAttrEl.getParentNode() == frameEl)
			{
				String idownAtr = ownedAttrEl.getAttribute("xmi:id");
				String refOnClass = ownedAttrEl.getAttribute("type");
				if(!refOnClass.equals(""))
					referencesOnClassId.put(idownAtr, refOnClass);
			}
		}		
		NodeList includedLifelines = frameEl.getElementsByTagName("lifeline");
		//работаем с вложенными жизненными линиями
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
			resEvent.setMessage(curMess);		//У Event сослались на сообщение
		else
			logger.warn("recieve Event was not find before message");
		curMess.setRecieveEvent(resEvent);	///В сообщение сослались на пол Event
		
		//получили Id отправляющего Event
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
		
		if(messSort.equals("reply"))  
			curMess.setMessageSort(MessageSort.ASYNCH_SIGNAL);
		
		//получаем ссылку на метод из сообщения
		CoreParserImpl coreParse = new CoreParserImpl();
		Element extens = (Element)messElem.getElementsByTagName("xmi:Extension").item(0);
		String methodStr = coreParse.getAttrByNameAndTag(extens, "modelTransition", "from");
		int index;
		MMethod refMethod = null;
		if(methodStr != null){
			//for (int i = methodStr.length(); i!=0 || methodStr.in[i] != "$"; i--) {
			index = methodStr.indexOf("$");
			if(index != -1){
				String id4Meth = methodStr.substring(index+1, methodStr.length()-1);
				if(id4Meth != null){
					refMethod = ParsersTool.getInstanceClassParser().getMethodsWithId().get(id4Meth);
					if(refMethod != null){
						curMess.setMethodName(refMethod.getName());
						List<Parameter> parameters = refMethod.getParameters();
						int paramCount;
						if(parameters == null)
							paramCount = 0;
						else
							paramCount = parameters.size();
						curMess.setParamCount(paramCount);
					}
				}					
			}
		}	
		
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
