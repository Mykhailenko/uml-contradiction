package com.uml.contradiction.model.sequence;
import java.util.*;

public class CombinedFragment extends Interaction {

	private Interaction enclosingInteraction;
	private InteractionOperator operator;
	private List<LifeLine> covered;
	private List<InteractionOperand> operands;
	public Interaction getEnclosingInteraction() {
		return enclosingInteraction;
	}
	public void setEnclosingInteraction(Interaction enclosingInteraction) {
		this.enclosingInteraction = enclosingInteraction;
	}
	public InteractionOperator getOperator() {
		return operator;
	}
	public void setOperator(InteractionOperator operator) {
		this.operator = operator;
	}
	public List<LifeLine> getCovered() {
		return covered;
	}
	public void setCovered(List<LifeLine> covered) {
		this.covered = covered;
	}
	public List<InteractionOperand> getOperands() {
		return operands;
	}
	public void setOperands(List<InteractionOperand> operands) {
		this.operands = operands;
	}
	
	
}