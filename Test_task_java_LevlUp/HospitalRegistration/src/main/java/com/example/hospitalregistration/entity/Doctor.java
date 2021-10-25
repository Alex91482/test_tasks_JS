package com.example.hospitalregistration.entity;

import lombok.Getter;
import lombok.Setter;

public class Doctor {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private String specialization ;

    //public Doctor(){
    //}

    public Doctor(long id, String lastName, String firstName, String specialization){
        this.lastName = lastName;
        this.firstName = firstName;
        this.specialization = specialization;
    }
}
