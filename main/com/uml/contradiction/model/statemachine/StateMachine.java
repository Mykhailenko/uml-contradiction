package com.uml.contradiction.model.statemachine;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.sequence.Message;

public class StateMachine {
	private List<Pseudostate> connectionPoint;
	private List<State> submachineState;
	private String name;
	private CClass cClass;
	private List<Transition> transitions;
	public List<Transition> getTransitions() {
		return transitions;
	}
	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}
	public List<Pseudostate> getConnectionPoint() {
		return connectionPoint;
	}
	public void setConnectionPoint(List<Pseudostate> connectionPoint) {
		this.connectionPoint = connectionPoint;
	}
	public List<State> getSubmachineState() {
		return submachineState;
	}
	public void setSubmachineState(List<State> submachineState) {
		this.submachineState = submachineState;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CClass getcClass() {
		return cClass;
	}
	public void setcClass(CClass cClass) {
		this.cClass = cClass;
	}
	public static List<Message> stayOnlyImportentMessages(StateMachine stateMachine, 
			List<Message> messages){
		List<Message> result = new LinkedList<Message>();
		for(Message message : messages){
			for(Transition transition : stateMachine.getTransitions()){
				for(Trigger trigger : transition.getTriggers()){
					if(messageBelongToTrigger(message, trigger)){
						result.add(message);
					}
				}
			}
		}
		return result;
	}
	private static boolean messageBelongToTrigger(Message message, Trigger trigger){
		if(message.getMethodName().equals(trigger.getMethodName()) &&
				message.getParamCount() == trigger.getParamCount()){
			return true;
		}else{
			return false;
		}
	}
}
