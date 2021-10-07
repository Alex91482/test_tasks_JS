package com.example.antipatterns.init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.antipatterns.entity.*;
import com.example.antipatterns.dao.*;

@Component
public class DataInit implements ApplicationRunner{

    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public DataInit(DoctorDAO doctorDAO, PatientDAO patientDAO){
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        long count = doctorDAO.count();
        long count1 = patientDAO.count();

        if (count == 0) {

            Doctor d1= new Doctor("Snow","Jhon","Therapist");
            Doctor d2= new Doctor("Headey","Lena","Therapist");
            Doctor d3= new Doctor("Williams ","Maisie","Therapist");
            Doctor d4= new Doctor("Madden","Richard","Therapist");

            doctorDAO.save(d1);
            doctorDAO.save(d2);
            doctorDAO.save(d3);
            doctorDAO.save(d4);
        }
        if(count1 == 0){
            Date date1 = dateFormat.parse("2021-10-07");
            Patient p1= new Patient("Smith","Stan",0000000007,"american.dad@mail.com",
                    date1,"Therapist","Jhon Snow");
            Date date2 = dateFormat.parse("2021-10-08");
            Patient p2= new Patient("Sponge","Bob",0002300321,"spnge.bob@mail.com",
                    date2,"Therapist","Madden Richard");

            patientDAO.save(p1);
            patientDAO.save(p2);
        }
    }
}
