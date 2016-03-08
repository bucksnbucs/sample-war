package edu.gatech.hiclass.spring.model;

public class MedicationData {
	private String medication;
	private String prescriber;
	private String dosage;
	private String date;
	
	public String getMedication() {
		return medication;
	}
	public void setMedication(String medication) {
		this.medication = medication;
	}
	public String getPrescriber() {
		return prescriber;
	}
	public void setPrescriber(String prescriber) {
		this.prescriber = prescriber;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String toString() {
		return medication;
	}
}
