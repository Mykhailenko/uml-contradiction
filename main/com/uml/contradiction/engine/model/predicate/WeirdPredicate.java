package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.sequence.InteractionElement;
import com.uml.contradiction.model.sequence.Message;
import com.uml.contradiction.model.statemachine.State;

public class WeirdPredicate implements Predicate {
	private  static final Logger LOGGER = Logger.getRootLogger();

	/**
	 * There are 2 input parameters in the list: - state (State) that contains
	 * entry trigger and belong to class C; - message (Message) that targeting
	 * to object of class C Predicate return true if entry-message appear
	 * already before message in that sequence diagram where message appear
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public boolean predict(List params) throws PredicatException {
		precondition(params);
		State state = (State) params.get(0);
		Message message = (Message) params.get(1);
		CClass stateClass = state.getStateMachine().getcClass();
		int index = message.getInteraction().getChilds().indexOf(message);
		for (int i = index - 1; i >= 0; --i) {
			InteractionElement ie = message.getInteraction().getChilds().get(i);
			if (ie.isMessage()) {
				Message message1 = (Message) ie;
				CClass cls = message1.getTarget().getcClass();
				if (cls.equals(stateClass)) {
					if (message1.compareWithTransition(state.getEntry())) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	private void precondition(List params) throws PredicatException {
		assert params != null;
		assert params.size() == 2;
		Object first = params.get(0);
		if ((first instanceof State) == false) {
			LOGGER.error("Unexpected type: " + first.getClass().toString());
			throw new PredicatException("Unexpected type: "
					+ first.getClass().toString());
		}
		Object second = params.get(1);
		if ((second instanceof Message) == false) {
			LOGGER.error("Unexpected type: " + second.getClass().toString());
			throw new PredicatException("Unexpected type: "
					+ second.getClass().toString());
		}
	}

	@Override
	public String getFailDescription() {
		return null;
	}

}
