package com.ehospital.ehospital.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehospital.ehospital.dao.MedicalPersonnelDAO;
import com.ehospital.ehospital.model.MedicalPersonnel;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
  
    private MedicalPersonnelDAO medicalPersonnelDAO;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    	
    	medicalPersonnelDAO = MedicalPersonnelDAO.getInstance();
    	
    	MedicalPersonnel medicalPersonnel = medicalPersonnelDAO.findUserByUsername(username);
        if (medicalPersonnel == null) {
            throw new UsernameNotFoundException(
              "No user found with username: " + username);
        }
        
        return new org.springframework.security.core.userdetails.User(medicalPersonnel.getName(), medicalPersonnel.getPassword(), getAuthorities(medicalPersonnel.getRoles()));
    }
     
    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
