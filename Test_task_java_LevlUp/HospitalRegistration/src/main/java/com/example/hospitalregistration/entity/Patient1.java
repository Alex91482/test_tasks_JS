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

    //@Getter @Setter
    private Date dateOfVisit;

    @Getter @Setter
    private String monthDay;

    private String time;

    public Patient1(){
    }
}
