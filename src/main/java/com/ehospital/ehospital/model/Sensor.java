package com.ehospital.ehospital.model;

import java.util.Observable;

public class Sensor extends Observable {
	
	private String id;
	private String type;
	private String description;
	private MedicalPersonnel assignedDoctor;
	private Patient patient;
	
	public Sensor(String id, String type, String description) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
	}
	
	public void checkValues() {
		notifyObservers(assignedDoctor);
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MedicalPersonnel getAssignedDoctor() {
		return assignedDoctor;
	}
	public void setAssignedDoctor(MedicalPersonnel assignedDoctor) {
		this.assignedDoctor = assignedDoctor;
	}
}