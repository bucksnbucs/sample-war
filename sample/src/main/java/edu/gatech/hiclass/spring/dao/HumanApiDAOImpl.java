package edu.gatech.hiclass.spring.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import edu.gatech.hiclass.spring.model.ActivityData;
import edu.gatech.hiclass.spring.model.BMIData;
import edu.gatech.hiclass.spring.model.BloodData;
import edu.gatech.hiclass.spring.model.BloodGlucoseData;
import edu.gatech.hiclass.spring.model.BloodOxygenData;
import edu.gatech.hiclass.spring.model.BloodPressureData;
import edu.gatech.hiclass.spring.model.BodyData;
import edu.gatech.hiclass.spring.model.BodyFatData;
import edu.gatech.hiclass.spring.model.GeneticTraitData;
import edu.gatech.hiclass.spring.model.Measurements;
import edu.gatech.hiclass.spring.model.SleepData;
import edu.gatech.hiclass.spring.model.WeightData;

public class HumanApiDAOImpl implements HumanApiDAO {

	@Override
	public GeneticTraitData[] getGeneticTraitData(String tokenID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<GeneticTraitData[]> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/genetic/traits?access_token=" + tokenID, 
				GeneticTraitData[].class);
		GeneticTraitData[] body = responseEntity.getBody();
		GeneticTraitData[] data = Measurements.cleanData(body);
		return data;
	}

	@Override
	public ActivityData[] getActivityData(String tokenID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ActivityData[]> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/activities?access_token=" + tokenID, 
				ActivityData[].class);
		ActivityData[] body = responseEntity.getBody();
		//ActivityData[] data = Measurements.cleanData(body);
		//We are cleaning this data on the ChartData model
		return body;
	}

	@Override
	public BloodGlucoseData[] getBloodGlucoseData(String tokenID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BloodGlucoseData[]> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/blood_glucose/readings?access_token=" + tokenID, 
				BloodGlucoseData[].class);
		BloodGlucoseData[] body = responseEntity.getBody();
		BloodGlucoseData[] data = Measurements.cleanData(body);
		return data;
	}

	@Override
	public BloodOxygenData[] getBloodOxygenData(String tokenID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BloodOxygenData[]> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/blood_oxygen/readings?access_token=" + tokenID, 
				BloodOxygenData[].class);
		BloodOxygenData[] body = responseEntity.getBody();
		BloodOxygenData[] data = Measurements.cleanData(body);
		return data;
	}

	@Override
	public BloodPressureData[] getBloodPressureData(String tokenID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BloodPressureData[]> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/blood_pressure/readings?access_token=" + tokenID, 
				BloodPressureData[].class);
		BloodPressureData[] body = responseEntity.getBody();
		BloodPressureData[] data = Measurements.cleanData(body);
		return data;
	}

	@Override
	public BloodData[] getBloodData(String tokenID) {
		BloodGlucoseData[] glucose = getBloodGlucoseData(tokenID);
		BloodOxygenData[] oxygen = getBloodOxygenData(tokenID);
		BloodData[] data = new BloodData[1];
		BloodData bloodData = new BloodData();
		data[0] = bloodData;
		try {
			data = Measurements.combineData(glucose, oxygen, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public BodyData[] getBodyData(String tokenID) {
		BMIData[] bmi = getBMIData(tokenID);
		BodyFatData[] bodyFat = getBodyFatData(tokenID);
		BodyData[] data = new BodyData[1];
		BodyData bodyData = new BodyData();
		data[0] = bodyData;
		try {
			data = Measurements.combineData(bmi, bodyFat, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public BMIData[] getBMIData(String tokenID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BMIData[]> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/bmi/readings?access_token=" + tokenID, 
				BMIData[].class);
		BMIData[] body = responseEntity.getBody();
		BMIData[] data = Measurements.cleanData(body);
		return data;
	}

	@Override
	public BodyFatData[] getBodyFatData(String tokenID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BodyFatData[]> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/body_fat/readings?access_token=" + tokenID, 
				BodyFatData[].class);
		BodyFatData[] body = responseEntity.getBody();
		BodyFatData[] data = Measurements.cleanData(body);
		return data;
	}

	@Override
	public WeightData[] getWeightData(String tokenID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WeightData[]> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/weight/readings?access_token=" + tokenID, 
				WeightData[].class);
		WeightData[] body = responseEntity.getBody();
		WeightData[] data = Measurements.cleanData(body);
		return data;
	}

	@Override
	public SleepData[] getSleepData(String tokenID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<SleepData[]> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/sleeps?access_token=" + tokenID,
				SleepData[].class);
		SleepData[] body = responseEntity.getBody();
		SleepData[] data = Measurements.cleanData(body);
		return data;
	}

	/*
	 * Indivitual calls
	 */

	@Override
	public WeightData getLastWeightData() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WeightData> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/weight/readings/553bb8b48a5896070059cb08?access_token=demo", 
				WeightData.class);
		WeightData data = responseEntity.getBody();
		return data;
	}

	@Override
	public SleepData getLastSleepData() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<SleepData> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/sleeps/553bb8c88a5896070059cbd0?access_token=demo", 
				SleepData.class);
		SleepData data = responseEntity.getBody();
		return data;
	}

	@Override
	public BloodPressureData getLastBloodPressureData() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BloodPressureData> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/blood_pressure/readings/550b8a54834dd16f259683ab?access_token=demo", 
				BloodPressureData.class);
		BloodPressureData data = responseEntity.getBody();
		return data;
	}

	@Override
	public BodyFatData getLastBodyFatData() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BodyFatData> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/body_fat/readings/553bb8b78a5896070059cb38?access_token=demo", 
				BodyFatData.class);
		BodyFatData data = responseEntity.getBody();
		return data;
	}

	@Override
	public ActivityData getLastActivityData() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ActivityData> responseEntity = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/activities/551aa2e4794b3108001226ca?access_token=demo", 
				ActivityData.class);
		ActivityData data = responseEntity.getBody();
		return data;
	}

}
