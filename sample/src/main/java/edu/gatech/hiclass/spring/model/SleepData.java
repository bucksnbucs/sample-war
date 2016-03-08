package edu.gatech.hiclass.spring.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SleepData extends Measurements {
	private String source;
	private String day;
	private int timeAsleep;
	private int timeAwake;
	private int efficiency;
	private int timeToFallAsleep;
	private int timeAfterWakeup;

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getTimeAsleep() {
		return timeAsleep;
	}
	public void setTimeAsleep(int timeAsleep) {
		this.timeAsleep = timeAsleep;
	}
	public int getTimeAwake() {
		return timeAwake;
	}
	public void setTimeAwake(int timeAwake) {
		this.timeAwake = timeAwake;
	}
	public int getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}
	public int getTimeToFallAsleep() {
		return timeToFallAsleep;
	}
	public void setTimeToFallAsleep(int timeToFallAsleep) {
		this.timeToFallAsleep = timeToFallAsleep;
	}
	public int getTimeAfterWakeup() {
		return timeAfterWakeup;
	}
	public void setTimeAfterWakeup(int timeAfterWakeup) {
		this.timeAfterWakeup = timeAfterWakeup;
	}

	@Override
	public String getDate() {
		return day;
	}
	@Override
	public String toString() {
        return ("Source " + getSource()
        		+ "  Date " + getDate()
        		+ "  Time Asleep " + getTimeAsleep() + "(min)"
        		+ "  Time Awake " + getTimeAwake() + "(min)"
        		+ "  Efficiency " + getEfficiency() + "(%)"
        		+ "  Time To Fall Asleep " + getTimeToFallAsleep() + "(min)"
        		+ "  Time After Wakeup " + getTimeAfterWakeup() + "(min)");
	}
}
