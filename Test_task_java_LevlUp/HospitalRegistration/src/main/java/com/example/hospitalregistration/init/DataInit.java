package com.example.hospitalregistration.init;


import com.example.hospitalregistration.service.patientservice.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataInit implements ApplicationRunner {

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
            Date d1 = formater.parse("2021-10-19 16:20");
            Date d2 = formater.parse("2021-10-19 16:40");
            Date d3 = formater.parse("2021-10-19 15:20");
            Date d4 = formater.parse("2021-10-19 15:40");

            patientService.createRecord(d1);
            patientService.createRecord(d2);
            patientService.createRecord(d3);
            patientService.createRecord(d4);
        }
        count++;
    }
}
