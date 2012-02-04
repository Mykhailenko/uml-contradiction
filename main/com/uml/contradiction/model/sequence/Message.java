package com.uml.contradiction.model.sequence;
public class Message implements InteractionElement {

	private MessageSort messageSort;
	private MessageKind messageKind;
	private Event sendEvent;
	private Event recieveEvent;
	public MessageSort getMessageSort() {
		return messageSort;
	}
	public void setMessageSort(MessageSort messageSort) {
		this.messageSort = messageSort;
	}
	public MessageKind getMessageKind() {
		return messageKind;
	}
	public void setMessageKind(MessageKind messageKind) {
		this.messageKind = messageKind;
	}
	public Event getSendEvent() {
		return sendEvent;
	}
	public void setSendEvent(Event sendEvent) {
		this.sendEvent = sendEvent;
	}
	public Event getRecieveEvent() {
		return recieveEvent;
	}
	public void setRecieveEvent(Event recieveEvent) {
		this.recieveEvent = recieveEvent;
	}
	

}