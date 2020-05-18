package com.ehospital.ehospital.model;

import java.util.Random;

public class Sensor extends Thread {
	
	private String idSensor;
	private String type;
	private String description;
	private MedicalPersonnel assignedDoctor;
	private Patient patient;
	
	public Sensor(String id, String type, String description) {
		super();
		this.idSensor = id;
		this.type = type;
		this.description = description;
	}
	
	public void run() {
		Random random = new Random();
		int low = 36;
		int high = 39;
		
		while(true) {
			try {

				int temperature = random.nextInt(high-low) + low;
				
				String notification = "Sensor type: " + type + " Assigned Doctor: " + assignedDoctor.getName() + " Patient: " + patient.toString() + " Temperature read: " + temperature;
				if(temperature > 37)
					System.out.println("ALERT " + notification);
				else 
					System.out.println("INFO " + notification);
				
				Thread.sleep(5000);
				
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(String id) {
		this.idSensor = id;
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
};