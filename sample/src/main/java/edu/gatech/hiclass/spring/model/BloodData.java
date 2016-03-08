package edu.gatech.hiclass.spring.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import edu.gatech.hiclass.spring.model.Measurements.CombinedMeasurements;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BloodData extends CombinedMeasurements {
	public int getGlucose() {
		return value1;
	}
	public void setGlucose(int glucose) {
		this.value1 = glucose;
	}
	public int getOxygen() {
		return value2;
	}
	public void setOxygen(int oxygen) {
		this.value2 = oxygen;
	}

	@Override
	public String toString() {
        return ("Source " + getSource()
        		+ "  Date " + getDate()
        		+ "  Glucose " + getGlucose() + "(mg/dL)"
        		+ "  Oxygen " + getOxygen() + "(%SpO2)");
	}
}
