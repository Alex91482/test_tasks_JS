package com.example.hospitalregistration.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Recording {

    @Getter @Setter
    private Date toVisit;

    @Getter @Setter
    private String specialization;

    public Recording(Date toVisit, String specialization){
        this.toVisit=toVisit;
        this.specialization=specialization;
    }
}
