package edu.gatech.hiclass.spring.dao;

import java.util.List;

import edu.gatech.hiclass.spring.model.AllergyData;
import edu.gatech.hiclass.spring.model.AppointmentData;
import edu.gatech.hiclass.spring.model.ConditionData;
import edu.gatech.hiclass.spring.model.LabData;
import edu.gatech.hiclass.spring.model.MedicationData;
import edu.gatech.hiclass.spring.model.PatientData;

public interface PatientDAO {
	
	public PatientData getPatientData(String pid);
	
	public List<AllergyData> getAllergyData(String pid);
	
	public List<ConditionData> getConditionData(String pid);
	
	public List<MedicationData> getMedicationData(String pid);
	
	public List<LabData> getLabResults(String pid);

	public List<AppointmentData> getAppointmentData(String pid);
}
