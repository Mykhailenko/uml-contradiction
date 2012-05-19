package com.uml.contradiction.engine.model.criteria.result;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.common.DiagramType;
import com.uml.contradiction.engine.model.HistoryPlainItem;
import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.model.Diagram;
import com.uml.contradiction.model.cclass.ClassDiagram;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.object.ObjectDiagram;
import com.uml.contradiction.model.object.ObjectGraph;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.SequenceGraph;
import com.uml.contradiction.model.statemachine.StateMachine;
import com.uml.contradiction.model.statemachine.StateMachineGraph;

public abstract class ResultTemplate {
	protected List<Diagram> diagrams = new LinkedList<Diagram>();
	protected List<DiagramType> diagrTypes = new LinkedList<DiagramType>();
	protected String description;
	public static String ELEMENT_MARKER = "!";

	public abstract void fill(HistoryPlainItem vars);

	public abstract void setDiagrTypes();

	protected ResultTemplate() {
		this.setDiagrTypes();
	}

	public void setDiagrams(List<VariableValue> vars) {

		for (int i = 0; i < vars.size(); i++) {
			System.out.println(i);
			if (vars.get(i) != null) {
				Object var = vars.get(i);
				DiagramType type = diagrTypes.get(i);
				Diagram d = getDiagram(type, var);
				if (d != null && !this.diagrams.contains(d)) {
					this.diagrams.add(d);
				}
			}
		}
	}

	public Diagram getDiagram(DiagramType type, Object var) {
		Diagram res = null;
		Object element = ((VariableValue) (var)).value;

		// System.out.println("Searching for element: " + element.toString());

		if (type == null) {
			return null;
		} else if (type == DiagramType.CLASS) {
			List<ClassDiagram> cds = ClassGraph.getClassDiagrams();
			for (ClassDiagram cd : cds) {
				if (cd.getClasses().contains(element)
						|| cd.getAssociations().contains(element)
						|| cd.getDependencies().contains(element)
						|| cd.getRealizations().contains(element)
						|| cd.getDependencies().contains(element)) {
					res = cd;
					break;
				}
			}
		} else if (type == DiagramType.OBJECT) {
			List<ObjectDiagram> ods = ObjectGraph.getObjectDiagrams();
			for (ObjectDiagram od : ods) {
				if (od.getObjects().contains(element)
						|| od.getLinks().contains(element)) {
					res = od;
					break;
				}
			}
		} else if (type == DiagramType.STATE_MACHINE) {
			List<StateMachine> sms = StateMachineGraph.getStateMachines();
			for (StateMachine sm : sms) {
				if (sm.getStates().contains(element)
						|| sm.getTransitions().contains(element)) {
					res = sm;
					break;
				}
			}
		} else if (type == DiagramType.SEQUENCE) {
			List<Interaction> ins = SequenceGraph.getInteractions();
			for (Interaction inter : ins) {
				if (inter.getLifeLines().contains(element)
						|| inter.getChilds().contains(element)) {
					res = inter;
					break;
				}
			}
		}
		return res;
	}

	public List<Diagram> getDiagrams() {
		return this.diagrams;
	};

	public String getDiagramsNames() {
		String res = new String();
		for (int i = 0; i < this.diagrams.size(); i++) {
			res = res + diagrams.get(i).getName();
			if (i != this.diagrams.size() - 1) {
				res = res + " - ";
			}
		}
		return res;
	}

	public String getDescription() {

		return this.description;
	}
}
