package com.ehospital.ehospital.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ehospital.ehospital.dao.MedicalPersonnelDAO;
import com.ehospital.ehospital.model.MedicalPersonnel;
import com.ehospital.ehospital.service.SecurityServiceImpl;


@RestController 
public class AuthenticationController {
	
	@Autowired
    private SecurityServiceImpl securityService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return new ModelAndView("redirect:/login");
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel();
		modelAndView.addObject("user", medicalPersonnel); 
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUserAccount (@ModelAttribute("user") @Valid MedicalPersonnel medicalPersonnel, BindingResult result, WebRequest request, Errors errors) {    
		ModelAndView modelAndView = new ModelAndView();
		MedicalPersonnelDAO medicalPersonnelDAO = MedicalPersonnelDAO.getInstance();
		boolean userExists = medicalPersonnelDAO.userExists(medicalPersonnel.getEmail());
		
		if (!result.hasErrors() && !userExists) {
			medicalPersonnelDAO.createUser(medicalPersonnel.getName(), medicalPersonnel.getEmail(), bCryptPasswordEncoder.encode(medicalPersonnel.getPassword()), 
					medicalPersonnel.getPhoneNumber(), medicalPersonnel.getAge(), medicalPersonnel.getSpecialization());
			securityService.autoLogin(medicalPersonnel.getName(), medicalPersonnel.getPassword());
			modelAndView = new ModelAndView("redirect:/home");
		} else {
			modelAndView.setViewName("register"); 
		}
		return modelAndView;
	}
	

}
