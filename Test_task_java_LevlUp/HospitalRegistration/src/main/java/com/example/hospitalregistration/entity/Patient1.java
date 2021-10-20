package com.example.hospitalregistration.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Patient1 {

    //@Getter @Setter
    //private Long id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private int passportSerial;

    @Getter @Setter
    private String mail;

    @Getter @Setter
    private String doctorSpetialisation;

    @Getter @Setter
    private String toWhichDoctor;

    @Getter @Setter
    private Date dateOfVisit;

    public Patient1(String firstName,String lastName,int passportSerial, String mail,
                    String doctorSpetialisation,String toWhichDoctor,Date dateOfVisit){
        this.firstName=firstName;
        this.lastName=lastName;
        this.passportSerial=passportSerial;
        this.mail=mail;
        this.doctorSpetialisation=doctorSpetialisation;
        this.toWhichDoctor=toWhichDoctor;
        this.dateOfVisit=dateOfVisit;
    }
}
