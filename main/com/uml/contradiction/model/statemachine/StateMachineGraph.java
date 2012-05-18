package com.uml.contradiction.model.statemachine;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.cclass.CClass;

public class StateMachineGraph {
	private static List<StateMachine> stateMachines = new LinkedList<StateMachine>();

	public static StateMachine findStateMachineByClassName(CClass cClass) {
		for (StateMachine stateMachine : stateMachines) {
			if (stateMachine.getcClass().equals(cClass)) {
				return stateMachine;
			}
		}
		return null;
	}

	public static List<StateMachine> getStateMachines() {
		return stateMachines;
	}

	public static void setStateMachines(List<StateMachine> stateMachines) {
		StateMachineGraph.stateMachines = stateMachines;
	}

	public static void clear() {
		stateMachines.clear();
	}
}
