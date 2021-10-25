package com.example.hospitalregistration.init;


import com.example.hospitalregistration.entity.Recording;
import com.example.hospitalregistration.service.patientservice.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataInit implements ApplicationRunner { //добавить поиск в папке config сериализованного репозитория

    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private final PatientService patientService;

    @Autowired
    public DataInit (PatientService patientService){
        this.patientService = patientService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        long count = 0;

        if (count == 0) {
            Date d1 = formater.parse("2021-10-31 9:00");
            Date d2 = formater.parse("2021-10-31 10:00");
            Date d3 = formater.parse("2021-10-31 11:00");
            Date d4 = formater.parse("2021-10-31 13:00");
            Date d5 = formater.parse("2021-10-31 14:00");

            String s1 = "THERAPIST";
            String s2 = "OPTOMETRIST";
            String s3 = "OTOLARYNGOLOGIST";

            patientService.createRecord(d1,s1); //терапевт
            patientService.createRecord(d2,s1); //терапевт
            patientService.createRecord(d3,s1); //терапевт

            patientService.createRecord(d1,s2); //окулист
            patientService.createRecord(d2,s2); //окулист
            patientService.createRecord(d5,s2); //окулист

            patientService.createRecord(d3,s3); //отолоринголог
            patientService.createRecord(d4,s3); //отолоринголог
            patientService.createRecord(d5,s3); //отолоринголог
        }
        count++;
    }
}
