package edu.gatech.hiclass.spring.model;

public class AllergyData {
	private String substance;
	private String reaction;
	private String severity;
	private String date;
	
	//private String[] substances = {"Apitoxin (Bee Venom)","Penicillin","Acetaminophen","House Dust Allergen","Staphylococcus Aureus","Morphine"};
	//private String[] reactions = {"Minor","Moderate","Serious","Severe"};
	//private String[] severities = {"Constipation","Diarrhea","Brochospasm","Nausea","Anaphylaxis Reaction"};
	
	public String getSubstance() {
		return substance;
	}
	public void setSubstance(String substance) {
		this.substance = substance;
	}
	public String getReaction() {
		return reaction;
	}
	public void setReaction(String reaction) {
		this.reaction = reaction;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String toString()
	{
		return this.substance;
	}
	
}
