package edu.gatech.hiclass.spring.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityData extends Measurements {
	private String source;
	private String type;
	private String startTime;
	private int duration;
	private int distance;
	private int steps;

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getDuration() {
		return duration;
	}
	public int getMinutes()
	{
		return duration/60;
	}
	public float getKilos()
	{
		return (float) distance/1000;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getCalories() {
		return (int) ((float) distance * .075);
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	@Override
	public String getDate() {
		return startTime.split("T")[0];
	}
	@Override
	public String getKey() {
		return getDate() + getSource() + getType();
	}
	@Override
	public String toString() {
        return ("Source " + getSource()
        		+ "  Type " + getType()
        		+ "  Date " + getDate()
        		+ "  Duration " + getMinutes() + "(min)"
        		+ "  Distance " + getDistance() + "(m)"
        		+ "  Calories " + getCalories());
	}
}
