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
import com.ehospital.ehospital.model.MedicalPersonnel;
import com.ehospital.ehospital.model.Patient;


@RestController 
public class PatientController {
	@GetMapping({"/", "/home"})
	public ModelAndView index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails s = ((UserDetails) principal);
		MedicalPersonnel user = MedicalPersonnelDAO.getInstance().findUserByUsername(s.getUsername());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("user", auth.getName());
		modelAndView.addObject("roles", auth.getAuthorities());
		modelAndView.addObject("user", user);
		
		List<Patient> patients = PatientDAO.getInstance().getPatients();
		modelAndView.addObject("patients", patients);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.POST)
	public ModelAndView addReview(@ModelAttribute("id") @Valid String id, @RequestParam("name") String name, @RequestParam("email") String email,
									@RequestParam("phoneNumber") String phoneNumber, @RequestParam("age") String age,
									@RequestParam("diagnostic") String diagnostic, @RequestParam("saloon") String saloon,
									@RequestParam("bed") String bed, BindingResult result, ModelMap model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails s = ((UserDetails) principal);
		MedicalPersonnel user = MedicalPersonnelDAO.getInstance().findUserByUsername(s.getUsername());
		
		PatientDAO.getInstance().createPatient(name, email, phoneNumber, Integer.valueOf(age), diagnostic, 
																	Integer.valueOf(saloon), Integer.valueOf(bed), user.getId());

		return new ModelAndView("redirect:home");
	}
}