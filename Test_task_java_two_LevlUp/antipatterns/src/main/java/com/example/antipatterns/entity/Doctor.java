package com.example.antipatterns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Doctor")
public class Doctor {

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
    @Column(name="Spetialization", length=128, nullable=false)
    private String spetialization;

    public Doctor(){
    }

    public Doctor(String lastName, String firstName, String spetialization){
        this.lastName = lastName;
        this.firstName = firstName;
        this.spetialization = spetialization;
    }
}
