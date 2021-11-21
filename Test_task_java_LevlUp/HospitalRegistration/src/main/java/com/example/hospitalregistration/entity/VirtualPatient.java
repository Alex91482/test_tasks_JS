package com.example.hospitalregistration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VirtualPatient {

    private Long id;
    private Long patientId;
    private String encrytedPassword;
    private String login;
    private String role;

    public VirtualPatient(Long patientId, String encrytedPassword, String login){
        //this.id =id;
        this.patientId = patientId;
        this.encrytedPassword = encrytedPassword;
        this.login = login;
        this.role = "PATIENT";
    }
}
