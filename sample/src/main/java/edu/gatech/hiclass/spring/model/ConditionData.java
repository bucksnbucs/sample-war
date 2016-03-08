package edu.gatech.hiclass.spring.model;

public class ConditionData {
	private String condition;
	private String onsetDate;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getOnsetDate() {
		return onsetDate;
	}
	public void setOnsetDate(String onsetDate) {
		this.onsetDate = onsetDate;
	}
	public String toString() {
		return condition;
	}

}
