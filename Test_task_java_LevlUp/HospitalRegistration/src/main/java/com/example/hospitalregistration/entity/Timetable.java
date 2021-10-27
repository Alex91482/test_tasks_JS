package com.example.hospitalregistration.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Timetable {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Long patientId;

    @Getter @Setter
    private Long doctorId;

    @Getter @Setter
    private Date date;

    public Timetable(Long id, Long patientId,Long doctorId,Date date){
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }
}
