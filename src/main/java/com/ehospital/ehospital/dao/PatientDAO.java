package com.ehospital.ehospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ehospital.ehospital.model.MedicalPersonnel;
import com.ehospital.ehospital.model.Patient;
import com.ehospital.ehospital.util.DBConnection;

public class PatientDAO {
	
    private PatientDAO() {};
    private static PatientDAO instance;

    public static PatientDAO getInstance() {
        if (instance == null) {
            instance = new PatientDAO();
        }
        return instance;
    }
    
	private Connection connection;
	
	public List<Patient> getPatients() {
		connection = DBConnection.getConnection();
        List<Patient> patients = new ArrayList<Patient>();
		
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patients");
           ResultSet rs = preparedStatement.executeQuery();
           while (rs.next()) {
        	   MedicalPersonnel medicalPersonnel = MedicalPersonnelDAO.getInstance().findUserById(rs.getInt("assignedDoctorId"));
        	   
        	   Patient patient = new Patient(rs.getString("id"), rs.getString("name"), 
							rs.getString("email"), rs.getString("phoneNumber"),
							rs.getInt("age"), rs.getString("diagnostic"),
							rs.getInt("saloon"), rs.getInt("bed"));
        	   
        	   patient.setAssignedDoctor(medicalPersonnel);
        	   patients.add(patient);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return patients;
	}
	
    public Patient findUserById(int id) {
    	connection = DBConnection.getConnection();
        
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patients WHERE id = ?");
           preparedStatement.setInt(1, id);
           ResultSet rs = preparedStatement.executeQuery();

           if (rs.next()) {
        	   Patient patient = new Patient(rs.getString("id"), rs.getString("name"), 
						rs.getString("email"), rs.getString("phoneNumber"),
						rs.getInt("age"), rs.getString("diagnostic"),
						rs.getInt("saloon"), rs.getInt("bed"));
				rs.close();
				return patient;
           }
       } catch (SQLException e) {	
           e.printStackTrace();
       }
       return null;
    }
    
    public boolean createPatient(String name, String email, String phoneNumber, int age, String diagnostic, int saloon, int bed, String assignedDoctorId) {
        connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO patients (name, email, phoneNumber, age, diagnostic, saloon, bed, assignedDoctorId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setInt(4, age);
            preparedStatement.setString(5, diagnostic);
            preparedStatement.setInt(6, saloon);
            preparedStatement.setInt(7, bed);
            preparedStatement.setString(8, assignedDoctorId);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
