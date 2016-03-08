package edu.gatech.hiclass.spring.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.IDatatype;
import ca.uhn.fhir.model.dstu.resource.Appointment;
import ca.uhn.fhir.model.dstu.resource.Condition;
import edu.gatech.hiclass.spring.model.AppointmentData;
import edu.gatech.hiclass.spring.model.ConditionData;
import edu.gatech.hiclass.spring.model.FamilyHistoryData;
import edu.gatech.hiclass.spring.model.MedicalDeviceData;

@PropertySource("classpath:patient1.properties")
public class HealthHistoryDAOImpl implements HealthHistoryDAO {
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	 
	@Value("${conditions}")
	private String con;
	
	@Value("${appointment}")
	private String appt;
	
	@Value("${medications}")
	private String meds;
	
	@Value("${labresults}")
	private String labs;

	@Override
	public List<AppointmentData> getAppointmentData() {

		SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd-yyyy",Locale.US);
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm",Locale.US);
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:00",Locale.US);

		// Create a client (only needed once)
		FhirContext ctx = new FhirContext();
		
		Bundle bundle = ctx.newJsonParser().parseBundle(appt);
		
		List<AppointmentData> appts = new ArrayList<AppointmentData>();
		Date date = new Date();
		int i = 1;

		for (Appointment appt : bundle.getResources(Appointment.class)) {
			AppointmentData dto = new AppointmentData();
			Date newDate = new Date(date.getTime() + i*120*60*60*1000);
			dto.setDescription(appt.getDescription().getValueAsString());
			dto.setStatus(appt.getStatus().getValueAsString());
			dto.setStartDate(sdfDate.format(newDate));
			dto.setStartTime(sdfTime.format(newDate));
			dto.setLocation("Dr. Rogers");
			dto.setStart(sdfDateTime.format(newDate));
			dto.setEnd(sdfDateTime.format(DateUtils.addHours(newDate, 1)));
			appts.add(dto);
			i++;
		}

		return appts;
	}

	private List<ConditionData> getConditionData() {
		List<ConditionData> conditions = new ArrayList<ConditionData>();
		SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		SimpleDateFormat sdfOut = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
		FhirContext ctx = new FhirContext();
		Bundle bundle = ctx.newJsonParser().parseBundle(con);
		List<Condition> cons = bundle.getResources(Condition.class);
		for (Condition condition : cons) {
			ConditionData dto = new ConditionData();
			String code = condition.getCode().getCoding().get(0).getDisplay().getValueAsString();
			IDatatype onset = condition.getOnsetElement();
			dto.setCondition(code);
			if(onset != null)
			{
				String onsetDate = (onset.toString().substring(7,17));
				try {
					dto.setOnsetDate(sdfOut.format(sdfIn.parse(onsetDate)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conditions.add(dto);
			}
		}
		
		return conditions;
	}

	@Override
	public List<FamilyHistoryData> getFamilyHistoryData() {
		List<ConditionData> conditions = getConditionData();
		List<FamilyHistoryData> data = new ArrayList<FamilyHistoryData>();
		
		for(int i=0; i<10; i++)
		{
			FamilyHistoryData dto = new FamilyHistoryData();
			ConditionData cd = conditions.get(new Random().nextInt(conditions.size()));
			dto.setCondition(cd.getCondition());
			dto.setOnsetDate(cd.getOnsetDate());
			data.add(dto);
		}
		return data;
	}

	@Override
	public List<MedicalDeviceData> getMedicalDeviceData() {
		List<MedicalDeviceData> devices = new ArrayList<MedicalDeviceData>();
		devices.add(new MedicalDeviceData("ACME Medical Devices", "Pacemaker 5000", "AC-PM-5000"));
		devices.add(new MedicalDeviceData("Acme Devices, Inc.", "Patient Monitor", "A-1-1"));
		devices.add(new MedicalDeviceData("Holter Devices", "Heart Monitoring Recorder System (ECG)","TLC5000"));
		return devices;
	}

}
