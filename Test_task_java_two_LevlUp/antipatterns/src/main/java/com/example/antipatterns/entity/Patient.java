package com.example.antipatterns.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Patient")
public class Patient {

    @Getter @Setter
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Getter @Setter
    @Column(name="First_name", length=128, nullable=false)
    private String firstName;

    @Getter @Setter
    @Column(name="Last_name", length=128, nullable=false)
    private String lastName;

    @Getter @Setter
    @Column(name="Passport_serial", length=64, nullable=false)
    private int passportSerial;

    @Getter @Setter
    @Column(name="Mail", length=255, nullable=false)
    private String mail;

    @Getter @Setter
    @Temporal(TemporalType.DATE)
    @Column(name="Date_of_visit",nullable=false)
    private Date dateOfVisit;

    @Getter @Setter
    @Column(name="Doctor_spetialisation", length=128, nullable=false)
    private String doctorSpetialisation;

    @Getter @Setter
    @Column(name="Attending_doctor", length=128, nullable=true)
    private String toWhichDoctor;

    public Patient(){}

    public Patient(String lastName, String firstName,int passportSerial,String mail,
                   Date dateOfVisit,String doctorSpetialisation,String toWhichDoctor){
        this.lastName = lastName;
        this.firstName = firstName;
        this.passportSerial = passportSerial;
        this.mail = mail;
        this.dateOfVisit = dateOfVisit;
        this.doctorSpetialisation = doctorSpetialisation;
        this.toWhichDoctor = toWhichDoctor;
    }

    public String getFullName(){               // нарушения принципа единственной ответственности (SRP)
        return (lastName + " " + firstName); //решение это создать отдельный класс отвечающий только за создание строки с именем и фамилией в service
    }
}
