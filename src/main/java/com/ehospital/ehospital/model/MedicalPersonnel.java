package com.ehospital.ehospital.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MedicalPersonnel extends Person implements Observer{
	
	private String specialization;
	private String password;
	private String role;
	
	public MedicalPersonnel() {
		super();
	}
	
	public MedicalPersonnel(String id, String name, String email, String password, String phoneNumber, int age, String specialization) {
		super(id, name, email, phoneNumber, age);
		this.specialization = specialization;
		this.password = password;
		this.role = "Admin";
	}
	public MedicalPersonnel(String id, String name, String email, String phoneNumber, int age, String specialization) {
		super(id, name, email, phoneNumber, age);
		this.specialization = specialization;
		this.role = "Admin";
	}
	
    @Override
    public void update(Observable o, Object news) {
        
    }

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		return roles;
	}
}
	