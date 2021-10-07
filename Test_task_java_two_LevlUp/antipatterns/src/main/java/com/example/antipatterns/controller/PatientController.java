package com.example.antipatterns.controller;

import com.example.antipatterns.dao.PatientDAO;
import com.example.antipatterns.entity.Patient;
import com.example.antipatterns.service.GetFullNamePatient;
import com.example.antipatterns.service.GetFullNamePatientASCII;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientController { //Принцип инверсии зависимостей (DIP)

    @Autowired
    PatientDAO patientDAO;

    @ResponseBody
    @RequestMapping("/get-name-patient")
    public String index() {
        Iterable<Patient> all = patientDAO.findAll();

        GetFullNamePatientASCII gfnpa = new GetFullNamePatientASCII();

        StringBuilder sb = new StringBuilder();
        all.forEach(p -> sb.append(gfnpa.getFullNamePatient(p) + "<br>"));

        return sb.toString();
    }
}
