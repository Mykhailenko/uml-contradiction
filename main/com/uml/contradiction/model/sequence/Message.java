package com.uml.contradiction.model.sequence;

import com.uml.contradiction.engine.model.Pair;
import com.uml.contradiction.model.statemachine.Trigger;

public class Message extends InteractionElement {
	private LifeLine source;
	private LifeLine target;
	private String methodName;
	private int paramCount;
	private Interaction interaction;
	private String originalString;

	public boolean compareWithTransition(Trigger trigger) {
		if (methodName.equals(trigger.getMethodName())
				&& paramCount == trigger.getParamCount()) {
			return true;
		} else {
			return true;
		}
	}

	public void parseStr(String str) {
		originalString = str;
		Pair<String, Integer> p = Trigger.getNameAndParamCount(str);
		methodName = p.a;
		paramCount = p.b;
		
//		// System.out.println("There are " + str);
//		// i suppose to get some like that 'ololoName(param1,param2)' or
//		// 'disable()' or 'enable'
//		if (str.contains("(")) { // first or second case
//			methodName = str.substring(0, str.indexOf("(")).trim();
//			String params = str.substring(str.indexOf("("), str.indexOf(")"));
//			params = params.replaceAll(" ", "");
//			if (params.length() > 2) {// first case
//				paramCount = params.split(",").length;
//			} else {
//				paramCount = 1;
//			}
//		} else {
//			methodName = str;
//			paramCount = 0;
//		}
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getParamCount() {
		return paramCount;
	}

	public void setParamCount(int paramCount) {
		this.paramCount = paramCount;
	}

	public LifeLine getSource() {
		return source;
	}

	public void setSource(LifeLine source) {
		this.source = source;
	}

	public LifeLine getTarget() {
		return target;
	}

	public void setTarget(LifeLine target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Message [ methodName=" + methodName + ", paramCount="
				+ paramCount + "]";
	}

	@Override
	public Type getType() {
		return Type.MESSAGE;
	}

	public Interaction getInteraction() {
		return interaction;
	}

	public void setInteraction(Interaction interaction) {
		this.interaction = interaction;
	}

	public String getOriginalString() {
		return originalString;
	}

	public void setOriginalString(String originalString) {
		this.originalString = originalString;
	}

}