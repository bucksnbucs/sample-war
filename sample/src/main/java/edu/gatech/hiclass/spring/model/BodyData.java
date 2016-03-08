package edu.gatech.hiclass.spring.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import edu.gatech.hiclass.spring.model.Measurements.CombinedMeasurements;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BodyData extends CombinedMeasurements {
	public int getBMI() {
		return value1;
	}
	public void setBMI(int bmi) {
		this.value1 = bmi;
	}
	public int getBodyFat() {
		return value2;
	}
	public void setBodyFat(int bodyFat) {
		this.value2 = bodyFat;
	}

	@Override
	public String toString() {
        return ("Source " + getSource()
        		+ "  Date " + getDate()
        		+ "  BMI " + getBMI() + "(kg/m2)"
        		+ "  Body Fat " + getValue() + "(%)");
	}
}
