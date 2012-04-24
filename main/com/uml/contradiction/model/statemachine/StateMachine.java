package com.uml.contradiction.model.statemachine;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.sequence.Message;

public class StateMachine {
	private List<State> states;
	private String name;
	private CClass cClass;
	private List<Transition> transitions;
	
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
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
	public List<Transition> getTransitions() {
		return transitions;
	}
	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
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
	@Override
	public String toString() {
		String s = new String("\n\t, states=");
		if(states != null){
			for(State st : states)
				s += "\n\t    " + st + ", ";
		}
		
		s += "\n\t, transitions=";
		if(states != null){
			for(Transition tr : transitions)
				s += "\n\t    " + tr + ", ";
		}
		
		return "StateMachine [name=" + name  + ", cClass=" + cClass.getFullName()
				 + s + "]";
	}
}
