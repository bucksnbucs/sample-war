package edu.gatech.hiclass.spring.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BloodPressureData extends Measurements {
	private String source;
	private String timestamp;
	private int systolic;
	private int diastolic;
	private int heartRate;

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getSystolic() {
		return systolic;
	}
	public void setSystolic(int systolic) {
		this.systolic = systolic;
	}
	public int getDiastolic() {
		return diastolic;
	}
	public void setDiastolic(int diastolic) {
		this.diastolic = diastolic;
	}
	public int getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	@Override
	public String getDate() {
		return timestamp.split("T")[0];
	}
	@Override
	public String toString() {
        return ("Source " + getSource()
        		+ "  Date " + getDate()
        		+ "  Systolic " + getSystolic() + "(mmHg)"
        		+ "  Diastolic " + getDiastolic() + "(mmHg)"
        		+ "  Heart Rate " + getHeartRate());
	}
}
