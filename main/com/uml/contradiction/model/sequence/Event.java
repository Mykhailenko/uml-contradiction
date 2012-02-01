package com.uml.contradiction.model.sequence;
public class Event {

	private Message message;
	private LifeLine covered;
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public LifeLine getCovered() {
		return covered;
	}
	public void setCovered(LifeLine covered) {
		this.covered = covered;
	}
	

}