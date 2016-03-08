package edu.gatech.hiclass.spring.model;

import java.util.Random;

public class FamilyHistoryData {
	private String condition;
	private String relation;
	private String onsetDate;
	private String onsetAge;
	
	private String[] relationships = {"Mother","Father","Sister","Brother","Grandmother (Maternal)","Grandfather (Maternal)","Grandmother (Paternal)","Grandfather (Paternal)"};
	
	public FamilyHistoryData() {
		int offset = new Random().nextInt(20);
		this.relation = (relationships[new Random().nextInt(relationships.length)]);
		if(relation == "Mother" || relation == "Father")
			onsetAge = Integer.toString(offset + 40);
		if(relation.contains("Grandmother") || relation.contains("Grandfather"))
			onsetAge = Integer.toString(offset + 60);
		if(relation == "Sister" || relation == "Brother")
			onsetAge = Integer.toString(offset + 20);
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getOnsetDate() {
		return onsetDate;
	}

	public void setOnsetDate(String onsetDate) {
		this.onsetDate = onsetDate;
	}

	public String getOnsetAge() {
		return onsetAge;
	}

	public void setOnsetAge(String onsetAge) {
		this.onsetAge = onsetAge;
	}
			
}
