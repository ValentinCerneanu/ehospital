package com.ehospital.ehospital.model;

public class Patient extends Person{
	
	private String diagnostic;
	private int saloon;
	private int bed;
	private MedicalPersonnel assignedDoctor;
	
	public Patient(String id, String name, String email, String phoneNumber, int age, String diagnostic, int saloon, int bed) {
		super(id, name, email, phoneNumber, age);
		this.diagnostic = diagnostic;
		this.saloon = saloon;
		this.bed = bed;
	}
	
	public MedicalPersonnel getAssignedDoctor() {
		return assignedDoctor;
	}

	public void setAssignedDoctor(MedicalPersonnel medicalPersonnel) {
		this.assignedDoctor = medicalPersonnel;
	}

	public String getDiagnostic() {
		return diagnostic;
	}
	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}
	public int getSaloon() {
		return saloon;
	}
	public void setSaloon(int saloon) {
		this.saloon = saloon;
	}
	public int getBed() {
		return bed;
	}
	public void setBed(int bed) {
		this.bed = bed;
	}

}
	
	