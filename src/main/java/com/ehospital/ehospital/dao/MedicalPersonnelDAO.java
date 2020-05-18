package com.ehospital.ehospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ehospital.ehospital.model.MedicalPersonnel;
import com.ehospital.ehospital.util.DBConnection;

public class MedicalPersonnelDAO {
	
    private MedicalPersonnelDAO() {};
    private static MedicalPersonnelDAO instance;

    public static MedicalPersonnelDAO getInstance() {
        if (instance == null) {
            instance = new MedicalPersonnelDAO();
        }
        return instance;
    }
    
	private Connection connection;
	
	public boolean setUserRole(int id, String role) {
		connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE medical_personnel SET role = ? WHERE id = ?");
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
	}
	
	public List<MedicalPersonnel> getUsers() {
		connection = DBConnection.getConnection();
        List<MedicalPersonnel> medicalPersonnel = new ArrayList<MedicalPersonnel>();
		
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medical_personnel");
           ResultSet rs = preparedStatement.executeQuery();
           while (rs.next()) {
        	   medicalPersonnel.add(new MedicalPersonnel(rs.getString("id"), rs.getString("name"), 
        			   									rs.getString("email"), rs.getString("phoneNumber"),
        			   									rs.getInt("age"), rs.getString("specialization")));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return medicalPersonnel;
	}
    
	public boolean userExists(String email){
        connection = DBConnection.getConnection();
        
         try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medical_personnel WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	resultSet.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public MedicalPersonnel findUserByEmail(String email) {
    	connection = DBConnection.getConnection();
        
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medical_personnel WHERE email = ?");
           preparedStatement.setString(1, email);
           ResultSet rs = preparedStatement.executeQuery();

           if (rs.next()) {
        	   MedicalPersonnel medicalPersonnel = new MedicalPersonnel(rs.getString("id"), rs.getString("name"), 
																		rs.getString("email"), rs.getString("phoneNumber"),
																		rs.getInt("age"), rs.getString("specialization"));
				rs.close();
               return medicalPersonnel;
           }
       } catch (SQLException e) {	
           e.printStackTrace();
       }
       return null;
    }
    
    public MedicalPersonnel findUserByUsername(String username) {
    	connection = DBConnection.getConnection();
        
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medical_personnel WHERE name = ?");
           preparedStatement.setString(1, username);
           ResultSet rs = preparedStatement.executeQuery();

           if (rs.next()) {
        	   MedicalPersonnel medicalPersonnel = new MedicalPersonnel(rs.getString("id"), rs.getString("name"), 
																	rs.getString("email"), rs.getString("password"), rs.getString("phoneNumber"),
																	rs.getInt("age"), rs.getString("specialization"));
				rs.close();
				return medicalPersonnel;
           }
       } catch (SQLException e) {	
           e.printStackTrace();
       }
       return null;
    }
    
    public MedicalPersonnel findUserById(int id) {
    	connection = DBConnection.getConnection();
        
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medical_personnel WHERE id = ?");
           preparedStatement.setInt(1, id);
           ResultSet rs = preparedStatement.executeQuery();

           if (rs.next()) {
        	   MedicalPersonnel medicalPersonnel = new MedicalPersonnel(rs.getString("id"), rs.getString("name"), 
						rs.getString("email"), rs.getString("phoneNumber"),
						rs.getInt("age"), rs.getString("specialization"));
				rs.close();
				return medicalPersonnel;
           }
       } catch (SQLException e) {	
           e.printStackTrace();
       }
       return null;
    }
    
    public boolean createUser(String name, String email, String password, String phoneNumber, int age, String specialization) {
        connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medical_personnel (name, email, password, phoneNumber, age, specialization) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setInt(5, age);
            preparedStatement.setString(6, specialization);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean isPasswordCorrect(String email, String password) {
        connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medical_personnel WHERE email = ? AND password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
            	resultSet.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
