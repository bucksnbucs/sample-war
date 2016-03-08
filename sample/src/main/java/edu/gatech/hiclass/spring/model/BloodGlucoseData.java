package edu.gatech.hiclass.spring.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BloodGlucoseData extends Measurements {
	private String timestamp;
	private int value;

	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String getDate() {
		return timestamp.split("T")[0];
	}
	@Override
	public String toString() {
        return ("Source " + getSource()
        		+ "  Date " + getDate()
        		+ "  Glucose " + getValue() + "(mg/dL)");
	}
}
