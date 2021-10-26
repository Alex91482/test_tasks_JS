package com.example.hospitalregistration.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Patient {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private int passportSerial;

    @Getter @Setter
    private String mail;

    @Getter @Setter
    private Date dateOfVisit;

    @Getter @Setter
    private String doctorSpecialization;

    @Getter @Setter
    private String toWhichDoctor;

    public Patient(long id,String firstName,String lastName,int passportSerial, String mail,
                   Date dateOfVisit, String doctorSpecialization,String toWhichDoctor){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.passportSerial=passportSerial;
        this.mail=mail;
        this.dateOfVisit=dateOfVisit;
        this.doctorSpecialization=doctorSpecialization;
        this.toWhichDoctor=toWhichDoctor;
    }
}
