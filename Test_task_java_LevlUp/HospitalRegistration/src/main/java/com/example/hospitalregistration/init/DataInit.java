package com.example.hospitalregistration.init;


import com.example.hospitalregistration.entity.Recording;
import com.example.hospitalregistration.service.serializable.SavePatientSerImpl;
import com.example.hospitalregistration.service.patientservice.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

@Component
public class DataInit implements ApplicationRunner {

    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private final PatientService patientService; //репозиторий к которому обращаются при поиске зарезервированных записей

    @Autowired
    public SavePatientSerImpl savePatientSer; //сервис отвечающей за сериализацию и десериализацию

    @Autowired
    public DataInit (PatientService patientService){
        this.patientService = patientService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        long count = 0;

        boolean search  = savePatientSer.existenceFile(); //проверяем есть ди сериализованный файл

        if (!search && count == 0) { //если сериализованного файла не существует и счетчик равен нулю
            Date d1 = formater.parse("2021-10-31 9:00");
            Date d2 = formater.parse("2021-10-31 9:20");
            Date d3 = formater.parse("2021-10-31 9:40");

            String s1 = "THERAPIST";
            String s2 = "OPTOMETRIST";
            String s3 = "OTOLARYNGOLOGIST";

            patientService.createRecord(d1,s1); //терапевт
            patientService.createRecord(d2,s1); //терапевт
            patientService.createRecord(d3,s1); //терапевт

            patientService.createRecord(d1,s2); //окулист
            patientService.createRecord(d2,s2); //окулист
            patientService.createRecord(d3,s2); //окулист

            patientService.createRecord(d1,s3); //отолоринголог
            patientService.createRecord(d2,s3); //отолоринголог
            patientService.createRecord(d3,s3); //отолоринголог

            count++;
        }
        if(search && count == 0){
            patientService.loadRecording(savePatientSer.recoveryPatientSerImpl()); //загружаем в репозиторий то что ранее было сериализованно
            System.out.println("patientService.loadRecording complete");
            count++;
        }

    }
}
