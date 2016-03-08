package edu.gatech.hiclass.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.gatech.hiclass.spring.model.MedicationData;
import edu.gatech.hiclass.spring.model.PatientData;
import edu.gatech.hiclass.spring.dao.PatientDAO;

@Controller
public class LoginController {
	private @Autowired PatientDAO patientDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login2(ModelMap model, Principal principal, @RequestParam(value = "pid", required=false) String pid) {
					
		if(pid != null) {
			model.addAttribute("pid", pid);
			PatientData patient = patientDAO.getPatientData(pid);
			List<MedicationData> medicationList = new ArrayList<MedicationData>();
			medicationList.add(patient.getMedications().get(0));
			medicationList.add(patient.getMedications().get(1));
			model.addAttribute("medicationList",medicationList);
			model.addAttribute("appointmentList",patient.getAppointmentData());
			String full_name = patient.getFirstName() + " " + patient.getLastName();
			model.addAttribute("patient_name",full_name);		
		}
		if(principal != null)
		{
			if(pid == null)
			{
				String name = principal.getName();
				if(name.equalsIgnoreCase("ajustice"))
					pid = "3.802001361-01";
				else if(name.equalsIgnoreCase("dbeard"))
					pid = "3.955750000-01";
				else if(name.equalsIgnoreCase("dbolton"))
					pid = "3.000001751-01";
				else if(name.equalsIgnoreCase("iflores"))
					pid = "3.568001602-01";
				else
					pid = "3.955750000-01";
				model.addAttribute("pid",pid);
				PatientData patient = patientDAO.getPatientData(pid);
				List<MedicationData> medicationList = new ArrayList<MedicationData>();
				medicationList.add(patient.getMedications().get(0));
				medicationList.add(patient.getMedications().get(1));
				model.addAttribute("medicationList",medicationList);
				model.addAttribute("appointmentList",patient.getAppointmentData());
				String full_name = patient.getFirstName() + " " + patient.getLastName();
				model.addAttribute("patient_name",full_name);
			}
			return "index";
		}
			
		else
			return "login";
	}
	 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model, Principal principal, @RequestParam(value = "pid", required=false) String pid) {
		if(pid != null)
			model.addAttribute("pid", pid);
		if(principal != null)
			return "index";
		return "login";
	}

	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}
}