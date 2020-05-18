package com.ehospital.ehospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ehospital.ehospital.model.MedicalPersonnel;
import com.ehospital.ehospital.model.Patient;
import com.ehospital.ehospital.model.Sensor;
import com.ehospital.ehospital.util.DBConnection;

public class SensorDAO {
	
    private SensorDAO() {};
    private static SensorDAO instance;

    public static SensorDAO getInstance() {
        if (instance == null) {
            instance = new SensorDAO();
        }
        return instance;
    }
    
	private Connection connection;
	
	public List<Sensor> getSensors() {
		connection = DBConnection.getConnection();
        List<Sensor> sensors = new ArrayList<Sensor>();
		
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sensors");
           ResultSet rs = preparedStatement.executeQuery();
           while (rs.next()) {
        	   MedicalPersonnel assignedDoctor = MedicalPersonnelDAO.getInstance().findUserById(rs.getInt("assignedDoctorId"));
        	   Patient patient = PatientDAO.getInstance().findUserById(rs.getInt("patientId"));
        	   Sensor sensor = new Sensor(rs.getString("id"), rs.getString("type"), rs.getString("description"));
        	   sensor.setAssignedDoctor(assignedDoctor);
        	   sensor.setPatient(patient);
        	   sensors.add(sensor);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return sensors;
	}
    
    public boolean createSensor(String type, String description, String assignedDoctor, String patientId) {
        connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sensors (type, description, assignedDoctorId, patientId) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, assignedDoctor);
            preparedStatement.setString(4, patientId);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
