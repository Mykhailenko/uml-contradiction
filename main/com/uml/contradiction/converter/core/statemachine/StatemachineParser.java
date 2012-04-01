package com.uml.contradiction.converter.core.statemachine;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.converter.core.CoreParser;
import com.uml.contradiction.converter.core.CoreParserImpl;
import com.uml.contradiction.converter.core.ParsersTool;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.statemachine.*;


public class StatemachineParser 
extends CoreParserImpl implements CoreParser{
	private static final Logger logger = Logger.getLogger(StatemachineParser.class);
	
	private Map<String, State> statesWithId = new LinkedHashMap <String, State>();
	private Map<String, Transition> transitionsWithId = new LinkedHashMap <String, Transition>();
	private Map<String, StateMachine> stMachinesWithId = new LinkedHashMap <String, StateMachine>();
	
	public void makeResult() {
		List<StateMachine> stateMach_s = StateMachineGraph.getStateMachines();
		
		Collection<StateMachine> collectstMs = stMachinesWithId.values();
		for(StateMachine stM : collectstMs)		
			stateMach_s.add(stM);
	}		

	public List<Object> parse(Element umlModelEl) {
		makeStates(umlModelEl);
		
		return null;
	}
	
	private void makeStates(Element umlModelEl){
		NodeList packNodes = umlModelEl.getElementsByTagName("packagedElement");
		StateMachine stateMach = null;		
		Map<String, CClass> classesWithId = ParsersTool.getInstanceClassParser().getClassesWithId();
		
		for (int i = 0; i < packNodes.getLength(); i++) {
			Element packElem = (Element)packNodes.item(i);
			
			//рассматриваем packagedElement для состояний в классах
			if(packElem.getAttribute("xmi:type").equals("uml:State"))
			{
				Element parentClass = (Element)packElem.getParentNode();
				String idParClass = parentClass.getAttribute("xmi:id");
				CClass class4StMach = classesWithId.get(idParClass);
				if(class4StMach != null){	//создание StateMachine с привязкой  к классам
					if(stateMach == null){
						stateMach = new StateMachine();
						stateMach.setcClass(class4StMach);
						stMachinesWithId.put(idParClass, stateMach); //пока id класса для stateMachine
					}else{
						CClass oldClass = stateMach.getcClass();
						if(oldClass != class4StMach){
							stateMach = new StateMachine();
							stateMach.setcClass(class4StMach);
							stMachinesWithId.put(idParClass, stateMach);
						}
					}
				}
				else
					logger.error("No class for statemachine in list of parsed classes");
				
				State curState = new State();
				curState.setName(packElem.getAttribute("name"));
				if(stateMach != null){
					curState.setStateMachine(stateMach);
					
					List<State> statesOfStM = stateMach.getStates();
					
					if(statesOfStM == null){
						statesOfStM = new LinkedList<State>();
						stateMach.setStates(statesOfStM);
					}
					statesOfStM.add(curState);
				}else
					logger.error("No statemachine for state");
			}
		}
		}
}
