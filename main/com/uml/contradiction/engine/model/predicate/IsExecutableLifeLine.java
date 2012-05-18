package com.uml.contradiction.engine.model.predicate;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.InteractionElement;
import com.uml.contradiction.model.sequence.LifeLine;
import com.uml.contradiction.model.sequence.Message;
import com.uml.contradiction.model.statemachine.State;
import com.uml.contradiction.model.statemachine.StateMachine;
import com.uml.contradiction.model.statemachine.StateMachineGraph;
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
		List<Message> messages = findAllMessagesTargetLifeLine(interaction,
				lifeLine);
		StateMachine stateMachine = StateMachineGraph
				.findStateMachineByClassName(lifeLine.getcClass());
		if (stateMachine == null) {
			return true;// it means that inconsistence epsent tooday
		}
		return traceMessagesOnClass(stateMachine, messages);
	}

	@SuppressWarnings("rawtypes")
	private void precondition(List params) throws PredicatException {
		assert params != null;
		assert params.size() == 2;
		Object first = params.get(0);
		if ((first instanceof Interaction) == false) {
			LOGGER.error("Unexpected type : " + first.getClass().toString());
			throw new PredicatException("Unexpected type : "
					+ first.getClass().toString());
		}
		Object second = params.get(1);
		if ((second instanceof LifeLine) == false) {
			LOGGER.error("Unexpected type : " + second.getClass().toString());
			throw new PredicatException("Unexpected type : "
					+ second.getClass().toString());
		}

	}

	public static List<Message> findAllMessagesTargetLifeLine(
			Interaction interaction, LifeLine lifeLine) {
		List<Message> messages = new LinkedList<Message>();
		for (int i = 0; i < interaction.getChilds().size(); ++i) {
			InteractionElement interactionElement = interaction.getChilds()
					.get(i);
			if (interactionElement instanceof Message) {
				Message message = (Message) interactionElement;
				if (message.getTarget().equals(lifeLine)) {
					messages.add(message);
				}
			} else {
				Interaction interaction2 = (Interaction) interactionElement;
				messages.addAll(findAllMessagesTargetLifeLine(interaction2,
						lifeLine));
			}
		}
		return messages;
	}

	private boolean traceMessagesOnClass(StateMachine stateMachine,
			List<Message> messages) {
		messages = StateMachine.stayOnlyImportentMessages(stateMachine,
				messages);
		if (messages.isEmpty()) {
			return true;
		}
		State startState = findStartState(stateMachine, messages.get(0));
		if (startState == null) {
			return false;
		}
		if (messages.size() > 1) {
			return traceFromState(stateMachine, startState, messages);
		} else {
			return true;
		}
	}

	public State findStartState(StateMachine stateMachine, Message message) {
		for (Transition transition : stateMachine.getTransitions()) {
			for (Trigger trigger : transition.getTriggers()) {
				if (trigger.getMethodName().equals(message.getMethodName())) {
					return (State) transition.getTarget();
				}
			}
		}
		return null;
	}

	private boolean traceFromState(StateMachine stateMachine,
			State currentState, List<Message> messages) {
		List<State> nextStates = getNextStates(stateMachine, currentState,
				messages.get(1));
		if (nextStates.isEmpty()) {
			return false;
		}
		for (State state : nextStates) {
			if (traceFromStateB(stateMachine, state, messages, 2)) {
				return true;
			}
		}
		return false;
	}

	private boolean traceFromStateB(StateMachine stateMachine,
			State currentState, List<Message> messages, int index) {
		if (index == messages.size()) {
			return true;
		}
		List<State> nextStates = getNextStates(stateMachine, currentState,
				messages.get(index));
		if (nextStates.isEmpty()) {
			return false;
		}
		for (State state : nextStates) {
			if (traceFromStateB(stateMachine, state, messages, index + 1)) {
				return true;
			}
		}
		return false;
	}

	private List<State> getNextStates(StateMachine stateMachine,
			State currentState, Message message) {
		List<State> nextStates = new LinkedList<State>();
		for (Transition transition : stateMachine.getTransitions()) {
			if (transition.getSource().equals(currentState)) {
				for (Trigger trigger : transition.getTriggers()) {
					if (trigger.getMethodName().equals(message)) {
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
