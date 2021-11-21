package com.example.hospitalregistration.security;

import com.example.hospitalregistration.dao.VirtualPatientDAO;
import com.example.hospitalregistration.entity.VirtualPatient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private VirtualPatientDAO virtualPatientDAO;

    @Override
    public UserDetails loadUserByUsername(String login)  throws UsernameNotFoundException {
        VirtualPatient virtualPatient = this.virtualPatientDAO.findVirtualPatientByLogin(login);

        if(virtualPatient == null){
            System.out.println("Patient not found! " + login);
            throw new UsernameNotFoundException("Patient " + login + " was not found in the database");
        }
        System.out.println("Found User: " + login);

        UserDetails userDetails = (UserDetails) new VirtualPatient(virtualPatient.getId(), virtualPatient.getPatientId(), virtualPatient.getLogin(), virtualPatient.getEncrytedPassword(), virtualPatient.getRole());
        return userDetails;
    }
}
