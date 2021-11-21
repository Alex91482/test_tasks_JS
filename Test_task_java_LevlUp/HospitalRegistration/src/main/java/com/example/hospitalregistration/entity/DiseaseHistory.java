package com.example.hospitalregistration.entity;


import lombok.Getter;
import lombok.Setter;

public class DiseaseHistory {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Long patientId;

    @Getter @Setter
    private Long doctorId;

    @Getter @Setter
    private String diagnosis;

    @Getter @Setter
    private String recommendations;

    public DiseaseHistory(long patientId, long doctorId, String diagnosis, String recommendations){
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.recommendations = recommendations;
    }
}
/*
CREATE TABLE disease_history
	(
	Id PRIMARY KEY,
	Patient_id BIGINT NOT NULL,
	Doctor_id BIGINT NOT NULL,
	Diagnosis TEXT,
	Recommendations TEXT,
	FOREIGN KEY (patient_id) REFERENCES patient (Id),
	FOREIGN KEY (doctor_id) REFERENCES doctor (Id)
	);
 */
