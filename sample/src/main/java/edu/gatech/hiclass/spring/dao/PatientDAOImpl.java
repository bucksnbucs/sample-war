package edu.gatech.hiclass.spring.dao;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.IDatatype;
import ca.uhn.fhir.model.dstu.composite.QuantityDt;
import ca.uhn.fhir.model.dstu.resource.Appointment;
import ca.uhn.fhir.model.dstu.resource.Condition;
import ca.uhn.fhir.model.dstu.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu.resource.MedicationPrescription;
import ca.uhn.fhir.model.dstu.resource.Observation;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import edu.gatech.hiclass.spring.model.AllergyData;
import edu.gatech.hiclass.spring.model.AppointmentData;
import edu.gatech.hiclass.spring.model.ConditionData;
import edu.gatech.hiclass.spring.model.LabData;
import edu.gatech.hiclass.spring.model.MedicationData;
import edu.gatech.hiclass.spring.model.ObservationData;
import edu.gatech.hiclass.spring.model.PatientData;

public class PatientDAOImpl implements PatientDAO {

	private String conditions;
	private String observations;
	private String medications;
	private String first;
	private String last;
	private String birthdate;
	private String id;
	private String smoker;
	private String gender;
	private String drinker;
	private String maritalstatus;
	private String race;
	private String height;
	private String surgeries;
	private String allergies;
	private String labs;
	private static Map<String, PatientData> pmap;

	public PatientDAOImpl() {
		if (pmap == null)
			pmap = new HashMap<String, PatientData>();
		loadPatient("/patient1.properties");
		loadPatient("/patient2.properties");
		loadPatient("/patient3.properties");
		loadPatient("/patient4.properties");
	}

