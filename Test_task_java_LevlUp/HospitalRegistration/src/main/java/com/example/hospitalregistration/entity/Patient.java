package com.example.hospitalregistration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Patient {

    private Long id;
    private String firstName;
    private String lastName;
    private int passportSerial;
    private String mail;
    private Date dateOfVisit;
    private String doctorSpecialization;
    private String toWhichDoctor;

    public Patient(String firstName, String lastName, int passportSerial, String mail,
                   String doctorSpecialisation, String toWhichDoctor, Date dateOfVisit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportSerial =passportSerial;
        this.mail =mail;
        this.doctorSpecialization = doctorSpecialisation;
        this.toWhichDoctor = toWhichDoctor;
        this.dateOfVisit = dateOfVisit;
    }
}
