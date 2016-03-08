package edu.gatech.hiclass.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gatech.hiclass.spring.dao.HealthHistoryDAO;
import edu.gatech.hiclass.spring.dao.PatientDAO;
import edu.gatech.hiclass.spring.model.AllergyData;
import edu.gatech.hiclass.spring.model.AppointmentData;
import edu.gatech.hiclass.spring.model.ConditionData;
import edu.gatech.hiclass.spring.model.FamilyHistoryData;
import edu.gatech.hiclass.spring.model.LabData;
import edu.gatech.hiclass.spring.model.MedicalDeviceData;
import edu.gatech.hiclass.spring.model.MedicationData;
import edu.gatech.hiclass.spring.model.PatientData;
import edu.gatech.hiclass.spring.model.TableDTO;
import edu.gatech.hiclass.spring.model.WeightData;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HealthHistoryController {

	private @Autowired HealthHistoryDAO healthHistoryDAO;
	private @Autowired PatientDAO patientDAO;
	
	@RequestMapping(value = "allergies", method = RequestMethod.GET)
	public String getAllergies(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid)
	{
		if(pid != null) {
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);	
		}
		return "HealthHistory/Allergies";
	}
	
	@RequestMapping(value = "conditions", method = RequestMethod.GET)
	public String getConditions(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid)
	{
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "HealthHistory/Conditions";
	}
	
	@RequestMapping(value = "familyhistory", method = RequestMethod.GET)
	public String getFamilyHistory(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid)
	{
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "HealthHistory/FamilyHistory";
	}

	@RequestMapping(value = "medicaldevices", method = RequestMethod.GET)
	public String getMedicalDevices(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid)
	{
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "HealthHistory/MedicalDevices";
	}
	
	@RequestMapping(value = "medications", method = RequestMethod.GET)
	public String getMedications(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid)
	{
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "HealthHistory/Medications";
	}
	
	@RequestMapping(value = "labresults", method = RequestMethod.GET)
	public String getLabResults(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid)
	{
		if(pid != null){
			model.addAttribute("pid",pid);
			PatientData patient = patientDAO.getPatientData(pid);
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);			
		}
		return "HealthHistory/LabResults";
	}
	
	@RequestMapping(value = "/get/history/appointments", method = RequestMethod.GET)
	public @ResponseBody TableDTO getAppointmentData(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		List<AppointmentData> apptList = patientDAO.getAppointmentData(pid);
		AppointmentData[] data = new AppointmentData[apptList.size()];
		data = apptList.toArray(data);
		TableDTO dto = new TableDTO();
		dto.setData(data);
		return dto;
	}
	
	@RequestMapping(value = "/get/history/allergies", method = RequestMethod.GET)
	public @ResponseBody TableDTO getAllergyData(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		List<AllergyData> allergies = patientDAO.getAllergyData(pid);
		AllergyData[] data = new AllergyData[allergies.size()];
		data = allergies.toArray(data);
		TableDTO dto = new TableDTO();
		dto.setData(data);
		return dto;
	}
	
	@RequestMapping(value = "/get/history/conditions", method = RequestMethod.GET)
	public @ResponseBody TableDTO getConditionData(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		List<ConditionData> conditions = patientDAO.getConditionData(pid);
		ConditionData[] data = new ConditionData[conditions.size()];
		data = conditions.toArray(data);
		TableDTO dto = new TableDTO();
		dto.setData(data);
		return dto;
	}
	
	@RequestMapping(value = "/get/history/familyhistory", method = RequestMethod.GET)
	public @ResponseBody TableDTO getFamilyHistoryData(Model model, HttpServletRequest request) {
		List<FamilyHistoryData> familyHistory = healthHistoryDAO.getFamilyHistoryData();
		FamilyHistoryData[] data = new FamilyHistoryData[familyHistory.size()];
		data = familyHistory.toArray(data);
		TableDTO dto = new TableDTO();
		dto.setData(data);
		return dto;
	}

	@RequestMapping(value = "/get/family_tree", method = RequestMethod.GET)
	public @ResponseBody String getFamilyTree(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		List<FamilyHistoryData> familyHistory = healthHistoryDAO.getFamilyHistoryData();
		FamilyHistoryData[] data = new FamilyHistoryData[familyHistory.size()];
		data = familyHistory.toArray(data);
		String html ="<ul>\n";
		html = html + "</ul>\n";
		html = "									<ul>\n" + 
				"										<li><a href=\"#\" data-toggle=\"modal\" data-target=\"#family_details\" onclick=\"document.getElementById('relationship').innerHTML='Sister' \">Sister</a></li>\n" + 
				"										<li>\n" + 
				"											<a href=\"#\">You</a>\n" + 
				"											<ul>\n" + 
				"												<li>\n" + 
				"													<a href=\"#\" data-toggle=\"modal\" data-target=\"#family_details\" onclick=\"document.getElementById('relationship').innerHTML='Father'\">Father</a>\n" + 
				"													<ul>\n" + 
				"														<li>\n" + 
				"															<a href=\"#\" data-toggle=\"modal\" data-target=\"#family_details\" onclick=\"document.getElementById('relationship').innerHTML='Grandfather'\">Grandfather</a>\n" + 
				"														</li>\n" + 
				"														<li>\n" + 
				"															<a href=\"#\" data-toggle=\"modal\" data-target=\"#family_details\" onclick=\"document.getElementById('relationship').innerHTML='Grandmother'\">Grandmother</a>\n" + 
				"														</li>\n" + 
				"													</ul>\n" + 
				"												</li>\n" + 
				"												<li>\n" + 
				"													<a href=\"#\" data-toggle=\"modal\" data-target=\"#family_details\" onclick=\"document.getElementById('relationship').innerHTML='Mother'\">Mother</a>\n" + 
				"													<ul>\n" + 
				"														<li>\n" + 
				"															<a href=\"#\" data-toggle=\"modal\" data-target=\"#family_details\" onclick=\"document.getElementById('relationship').innerHTML='Grandfather'\">Grandfather</a>\n" + 
				"														</li>\n" + 
				"														<li>\n" + 
				"															<a href=\"#\" data-toggle=\"modal\" data-target=\"#family_details\" onclick=\"document.getElementById('relationship').innerHTML='Grandmother'\">Grandmother</a>\n" + 
				"														</li>\n" + 
				"													</ul>\n" + 
				"												</li>							\n" + 
				"											</ul>\n" + 
				"										</li>\n" + 
				"										<li><a href=\"\" data-toggle=\"modal\" data-target=\"#family_details\" onclick=\"document.getElementById('relationship').innerHTML='Brother'\" >Brother</a></li>\n" + 
				"									</ul>";
		return html;
	}
	
	
	@RequestMapping(value = "/get/history/medicaldevices", method = RequestMethod.GET)
	public @ResponseBody TableDTO getMedicalDeviceData(Model model, HttpServletRequest request) {
		List<MedicalDeviceData> medicalDevices = healthHistoryDAO.getMedicalDeviceData();
		MedicalDeviceData[] data = new MedicalDeviceData[medicalDevices.size()];
		data = medicalDevices.toArray(data);
		TableDTO dto = new TableDTO();
		dto.setData(data);
		return dto;
	}

	@RequestMapping(value = "/get/history/medications", method = RequestMethod.GET)
	public @ResponseBody TableDTO getMedicationData(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		List<MedicationData> medications = patientDAO.getMedicationData(pid);
		MedicationData[] data = new MedicationData[medications.size()];
		data = medications.toArray(data);
		TableDTO dto = new TableDTO();
		dto.setData(data);
		return dto;
	}
	
	@RequestMapping(value = "/get/history/labresults", method = RequestMethod.GET)
	public @ResponseBody TableDTO getLabResultsData(Model model, HttpServletRequest request, @RequestParam(value = "pid", required=false) String pid) {
		List<LabData> labs = patientDAO.getLabResults(pid);
		LabData[] data = new LabData[labs.size()];
		data = labs.toArray(data);
		TableDTO dto = new TableDTO();
		dto.setData(data);
		return dto;
	}
}
