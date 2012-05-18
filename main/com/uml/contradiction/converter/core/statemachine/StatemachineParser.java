package com.uml.contradiction.converter.core.statemachine;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.converter.core.CoreParser;
import com.uml.contradiction.converter.core.CoreParserImpl;
import com.uml.contradiction.converter.core.ParsersTool;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.statemachine.Guard;
import com.uml.contradiction.model.statemachine.State;
import com.uml.contradiction.model.statemachine.StateMachine;
import com.uml.contradiction.model.statemachine.StateMachineGraph;
import com.uml.contradiction.model.statemachine.Transition;
import com.uml.contradiction.model.statemachine.Trigger;

public class StatemachineParser extends CoreParserImpl implements CoreParser {
	private static final Logger logger = Logger
			.getLogger(StatemachineParser.class);

	private Map<String, State> statesWithId = new LinkedHashMap<String, State>();
	private Map<String, Transition> transitionsWithId = new LinkedHashMap<String, Transition>();
	private Map<String, StateMachine> stMachinesWithId = new LinkedHashMap<String, StateMachine>();
	private Map<String, Trigger> triggersTransWithId = new LinkedHashMap<String, Trigger>();
	private Map<String, State> choicesWithId = new LinkedHashMap<String, State>();

	@Override
	public void makeResult() {
		List<StateMachine> stateMach_s = StateMachineGraph.getStateMachines();

		Collection<StateMachine> collectstMs = stMachinesWithId.values();
		for (StateMachine stM : collectstMs) {
			stateMach_s.add(stM);
		}
	}

	@Override
	public List<Object> parse(Element umlModelEl) {
		getAllIdDiagrams(DiagramType.STATE_MACHINE, umlModelEl);

		makeStates(umlModelEl);
		getAllTriggers(umlModelEl);

		makeTransitions(umlModelEl);

		return null;
	}

