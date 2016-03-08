package edu.gatech.hiclass.spring.dao;

import edu.gatech.hiclass.spring.model.ActivityData;
import edu.gatech.hiclass.spring.model.BMIData;
import edu.gatech.hiclass.spring.model.BloodData;
import edu.gatech.hiclass.spring.model.BloodGlucoseData;
import edu.gatech.hiclass.spring.model.BloodOxygenData;
import edu.gatech.hiclass.spring.model.BloodPressureData;
import edu.gatech.hiclass.spring.model.BodyData;
import edu.gatech.hiclass.spring.model.BodyFatData;
import edu.gatech.hiclass.spring.model.GeneticTraitData;
import edu.gatech.hiclass.spring.model.SleepData;
import edu.gatech.hiclass.spring.model.WeightData;

public interface HumanApiDAO {

	public GeneticTraitData[] getGeneticTraitData(String tokenID);

	public ActivityData[] getActivityData(String tokenID);

	public BloodPressureData[] getBloodPressureData(String tokenID);

	public SleepData[] getSleepData(String tokenID);

	public WeightData[] getWeightData(String tokenID);

	public BloodGlucoseData[] getBloodGlucoseData(String tokenID);

	public BloodOxygenData[] getBloodOxygenData(String tokenID);

	/*
	 * Blood Glucose and Oxygen Data combined
	 */
	public BloodData[] getBloodData(String tokenID);

	public BMIData[] getBMIData(String tokenID);

	public BodyFatData[] getBodyFatData(String tokenID);

	/*
	 * BMI and Body Fat Data combined
	 */
	public BodyData[] getBodyData(String tokenID);

	/*
	 * Individual Calls
	 */
	public WeightData getLastWeightData();

	public SleepData getLastSleepData();

	public BloodPressureData getLastBloodPressureData();

	public BodyFatData getLastBodyFatData();

	public ActivityData getLastActivityData();

}
