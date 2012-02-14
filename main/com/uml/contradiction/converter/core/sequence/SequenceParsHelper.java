package com.uml.contradiction.converter.core.sequence;

import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.Element;

import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.common.*;
import com.uml.contradiction.model.sequence.*;

public class SequenceParsHelper {
	private Map<String, Interaction> interactionsFrWithId;
	private Map<String, LifeLine> lifelinesWithId;
	private Map<String, Event> eventsWithId;
	
	public SequenceParsHelper(Map<String, Interaction> interactionsFrWithId,
			Map<String, LifeLine> lifelinesWithId,
			Map<String, Event> eventsWithId) {
		super();
		this.interactionsFrWithId = interactionsFrWithId;
		this.lifelinesWithId = lifelinesWithId;
		this.eventsWithId = eventsWithId;
	}
	
	public LifeLine parseLifeline(Element lifeLine) {
		LifeLine lifeln = new LifeLine();
		CClass includedClass;
		lifeln.setName(lifeLine.getAttribute("name"));
		
		//lifeln.setCclass(includedClass);		
		return lifeln;
	}
	
	public Event parseEvent(Element eventEL) {
		Event curEvent  = new Event();
		String covd = eventEL.getAttribute("covered");
		curEvent.setCovered(lifelinesWithId.get(covd));
		
		return curEvent;
	}
	public Message parseMessage(Element messElem) {
		Message curMess = new Message();
		
		String messageValue = messElem.getAttribute("name");
		curMess.parseStr(messageValue);
		
		//�������� Id ����������� Event
		String resEvId = messElem.getAttribute("receiveEvent");
		Event resEvent = eventsWithId.get(resEvId);
		
		resEvent.setMessage(curMess);		//� Event ��������� �� ���������
		curMess.setRecieveEvent(resEvent);	///� ��������� ��������� �� ��� Event
		
		//�������� Id ������������� Event
		String sendEvId = messElem.getAttribute("sendEvent");
		Event sendEvent = eventsWithId.get(sendEvId);
		
		sendEvent.setMessage(curMess);		
		curMess.setSendEvent(sendEvent);
		
		String messSort = messElem.getAttribute("messageSort");
		if(messSort.equals("synchCall"))  
			curMess.setMessageSort(MessageSort.SYNCH_CALL);
		
		
		return curMess;
	}
}
