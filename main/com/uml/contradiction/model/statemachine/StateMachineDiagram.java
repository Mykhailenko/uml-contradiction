package com.uml.contradiction.model.statemachine;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.cclass.CClass;


public class StateMachineDiagram {
	private static List<StateMachine> stateMachines = new LinkedList<StateMachine>();
	
	public static StateMachine findStateMachineByClassName(CClass cСlass){
		for(StateMachine stateMachine : stateMachines){
			if(stateMachine.getClassName().equals(cСlass.getFullName())){
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
