package edu.gatech.hiclass.web;

import java.util.Collections;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gatech.hiclass.spring.dao.HumanApiDAO;
import edu.gatech.hiclass.spring.dao.PatientDAO;
import edu.gatech.hiclass.spring.model.ActivityData;
import edu.gatech.hiclass.spring.model.BMIData;
import edu.gatech.hiclass.spring.model.BloodData;
import edu.gatech.hiclass.spring.model.BloodGlucoseData;
import edu.gatech.hiclass.spring.model.BloodOxygenData;
import edu.gatech.hiclass.spring.model.BloodPressureData;
import edu.gatech.hiclass.spring.model.BodyData;
import edu.gatech.hiclass.spring.model.BodyFatData;
import edu.gatech.hiclass.spring.model.GeneticTraitData;
import edu.gatech.hiclass.spring.model.PatientData;
import edu.gatech.hiclass.spring.model.SleepData;
import edu.gatech.hiclass.spring.model.TableDTO;
import edu.gatech.hiclass.spring.model.WeightData;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private @Autowired PatientDAO patientDAO;
	private @Autowired HumanApiDAO humanApiDAO;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String getAccount(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid)
	{
		PatientData patient = patientDAO.getPatientData(pid);
		model.addAttribute("patient",patient);
		if(pid != null)
			model.addAttribute("pid",pid);
		return "Account";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
    public String submit(@ModelAttribute("patient")PatientData patient,Model model, @RequestParam(value = "pid", required=false) String pid) {
		PatientData patient2 = patientDAO.getPatientData(pid);
		if (patient.getFirstName()=="" || patient.getLastName()==""){
			model.addAttribute("fail", true);
		}
		else{
			patient2.setFirstName(patient.getFirstName());
			patient2.setLastName(patient.getLastName());
			patient2.setRace(patient.getRace());
			patient2.setBirthdate(patient.getBirthdate());
			patient2.setHeight(patient.getHeight());
			patient2.setMaritalStatus(patient.getMaritalStatus());
			patient2.setGender(patient.getGender());
			model.addAttribute("success", true);
		}
		model.addAttribute("patient",patient2);
		if(pid != null)
			model.addAttribute("pid",pid);
		return "Account";
    }
	
	@RequestMapping(value = "/ataglance", method = RequestMethod.GET)
	public String getPatientData(Model model, @RequestParam(value = "pid", required=false) String pid, HttpServletRequest request) {
		PatientData patient = patientDAO.getPatientData(pid);
		model.addAttribute("patient",patient);
		
		ObjectMapper mapper = new ObjectMapper();
		String providers = "";
		String obsDates = "";
		String obsMap = "";
		Collections.sort(patient.getProviderList());
		Collections.sort(patient.getObsDates());
		try {
			providers = mapper.writeValueAsString(patient.getProviderList());
			obsDates = mapper.writeValueAsString(patient.getObsDates());
			obsMap = mapper.writeValueAsString(patient.getObservations());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		model.addAttribute("providerList", providers);
		model.addAttribute("obsDates",obsDates);
		model.addAttribute("obsMap",obsMap);
		
		if(pid != null)
			model.addAttribute("pid",pid);
		return "Patient";
	}

	@RequestMapping(value = "/appointment", method = RequestMethod.GET)
	public String getAppointment(Model model, @RequestParam(value = "pid", required=false) String pid)
	{
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "Appointment";
	}

	@RequestMapping(value = "/document", method = RequestMethod.GET)
	public String getDocument(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid)
	{
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "Documents";
	}

	/*
	 * PAGES
	 */

	@RequestMapping(value = "/genetic_trait", method = RequestMethod.GET)
	public String getGeneticTrait(Locale locale, Model model, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "Measurements/GeneticTrait";
	}

	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String getActivity(Locale locale, Model model, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "Measurements/Activity";
	}

	@RequestMapping(value = "/blood", method = RequestMethod.GET)
	public String getBlood(Locale locale, Model model, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "Measurements/Blood";
	}

	@RequestMapping(value = "/blood_pressure", method = RequestMethod.GET)
	public String getBloodPressure(Locale locale, Model model, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "Measurements/BloodPressure";
	}

	@RequestMapping(value = "/body", method = RequestMethod.GET)
	public String getBodyFat(Locale locale, Model model, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "Measurements/Body";
	}

	@RequestMapping(value = "/weight", method = RequestMethod.GET)
	public String getWeight(Locale locale, Model model, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "Measurements/Weight";
	}

	@RequestMapping(value = "/sleep", method = RequestMethod.GET)
	public String getSleep(Locale locale, Model model, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "Measurements/Sleep";
	}

	/*
	 * Data
	 */

	@RequestMapping(value = "/get/genetic_trait", method = RequestMethod.GET)
	public @ResponseBody TableDTO getGeneticTrait(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		GeneticTraitData[] geneticTrait = humanApiDAO.getGeneticTraitData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(geneticTrait);
		return dto;
	}

	@RequestMapping(value = "/get/activity", method = RequestMethod.GET)
	public @ResponseBody TableDTO getActivity(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		ActivityData[] activity = humanApiDAO.getActivityData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(activity);
		return dto;
	}

	@RequestMapping(value = "/get/blood", method = RequestMethod.GET)
	public @ResponseBody TableDTO getBloodData(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		BloodData[] blood = humanApiDAO.getBloodData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(blood);
		return dto;
	}

	@RequestMapping(value = "/get/blood_glucose", method = RequestMethod.GET)
	public @ResponseBody TableDTO getBloodGlucoseData(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		BloodGlucoseData[] bloodGlucose = humanApiDAO.getBloodGlucoseData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(bloodGlucose);
		return dto;
	}

	@RequestMapping(value = "/get/blood_oxygen", method = RequestMethod.GET)
	public @ResponseBody TableDTO getBloodOxygenData(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		BloodOxygenData[] bloodOxygen = humanApiDAO.getBloodOxygenData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(bloodOxygen);
		return dto;
	}

	@RequestMapping(value = "/get/blood_pressure", method = RequestMethod.GET)
	public @ResponseBody TableDTO getBloodPressureData(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		BloodPressureData[] bloodPressure = humanApiDAO.getBloodPressureData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(bloodPressure);
		return dto;
	}

	@RequestMapping(value = "/get/body", method = RequestMethod.GET)
	public @ResponseBody TableDTO getBodyData(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		BodyData[] body = humanApiDAO.getBodyData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(body);
		return dto;
	}

	@RequestMapping(value = "/get/bmi", method = RequestMethod.GET)
	public @ResponseBody TableDTO getBMIData(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		BMIData[] bmi = humanApiDAO.getBMIData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(bmi);
		return dto;
	}

	@RequestMapping(value = "/get/body_fat", method = RequestMethod.GET)
	public @ResponseBody TableDTO getBodyFatData(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		BodyFatData[] bodyFat = humanApiDAO.getBodyFatData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(bodyFat);
		return dto;
	}

	@RequestMapping(value = "/get/weight", method = RequestMethod.GET)
	public @ResponseBody TableDTO getWeightData(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		WeightData[] weight = humanApiDAO.getWeightData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(weight);
		return dto;
	}

	@RequestMapping(value = "/get/sleep", method = RequestMethod.GET)
	public @ResponseBody TableDTO getSleepData(Model model, HttpServletRequest request, @RequestParam("tokenID") String tokenID, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		SleepData[] sleep = humanApiDAO.getSleepData(tokenID);
		TableDTO dto = new TableDTO();
		dto.setData(sleep);
		return dto;
	}

	/*
	 * Individual Calls
	 */

	@RequestMapping(value = "/get/last_weight", method = RequestMethod.GET)
	public @ResponseBody int getLastWeightData(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		WeightData data = humanApiDAO.getLastWeightData();
		return data.getValue();
	}

	@RequestMapping(value = "/get/last_sleep", method = RequestMethod.GET)
	public @ResponseBody int getLastSleepData(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		SleepData data = humanApiDAO.getLastSleepData();
		return data.getEfficiency();
	}

	@RequestMapping(value = "/get/last_heart_rate", method = RequestMethod.GET)
	public @ResponseBody int getLastHeartRateData(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		BloodPressureData data = humanApiDAO.getLastBloodPressureData();
		return data.getHeartRate();
	}

	@RequestMapping(value = "/get/last_fat", method = RequestMethod.GET)
	public @ResponseBody int getLastBodyFatData(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		BodyFatData data = humanApiDAO.getLastBodyFatData();
		return data.getValue();
	}

	@RequestMapping(value = "/get/last_activity", method = RequestMethod.GET)
	public @ResponseBody Object[] getLastActivityData(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid",pid);
		ActivityData data = humanApiDAO.getLastActivityData();
		Object[] lastActivity = {data.getDate(), data.getType(), data.getDistance(), data.getMinutes()};
		return lastActivity;
	}

}
