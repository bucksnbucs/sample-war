package edu.gatech.hiclass.humanapi;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import edu.gatech.hiclass.spring.model.ActivityData;
import edu.gatech.hiclass.spring.model.BMIData;
import edu.gatech.hiclass.spring.model.BloodData;
import edu.gatech.hiclass.spring.model.BloodGlucoseData;
import edu.gatech.hiclass.spring.model.BloodOxygenData;
import edu.gatech.hiclass.spring.model.BloodPressureData;
import edu.gatech.hiclass.spring.model.BodyFatData;
import edu.gatech.hiclass.spring.model.GeneticTraitData;
import edu.gatech.hiclass.spring.model.Measurements;
import edu.gatech.hiclass.spring.model.SleepData;
import edu.gatech.hiclass.spring.model.WeightData;

public class HumanAPIHelperTest {

	@Ignore
    @Test
    public void test_genetic_trait() {
        RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<GeneticTraitData[]> response = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/genetic/traits?access_token=demo", 
				GeneticTraitData[].class);

        if (HttpStatus.OK == response.getStatusCode()) {
        	GeneticTraitData[] geneticTraits = response.getBody();
        	geneticTraits = Measurements.cleanData(geneticTraits);
        	GeneticTraitData geneticTrait = geneticTraits[0];
        	geneticTrait.getDate();
        } else {
        	fail("test_genetic_trait error " + response.getStatusCode());
        }
    }

	@Ignore
	@Test
    public void test_activity() {
        RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ActivityData[]> response = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/activities?access_token=demo", 
				ActivityData[].class);

        if (HttpStatus.OK == response.getStatusCode()) {
			ActivityData[] activities = response.getBody();
			ActivityData activity = activities[0];
			activity.getDate();
        } else {
        	fail("test_activities error " + response.getStatusCode());
        }
    }

	@Ignore
    @Test
    public void test_blood_glucose() {
		BloodGlucoseData[] glucose = getBloodGlucoseData();
        if (glucose != null) {
        	BloodGlucoseData bloodGlucoseData = glucose[0];
        	bloodGlucoseData.getDate();
        } else {
        	fail("test_blood_glucose error");
        }
    }

	private BloodGlucoseData[] getBloodGlucoseData() {
        RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BloodGlucoseData[]> response = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/blood_glucose/readings?access_token=demo", 
				BloodGlucoseData[].class);

        if (HttpStatus.OK == response.getStatusCode()) {
        	BloodGlucoseData[] bloodGlucose = response.getBody();
        	return bloodGlucose;
        } else {
        	return null;
        }
	}

	@Ignore
    @Test
    public void test_blood_oxygen() {
		BloodOxygenData[] oxygen = getBloodOxygenData();
        if (oxygen != null) {
        	BloodOxygenData bloodOxygenData = oxygen[0];
        	bloodOxygenData.getDate();
        } else {
        	fail("test_blood_oxygen error");
        }
    }

    private BloodOxygenData[] getBloodOxygenData() {
        RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BloodOxygenData[]> response = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/blood_oxygen/readings?access_token=demo", 
				BloodOxygenData[].class);

        if (HttpStatus.OK == response.getStatusCode()) {
        	BloodOxygenData[] bloodOxygen = response.getBody();
        	return bloodOxygen;
        } else {
        	return null;
        }
	}

    @Ignore
    @Test
    public void test_blood() {
		BloodGlucoseData[] glucose = getBloodGlucoseData();
		BloodOxygenData[] oxygen = getBloodOxygenData();
		BloodData[] data = new BloodData[1];
		BloodData bloodData = new BloodData();
		data[0] = bloodData;
		try {
			data = Measurements.combineData(glucose, oxygen, data);
		} catch (Exception e) {
        	fail("test_blood error " + e);
		}
    }

	@Ignore
    @Test
    public void test_blood_pressure() {
        RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BloodPressureData[]> response = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/blood_pressure/readings?access_token=demo", 
				BloodPressureData[].class);

        if (HttpStatus.OK == response.getStatusCode()) {
        	BloodPressureData[] bloodPressure = response.getBody();
        	BloodPressureData bloodPressureData = bloodPressure[0];
        	bloodPressureData.getDate();
        } else {
        	fail("test_blood_pressure error " + response.getStatusCode());
        }
    }

	@Ignore
    @Test
    public void test_bmi() {
        RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BMIData[]> response = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/bmi/readings?access_token=demo", 
				BMIData[].class);

        if (HttpStatus.OK == response.getStatusCode()) {
        	BMIData[] bmi = response.getBody();
        	BMIData bmiData = bmi[0];
        	bmiData.getDate();
        } else {
        	fail("test_bmi error " + response.getStatusCode());
        }
    }

	@Ignore
    @Test
    public void test_body_fat() {
        RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BodyFatData[]> response = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/body_fat/readings?access_token=demo", 
				BodyFatData[].class);

        if (HttpStatus.OK == response.getStatusCode()) {
        	BodyFatData[] bodyFat = response.getBody();
        	BodyFatData bodyFatData = bodyFat[0];
        	bodyFatData.getDate();
        } else {
        	fail("test_body_fat error " + response.getStatusCode());
        }
    }

	@Ignore
    @Test
    public void test_weight() {
        RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WeightData[]> response = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/weight/readings?access_token=demo", 
				WeightData[].class);

        if (HttpStatus.OK == response.getStatusCode()) {
        	WeightData[] weight = response.getBody();
        	WeightData weightData = weight[0];
        	weightData.getDate();
        } else {
        	fail("test_weight error " + response.getStatusCode());
        }
    }

	@Ignore
    @Test
    public void test_sleep() {
        RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<SleepData[]> response = restTemplate.getForEntity(
				"https://api.humanapi.co/v1/human/sleeps?access_token=demo", 
				SleepData[].class);

        if (HttpStatus.OK == response.getStatusCode()) {
        	SleepData[] sleep = response.getBody();
        	SleepData sleepData = sleep[0];
        	sleepData.getDate();
        } else {
        	fail("test_sleep error " + response.getStatusCode());
        }
    }

}
