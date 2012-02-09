package com.uml.contradiction.engine.model.predicate;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.InteractionElement;
import com.uml.contradiction.model.sequence.LifeLine;
import com.uml.contradiction.model.sequence.Message;
import com.uml.contradiction.model.statemachine.State;
import com.uml.contradiction.model.statemachine.StateMachine;
import com.uml.contradiction.model.statemachine.StateMachineDiagram;
import com.uml.contradiction.model.statemachine.Transition;
import com.uml.contradiction.model.statemachine.Trigger;

public class IsExecutableLifeLine implements Predicate {
	private static final Logger LOGGER = Logger.getRootLogger();
	private String failString = null;
	@SuppressWarnings("rawtypes")
	@Override
	public boolean predict(List params) throws PredicatException {
		precondition(params);
		Interaction interaction = (Interaction) params.get(0);
		LifeLine lifeLine = (LifeLine) params.get(1);
		List<Message> messages = findAllMessagesTargetLifeLine(interaction, lifeLine);
		StateMachine stateMachine = StateMachineDiagram.findStateMachineByClassName(lifeLine.getClassName());
		if(stateMachine == null){
			return true;// it means that inconsistence epsent tooday
		}
		return true;//traceMessagesOnClass(stateMachine, messages);
	}
	@SuppressWarnings("rawtypes")
	private void precondition(List params) throws PredicatException {
		assert params != null;
		assert params.size() == 2;
		Object first = params.get(0);
		if((first instanceof Interaction) == false){
			LOGGER.error("Unexpected type : " + first.getClass().toString());
			throw new PredicatException("Unexpected type : " + first.getClass().toString());
		}
		Object second = params.get(1);
		if((second instanceof LifeLine) == false){
			LOGGER.error("Unexpected type : " + second.getClass().toString());
			throw new PredicatException("Unexpected type : " + second.getClass().toString());
		}
		
	}
	private List<Message> findAllMessagesTargetLifeLine(Interaction interaction,
			LifeLine lifeLine){
		List<Message> messages = new LinkedList<Message>();
		for(int i = 0; i < interaction.getChilds().size(); ++i){
			InteractionElement interactionElement = interaction.getChilds().get(i);
			if(interactionElement instanceof Message){
				Message message = (Message) interactionElement;
				if(message.getRecieveEvent().getCovered().equals(lifeLine)){
					messages.add(message);
				}
			}else {
				Interaction interaction2 = (Interaction) interactionElement;
				messages.addAll(findAllMessagesTargetLifeLine(interaction2, lifeLine));
			}
		}
		return messages;
	}
	private boolean traceMessagesOnClass(StateMachine stateMachine, List<Message> messages){
		State startState = null;
		int index;
		for(index = 0; index < messages.size() && startState == null; ++index){
			startState = findStartState(stateMachine, messages.get(index));
		}
		if(startState == null){
			return true;
		}
		if(index == messages.size()){
			return true;
		}
		return traceFromState(stateMachine, startState, messages, index);
	}
	private State findStartState(StateMachine stateMachine, Message message){
		for(Transition transition : stateMachine.getTransitions()){
			for(Trigger trigger : transition.getTriggers()){
				if(trigger.getMethodName().equals(message.getMethodName())){
					return (State) transition.getTarget();
				}
			}
		}
		return null;
	}
	private boolean traceFromState(StateMachine stateMachine, 
			State currentState, 
			List<Message> messages, 
			int index){
		// тут поиск в глубину хотя бы одного действенного варианта
		List<State> nextStates = getNextStates(stateMachine, currentState, messages.get(index));
		if(nextStates.isEmpty() == true && index == messages.size()-1){
			return true;
		}
		for(State state : nextStates){
			traceFromState(stateMachine, state, messages, index+1);
		}
		return false;
	}
	private List<State> getNextStates(StateMachine stateMachine, 
			State currentState, 
			Message message){
		List<State> nextStates = new LinkedList<State>();
		for(Transition transition : stateMachine.getTransitions()){ 
			if(transition.getSource().equals(currentState)){
				for(Trigger trigger : transition.getTriggers()){
					if(trigger.getMethodName().equals(message)){
						nextStates.add((State) transition.getTarget());
					}
				}
			}
		}
		return nextStates;
	}
	@Override
	public String getFailDescription() {
		return failString;
	}
}
