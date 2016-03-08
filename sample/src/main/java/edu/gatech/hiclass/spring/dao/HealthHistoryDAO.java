package edu.gatech.hiclass.spring.dao;

import java.util.List;

import edu.gatech.hiclass.spring.model.AllergyData;
import edu.gatech.hiclass.spring.model.AppointmentData;
import edu.gatech.hiclass.spring.model.ConditionData;
import edu.gatech.hiclass.spring.model.FamilyHistoryData;
import edu.gatech.hiclass.spring.model.LabData;
import edu.gatech.hiclass.spring.model.MedicalDeviceData;
import edu.gatech.hiclass.spring.model.MedicationData;

public interface HealthHistoryDAO {
	
	public List<AppointmentData> getAppointmentData();
	
	public List<FamilyHistoryData> getFamilyHistoryData();
	
	public List<MedicalDeviceData> getMedicalDeviceData();

}
