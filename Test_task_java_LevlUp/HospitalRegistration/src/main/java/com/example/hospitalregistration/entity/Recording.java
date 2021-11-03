package com.example.hospitalregistration.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class Recording implements Serializable{

    @Getter @Setter
    private Date toVisit;

    @Getter @Setter
    private String specialization;

    public Recording(Date toVisit, String specialization){
        this.toVisit=toVisit;
        this.specialization=specialization;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Recording recording = (Recording) obj;
        return(
                (specialization != null && specialization.equals(recording.getSpecialization()))
                && toVisit != null && toVisit.getTime() == recording.getToVisit().getTime()
                );
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((specialization == null) ? 0 : specialization.hashCode());
        result = prime * result + ((toVisit == null) ? 0 : toVisit.hashCode());
        return result;
    }
}
