package com.example.hospitalregistration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Doctor {

    private Long id;
    private String firstName;
    private String lastName;
    private String specialization ;


    public Doctor(String lastName, String firstName, String specialization){
        this.lastName = lastName;
        this.firstName = firstName;
        this.specialization = specialization;
    }
}