	private void loadPatient(String filename) {
		Properties props = new Properties();
		InputStream iStream = getClass().getResourceAsStream(filename);
		try {
			props.load(iStream);
			conditions = props.getProperty("conditions");
			observations = props.getProperty("observations");
			medications = props.getProperty("medications");
			first = props.getProperty("first");
			last = props.getProperty("last");
			birthdate = props.getProperty("birthdate");
			id = props.getProperty("id");
			smoker = props.getProperty("smoker");
			gender = props.getProperty("gender");
			drinker = props.getProperty("drinker");
			maritalstatus = props.getProperty("maritalstatus");
			race = props.getProperty("race");
			height = props.getProperty("height");
			surgeries = props.getProperty("surgeries");
			allergies = props.getProperty("allergies");
			labs = props.getProperty("labresults");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		SimpleDateFormat sdfOut = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
		// Create a client (only needed once)
		FhirContext ctx = new FhirContext();

		Bundle bundle;

		PatientData dto = new PatientData();

		dto.setLastName(last);
		dto.setFirstName(first);
		dto.setBirthdate(birthdate);
		dto.setId(id);

		bundle = ctx.newJsonParser().parseBundle(conditions);
		List<Condition> cons = bundle.getResources(Condition.class);
		for (Condition condition : cons) {
			ConditionData cd = new ConditionData();
			String code = condition.getCode().getCoding().get(0).getDisplay()
					.getValueAsString();
			IDatatype onset = condition.getOnsetElement();
			cd.setCondition(code);
			if (onset != null) {
				String onsetDate = (onset.toString().substring(7, 17));
				try {
					cd.setOnsetDate(sdfOut.format(sdfIn.parse(onsetDate)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dto.getConditions().add(cd);
			}
		}

		bundle = ctx.newJsonParser().parseBundle(medications);
		List<MedicationPrescription> prescriptions = bundle
				.getResources(MedicationPrescription.class);
		List<String> pl = new ArrayList<String>();
		for (MedicationPrescription rx : prescriptions) {
			MedicationData md = new MedicationData();
			String medication = rx.getMedication().getDisplay()
					.getValueAsString();
			String prescriber = rx.getPrescriber().getDisplay()
					.getValueAsString();
			String dosage = rx.getDosageInstructionFirstRep().getDoseQuantity()
					.getValue().getValueAsString();
			String units = rx.getDosageInstructionFirstRep().getDoseQuantity()
					.getUnits().getValueAsString();
			Date datePrescribed = rx.getDateWritten().getValue();

			md.setMedication(medication);
			md.setPrescriber(prescriber);
			md.setDosage(dosage + " " + units);
			md.setDate(sdfOut.format(datePrescribed));
			boolean found = false;
			for (MedicationData m : dto.getMedications()) {
				if (m.getMedication().equalsIgnoreCase(md.getMedication()))
					found = true;
			}
			if (!found)
				pl.add(md.getMedication());
			dto.getMedications().add(md);
		}

		bundle = ctx.newJsonParser().parseBundle(labs);
		List<DiagnosticReport> reports = bundle
				.getResources(DiagnosticReport.class);
		for (DiagnosticReport report : reports) {
			LabData ld = new LabData();
			String table = report.getText().getDiv().getValueAsString();
			String name = report.getName().getText().getValueAsString();
			Date issueDate = report.getIssued().getValue();
			Document doc = Jsoup.parse(table);
			Elements tableData = doc.getElementsByClass("hapiTableOfValues");
			Elements rows = tableData.get(0).getElementsByTag("tr");
			for(Element row : rows)
			{
				String[] rowData = new String[5];
				Elements cells = row.getElementsByTag("td");
				if(cells.size() < 5 || cells.get(0).text().equalsIgnoreCase("Name"))
					continue;
				rowData[0] = cells.get(0).text();
				rowData[1] = cells.get(1).text();
				rowData[2] = cells.get(2).text();
				rowData[3] = cells.get(3).text();
				rowData[4] = cells.get(4).text();
				if(rowData[3] != null && !rowData[3].isEmpty())
					ld.getTableData().add(rowData);
			}
			ld.setReport(table);
			ld.setName(name);
			ld.setDate(sdfOut.format(issueDate));
			if(ld.getTableData().size() > 0)
				dto.getLabs().add(ld);
		}

		for(MedicationData md : dto.getMedications())
		{
			String provider = md.getPrescriber();
			List<String> pList = dto.getProviderList();
			boolean found = false;
			for(String p : pList)
			{
				if(p.equalsIgnoreCase(provider))
					found = true;
			}
			if(!found)
			{
				dto.getProviderList().add(provider);
			}
		}
		
		bundle = ctx.newJsonParser().parseBundle(observations);
		List<Observation> obs = bundle
				.getResources(Observation.class);
		Map<String, List<ObservationData>> obsMap = new HashMap<String, List<ObservationData>>();
		for(Observation observation : obs)
		{
			ObservationData od = new ObservationData();
			od.setType(observation.getName().getCoding().get(0).getDisplay().getValueAsString());
			DateTimeDt appdt = (DateTimeDt) observation.getApplies();
			String obsDate = sdfIn.format(appdt.getValue());
			od.setDate(obsDate);
			QuantityDt qty = (QuantityDt) observation.getValue();
			if(qty == null)
				continue;
			od.setValue(qty.getValue().getValueAsString());
			od.setUnits(qty.getUnits().getValueAsString());
			if(obsMap.get(obsDate) == null)
			{
				List<ObservationData> ol = new ArrayList<ObservationData>();
				ol.add(od);
				if(obsMap.size()/dto.getProviderList().size() < 10)
					obsMap.put(obsDate, ol);
			}
			else
			{
				List<ObservationData> ol = obsMap.get(obsDate);
				ol.add(od);
				obsMap.put(obsDate, ol);
			}
		}

		dto.setObservations(obsMap);
		
		for(String obsDate : obsMap.keySet())
			dto.getObsDates().add(obsDate);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy",Locale.US);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
		AppointmentData ad1 = new AppointmentData();
		ad1.setDescription(dto.getConditions().get(0).getCondition());
		ad1.setStatus("Tentative");
		ad1.setStartDate(sdf1.format(new Date()));
		ad1.setStartTime("08:00");
		ad1.setLocation(dto.getProviderList().get(0));
		ad1.setStart(sdf2.format(new Date())+"T08:00:00");
		ad1.setEnd(sdf2.format(new Date())+"T09:00:00");
		dto.getAppointmentData().add(ad1);
		
		AppointmentData ad2 = new AppointmentData();
		if(dto.getConditions().size() > 1)
			ad2.setDescription(dto.getConditions().get(1).getCondition());
		else
			ad2.setDescription(dto.getConditions().get(0).getCondition());
		ad2.setStatus("Tentative");
		ad2.setStartDate("05-07-2015");
		ad2.setStartTime("12:00");
		if(dto.getProviderList().size() > 1)
			ad2.setLocation(dto.getProviderList().get(1));
		else
			ad2.setLocation(dto.getProviderList().get(0));
		ad2.setStart("2015-05-07T12:00:00");
		ad2.setEnd("2015-05-07T13:00:00");
		dto.getAppointmentData().add(ad2);
		
		AppointmentData ad3 = new AppointmentData();
		if(dto.getConditions().size() > 2)
			ad3.setDescription(dto.getConditions().get(2).getCondition());
		else
			ad3.setDescription(dto.getConditions().get(0).getCondition());
		ad3.setStatus("Tentative");
		ad3.setStartDate("05-21-2015");
		ad3.setStartTime("09:30");
		if(dto.getProviderList().size() > 2)
			ad3.setLocation(dto.getProviderList().get(2));
		else
			ad3.setLocation(dto.getProviderList().get(0));
		ad3.setStart("2015-05-21T09:30:00");
		ad3.setEnd("2015-05-21T10:30:00");
		dto.getAppointmentData().add(ad3);
		
		AppointmentData ad4 = new AppointmentData();
		if(dto.getConditions().size() > 3)
			ad4.setDescription(dto.getConditions().get(3).getCondition());
		else
			ad4.setDescription(dto.getConditions().get(0).getCondition());
		ad4.setStatus("Tentative");
		ad4.setStartDate("06-02-2015");
		ad4.setStartTime("14:00");
		ad4.setLocation(dto.getProviderList().get(0));
		ad4.setStart("2015-06-02T14:00:00");
		ad4.setEnd("2015-06-02T15:00:00");
		dto.getAppointmentData().add(ad4);
		
		
		dto.setMedicationList(pl);
		dto.setSmoker(smoker);
		dto.setGender(gender);
		dto.setDrinker(drinker);
		dto.setMaritalStatus(maritalstatus);
		dto.setRace(race);
		dto.setHeight(height);

		String[] allergy = allergies.split(",");
		for (int i = 0; i < allergy.length; i += 4) {
			AllergyData ad = new AllergyData();
			ad.setSubstance(allergy[i]);
			ad.setSeverity(allergy[i + 1]);
			ad.setReaction(allergy[i + 2]);
			ad.setDate(allergy[i + 3]);
			dto.getAllergies().add(ad);
		}

		for (String surgery : surgeries.split(","))
			dto.getSurgeries().add(surgery);

		pmap.put(id, dto);
	}

	@Override
	public PatientData getPatientData(String pid) {
		return pmap.get(pid);
	}

	@Override
	public List<AllergyData> getAllergyData(String pid) {
		return pmap.get(pid).getAllergies();
	}

	@Override
	public List<ConditionData> getConditionData(String pid) {
		return pmap.get(pid).getConditions();
	}

	@Override
	public List<MedicationData> getMedicationData(String pid) {
		return pmap.get(pid).getMedications();
	}

	@Override
	public List<LabData> getLabResults(String pid) {
		return pmap.get(pid).getLabs();
	}

	@Override
	public List<AppointmentData> getAppointmentData(String pid) {
		return pmap.get(pid).getAppointmentData();
	}

}