	// для состояний в классах
	private void makeStates(Element umlModelEl) {
		NodeList packNodes = umlModelEl.getElementsByTagName("packagedElement");
		StateMachine stateMach = null;
		Map<String, CClass> classesWithId = ParsersTool
				.getInstanceClassParser().getClassesWithId();

		for (int i = 0; i < packNodes.getLength(); i++) {
			Element packElem = (Element) packNodes.item(i);
			String stateType = packElem.getAttribute("xmi:type");

			// рассматриваем packagedElement для состояний в классах
			if (stateType.equals("uml:State")
					|| stateType.equals("uml:Pseudostate")
					|| stateType.equals("uml:FinalState")) {
				// привязка StateMachine к классу
				Element parentClass = (Element) packElem.getParentNode(); // элемент
																			// родитель
																			// -
																			// класс
				// связывание диаграммы состояния с классом по ID
				Element exts = (Element) parentClass.getElementsByTagName(
						"xmi:Extension").item(0);
				String stMachId = getAttrByNameAndTag(exts, "subdiagram",
						"xmi:value");
				String stMachName = null;

				if (isIdInDiagrams(stMachId)) { // такая диаграмма есть
					NodeList diagramAll = umlModelEl
							.getElementsByTagName("uml:Diagram");

					for (int temp = 0; temp < diagramAll.getLength(); temp++) {
						Element curDiagr = (Element) diagramAll.item(temp);

						if (curDiagr.getAttribute("diagramType").equals(
								"StateDiagram")
								&& curDiagr.getAttribute("xmi:id").equals(
										stMachId)) {
							stMachName = curDiagr.getAttribute("name");
						}
					}
				} else {
					logger.error("No diagramm in list ID");
				}

				String idParClass = parentClass.getAttribute("xmi:id");
				CClass class4StMach = classesWithId.get(idParClass);
				if (class4StMach != null) { // создание StateMachine с привязкой
											// к классам
					if (stateMach == null) {
						stateMach = new StateMachine();
						stateMach.setcClass(class4StMach);
						stateMach.setName(stMachName);
						stMachinesWithId.put(stMachId, stateMach); // вставка
																	// StateMachine
																	// с id
					} else {
						CClass oldClass = stateMach.getcClass();

						if (oldClass != class4StMach) { // перешли к состояниям
														// другого класса
														// (StateMachine new)
							stateMach = new StateMachine();
							stateMach.setcClass(class4StMach);
							stateMach.setName(stMachName);
							stMachinesWithId.put(stMachId, stateMach);
						}
					}
				} else {
					logger.error("No class for statemachine in list of parsed classes");
				}

				// разбор состояния
				State curState = new State();
				curState.setName(packElem.getAttribute("name"));
				String idState = packElem.getAttribute("xmi:id");
				if (stateMach != null) {
					curState.setStateMachine(stateMach);

					if (!packElem.getAttribute("kind").equals("choice")) { // если
																			// выбор
																			// state

						List<State> statesOfStM = stateMach.getStates();

						if (statesOfStM == null) {
							statesOfStM = new LinkedList<State>();
							stateMach.setStates(statesOfStM);
						}
						statesOfStM.add(curState);
					}
				} else {
					logger.error("No statemachine for state");
				}

				// разбор тригерров
				NodeList entryNodes = packElem.getElementsByTagName("entry");
				if (entryNodes.getLength() != 0) {
					Trigger trig = new Trigger();
					Element entryElem = (Element) entryNodes.item(0);
					if (entryElem.getAttribute("xmi:type").equals(
							"uml:Activity")) {
						String nameActivity = entryElem.getAttribute("name");

						trig.setMethodName(nameActivity);
						NodeList paramNodes = entryElem
								.getElementsByTagName("ownedParameter");
						trig.setParamCount(paramNodes.getLength());

						curState.setEntry(trig);
					}
				}
				NodeList exitNodes = packElem.getElementsByTagName("exit");
				if (exitNodes.getLength() != 0) {
					Trigger trig = new Trigger();
					Element exitElem = (Element) exitNodes.item(0);
					if (exitElem.getAttribute("xmi:type")
							.equals("uml:Activity")) {
						String nameActivity = exitElem.getAttribute("name");
						trig.setMethodName(nameActivity);
						NodeList paramNodes = exitElem
								.getElementsByTagName("ownedParameter");
						trig.setParamCount(paramNodes.getLength());

						curState.setExit(trig);
					}
				}
				NodeList doNodes = packElem.getElementsByTagName("doActivity");
				if (doNodes.getLength() != 0) {
					Trigger trig = new Trigger();
					Element doElem = (Element) doNodes.item(0);
					if ((doElem.getAttribute("xmi:type"))
							.equals("uml:Activity")) {
						String nameActivity = doElem.getAttribute("name");
						trig.setMethodName(nameActivity);
						NodeList paramNodes = doElem
								.getElementsByTagName("ownedParameter");
						trig.setParamCount(paramNodes.getLength());

						curState.setDdo(trig);
					}
				}

				if (packElem.getAttribute("kind").equals("choice")) {
					// выбор
																	// state
					choicesWithId.put(idState, curState);
				} else {
					statesWithId.put(idState, curState);
				}
			}
		}
	}

