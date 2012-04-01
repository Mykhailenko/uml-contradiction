package com.uml.contradiction.model.statemachine;

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
	public static Trigger createTrigger(String str){
		Trigger result = null;
		// i suppose to get some like that 'ololoName(param1,param2)' or 'disable()' or 'enable' 
		if(str.contains("(")){ // first or second case
			result.methodName = str.substring(0, str.indexOf("(")).trim();
			String params = str.substring(str.indexOf("("), str.indexOf(")"));
			params = params.replaceAll(" ", "");
			if(params.length() > 2){//first case
				result.paramCount = params.split(",").length;
			}else{
				result.paramCount = 1;
			}
		}else{
			result.methodName = str;
			result.paramCount = 0;
		}
		return null;
		
	}
	@Override
	public String toString() {
		return "Trigger [methodName=" + methodName + ", paramCount="
				+ paramCount + "]";
	}
	
}
