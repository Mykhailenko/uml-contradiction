package com.uml.contradiction.model.statemachine;

import com.uml.contradiction.engine.model.Pair;

public class Trigger {
	private String methodName;
	private int paramCount;

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
	public static Pair<String, Integer> getNameAndParamCount(String str) {
		Pair<String, Integer> result = new Pair<String, Integer>();
		// i suppose to get some like that 'ololoName(param1,param2)' or
		// 'disable()' or 'enable'
		if(str.contains("()")){
			result.a = str.substring(0, str.indexOf('(')).trim();
			result.b = 0;
		}else{
			if (str.contains("(")) { // first or second case
				result.a = str.substring(0, str.indexOf('(')).trim();
				String params = str.substring(str.indexOf('('), str.indexOf(')'));
				params = params.replaceAll(" ", "");
				if (params.length() > 2) {// first case
					result.b = params.split(",").length;
				} else {
					result.b = 1;
				}
			} else {
				result.a = str;
				result.b = 0;
			}
		}
		return result;

	}

	public static Trigger createTrigger(String str) {
		Trigger result = new Trigger();
		final Pair<String, Integer> p = getNameAndParamCount(str);
		result.methodName = p.a;
		result.paramCount = p.b;
		return result;

	}

	@Override
	public String toString() {
		return "Trigger [methodName=" + methodName + ", paramCount="
				+ paramCount + "]";
	}

}
