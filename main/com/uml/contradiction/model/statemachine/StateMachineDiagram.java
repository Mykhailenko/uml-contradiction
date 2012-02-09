package com.uml.contradiction.model.statemachine;

import java.util.LinkedList;
import java.util.List;


public class StateMachineDiagram {
	private static List<StateMachine> stateMachines = new LinkedList<StateMachine>();
	
	public static StateMachine findStateMachineByClassName(String className){
		for(StateMachine stateMachine : stateMachines){
			if(stateMachine.getClassName().equals(className)){
				return stateMachine;
			}
		}
		return null;
	}
	
	public static List<StateMachine> getStateMachines() {
		return stateMachines;
	}
	public static void setStateMachines(List<StateMachine> stateMachines) {
		StateMachineDiagram.stateMachines = stateMachines;
	}
}