	private void makeTransitions(Element umlModelEl) {
		NodeList transNodes = umlModelEl.getElementsByTagName("transition");
		StateMachine stateMach = null;
		Map<State, List<Transition>> choiceSourceWithStates = new LinkedHashMap<State, List<Transition>>();
		Map<State, List<Transition>> choiceTargetWithStates = new LinkedHashMap<State, List<Transition>>();
		boolean flag = true;

		for (int i = 0; i < transNodes.getLength(); i++) {
			Element transElem = (Element) transNodes.item(i);

			// рассматриваем transition
			if (transElem.getAttribute("xmi:type").equals("uml:Transition")) {
				State stSourceChoice = null;
				State stTargetChoice = null;

				Transition curTrans = new Transition();
				curTrans.setName(transElem.getAttribute("name"));
				String idTrans = transElem.getAttribute("xmi:id");
				String idSource = transElem.getAttribute("source");
				String idTarget = transElem.getAttribute("target");

				State stSource = statesWithId.get(idSource);
				State stTarget = statesWithId.get(idTarget);

				if (stSource == null) {
					flag = false;
					stSourceChoice = choicesWithId.get(idSource);
					if (stSourceChoice == null) {
						logger.error("No source state for transition");
					} else { // если исходный конец choice

						List<Transition> transList = choiceSourceWithStates
								.get(stSource);
						if (transList == null) {
							transList = new LinkedList<Transition>();
							transList.add(curTrans);
							choiceSourceWithStates.put(stSource, transList);
						} else {
							transList.add(curTrans);
						}

					}
				} else {
					if (stTarget == null) {
						flag = false;
						stTargetChoice = choicesWithId.get(idTarget);

						if (stTargetChoice == null) {
							logger.error("No target state for transition");
						} else { // если конечный конец choice

							List<Transition> transList = choiceTargetWithStates
									.get(stTarget);
							if (transList == null) {
								transList = new LinkedList<Transition>();
								transList.add(curTrans);
								choiceTargetWithStates.put(stTarget, transList);
							} else {
								transList.add(curTrans);
							}

						}
					}
				}

				curTrans.setSource(stSource);
				curTrans.setTarget(stTarget);

				if (stSource != null) {
					stateMach = stSource.getStateMachine();
				} else {
					if (stTarget != null) {
						stateMach = stTarget.getStateMachine();
					}
				}
				if (stateMach != null) {
					curTrans.setStateMachine(stateMach);

					if (flag) {
						List<Transition> transOfStM = stateMach
								.getTransitions();

						if (transOfStM == null) {
							transOfStM = new LinkedList<Transition>();
							stateMach.setTransitions(transOfStM);
						}
						transOfStM.add(curTrans);
					}

					stateMach = null;
					// триггеры для Transition
					if (triggersTransWithId != null) {
						NodeList nodesTrig = transElem
								.getElementsByTagName("trigger");
						for (int t = 0; t < nodesTrig.getLength(); t++) {
							Element refTrigElem = (Element) nodesTrig.item(t);
							String idTrig = refTrigElem.getAttribute("event");

							Trigger trig = triggersTransWithId.get(idTrig);

							List<Trigger> trigs4Tran = curTrans.getTriggers();

							if (trigs4Tran == null) {
								trigs4Tran = new LinkedList<Trigger>();
								curTrans.setTriggers(trigs4Tran);
							}
							trigs4Tran.add(trig);
						}
					}
					// Guard для Transition
					Element ownRule = (Element) transElem.getElementsByTagName(
							"ownedRule").item(0);
					String connt = getAttrByNameAndTag(ownRule,
							"specification", "body");
					if (connt != null) {
						if (!connt.equals("")) {
							Guard grd = new Guard();
							grd.setConstraint(connt);
							curTrans.setGuard(grd);
						}
					}
					if (flag) {
						transitionsWithId.put(idTrans, curTrans);
					}
				}
			}
		}

		// проход по всем элементам HashMap для Choice
		if (!choiceSourceWithStates.isEmpty()) {
			// для всех choice
			for (State key : choiceSourceWithStates.keySet()) {
				List<Transition> toChoiceTrans = choiceTargetWithStates
						.get(key);
				List<Transition> fromChoiceTrs = choiceSourceWithStates
						.get(key);
				// для каждого входящего в Сhoice подставляем множество
				// выходящих transitions
				for (Transition trTo : toChoiceTrans) {
					for (Transition trFrom : fromChoiceTrs) {
						Transition curTrans = new Transition();
						curTrans.setName(trFrom.getName());
						curTrans.setTarget(trFrom.getTarget());
						curTrans.setGuard(trFrom.getGuard());
						curTrans.setStateMachine(trFrom.getStateMachine());
						curTrans.setTriggers(trFrom.getTriggers());

						curTrans.setSource(trTo.getSource());

						StateMachine stateM = trFrom.getStateMachine();
						if (stateM != null) {
							List<Transition> transOfStM = stateM
									.getTransitions();

							if (transOfStM == null) {
								transOfStM = new LinkedList<Transition>();
								stateM.setTransitions(transOfStM);
							}
							transOfStM.add(curTrans);
						}
					}
				}
			}
		}
	}

	private void getAllTriggers(Element umlModelEl) {
		NodeList trigsNodes = umlModelEl
				.getElementsByTagName("packagedElement");

		for (int i = 0; i < trigsNodes.getLength(); i++) {
			Element trigElem = (Element) trigsNodes.item(i);

			// рассматриваем trigger
			if (trigElem.getAttribute("xmi:type").equals("uml:AnyReceiveEvent")) {
				Trigger trig;
				String idTrig = trigElem.getAttribute("xmi:id");

				String nameTrig = trigElem.getAttribute("name");
				trig = Trigger.createTrigger(nameTrig);

				triggersTransWithId.put(idTrig, trig);
			}
		}
	}
}
