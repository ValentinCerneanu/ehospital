package com.ehospital.ehospital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ehospital.ehospital.dao.MedicalPersonnelDAO;
import com.ehospital.ehospital.dao.PatientDAO;
import com.ehospital.ehospital.dao.SensorDAO;
import com.ehospital.ehospital.model.MedicalPersonnel;
import com.ehospital.ehospital.model.Patient;
import com.ehospital.ehospital.model.Sensor;


@RestController 
public class SensorController {

	@GetMapping({"/sensors"})
	public ModelAndView index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails s = ((UserDetails) principal);
		MedicalPersonnel user = MedicalPersonnelDAO.getInstance().findUserByUsername(s.getUsername());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sensors");
		
		modelAndView.addObject("user", auth.getName());
		modelAndView.addObject("roles", auth.getAuthorities());
		modelAndView.addObject("user", user);

		List<Sensor> sensors = SensorDAO.getInstance().getSensors();
		modelAndView.addObject("sensors", sensors);
		
		for(Sensor sensor: sensors) {
			sensor.start();
		}
		
		List<Patient> patients = PatientDAO.getInstance().getPatients();
		modelAndView.addObject("patients", patients);
	
		return modelAndView;
	}
	
	@RequestMapping(value = {"/sensors"}, method = RequestMethod.POST)
	public ModelAndView addReview(@ModelAttribute("id") @Valid String id, @RequestParam("type") String type, 
									@RequestParam("description") String description, @RequestParam("patient") String patient, 
									BindingResult result, ModelMap model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails s = ((UserDetails) principal);
		MedicalPersonnel user = MedicalPersonnelDAO.getInstance().findUserByUsername(s.getUsername());
		
		SensorDAO.getInstance().createSensor(type, description, user.getId(), patient);

		return new ModelAndView("redirect:sensors");
	}

}