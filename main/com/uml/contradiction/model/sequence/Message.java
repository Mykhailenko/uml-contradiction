package com.uml.contradiction.model.sequence;
public class Message extends InteractionElement {

	private MessageSort messageSort;
	private MessageKind messageKind;
	private Event sendEvent;
	private Event recieveEvent;
	private String methodName;
	private int paramCount;
	public void parseStr(String str){
//		System.out.println("There are " + str);
		// i suppose to get some like that 'ololoName(param1,param2)' or 'disable()' or 'enable' 
		if(str.contains("(")){ // first or second case
			methodName = str.substring(0, str.indexOf("(")).trim();
			String params = str.substring(str.indexOf("("), str.indexOf(")"));
			params = params.replaceAll(" ", "");
			if(params.length() > 2){//first case
				paramCount = params.split(",").length;
			}else{
				paramCount = 1;
			}
		}else{
			methodName = str;
			paramCount = 0;
		}
	}

	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public int getParamCount() {
		return paramCount;
	}
	public void setParamCount(int paramCount) {
		this.paramCount = paramCount;
	}
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

	@Override
	public String toString() {
		return "Message [messageSort=" + messageSort + ", messageKind="
				+ messageKind + ", sendEvent=" + sendEvent + ", recieveEvent="
				+ recieveEvent + ", methodName=" + methodName + ", paramCount="
				+ paramCount + "]";
	}
	@Override
	public Type getType() {
		return Type.MESSAGE;
	}
	
}