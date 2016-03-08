package edu.gatech.hiclass.spring.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientData {
	private String firstName;
	private String lastName;
	private String birthdate;
	private String height;
	private String id;
	private String smoker;
	private String drinker;
	private String gender;
	private String maritalStatus;
	private String race;
	private List<ConditionData> conditions;
	private List<String> surgeries;
	private List<MedicationData> medications;
	private List<AllergyData> allergies;
	private List<LabData> labs;
	private List<AppointmentData> appointmentData;
	
	private List<String> providerList;
	private List<String> obsDates;
	private Map<String, List<ObservationData>> observations;
	
	private List<String> medicationList;
	
	public PatientData() {
		super();
		this.conditions = new ArrayList<ConditionData>();
		this.surgeries = new ArrayList<String>();
		this.medications = new ArrayList<MedicationData>();
		this.allergies = new ArrayList<AllergyData>();
		this.medicationList = new ArrayList<String>();
		this.labs = new ArrayList<LabData>();
		this.providerList = new ArrayList<String>();
		this.observations = new HashMap<String, List<ObservationData>>();
		this.obsDates = new ArrayList<String>();
		this.appointmentData = new ArrayList<AppointmentData>();
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getSmoker() {
		return smoker;
	}
	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}
	public String getDrinker() {
		return drinker;
	}
	public void setDrinker(String drinker) {
		this.drinker = drinker;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public List<ConditionData> getConditions() {
		return conditions;
	}
	public void setConditions(List<ConditionData> conditions) {
		this.conditions = conditions;
	}
	public List<String> getSurgeries() {
		return surgeries;
	}
	public void setSurgeries(List<String> surgeries) {
		this.surgeries = surgeries;
	}
	public List<MedicationData> getMedications() {
		return medications;
	}
	public void setMedications(List<MedicationData> medications) {
		this.medications = medications;
	}
	public List<AllergyData> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<AllergyData> allergies) {
		this.allergies = allergies;
	}
	public List<String> getMedicationList() {
		return medicationList;
	}
	public void setMedicationList(List<String> medicationList) {
		this.medicationList = medicationList;
	}
	public List<LabData> getLabs() {
		return labs;
	}
	public void setLabs(List<LabData> labs) {
		this.labs = labs;
	}
	public List<String> getProviderList() {
		return providerList;
	}
	public Map<String, List<ObservationData>> getObservations() {
		return observations;
	}
	public void setObservations(Map<String, List<ObservationData>> observations) {
		this.observations = observations;
	}
	public List<String> getObsDates() {
		return obsDates;
	}
	public void setObsDates(List<String> obsDates) {
		this.obsDates = obsDates;
	}
	public void setProviderList(List<String> providerList) {
		this.providerList = providerList;
	}
	public List<AppointmentData> getAppointmentData() {
		return appointmentData;
	}
	public void setAppointmentData(List<AppointmentData> appointmentData) {
		this.appointmentData = appointmentData;
	}
	
}
