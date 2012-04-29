package com.uml.contradiction.engine.model;

import java.util.Date;
import java.util.Map;

import com.uml.contradiction.engine.model.criteria.Criterion;

public class GeneralResult {
	private Map<Criterion, VerificationResult> map;
	private String projectName;
	private Date date;
	public Map<Criterion, VerificationResult> getMap() {
		return map;
	}
	public void setMap(Map<Criterion, VerificationResult> map) {
		this.map = map;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
