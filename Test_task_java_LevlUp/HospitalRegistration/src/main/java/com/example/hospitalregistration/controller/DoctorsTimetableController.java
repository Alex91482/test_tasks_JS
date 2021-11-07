package com.example.hospitalregistration.controller;

import com.example.hospitalregistration.dao.DoctorsTimetableDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorsTimetableController {

    @Autowired
    DoctorsTimetableDAO doctorsTimetableDAO;

    @RequestMapping(value = "/doctorNameTimetable",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> returnRecord(/*String lastNameDoctor*/){
        List<ArrayList<String>> list = doctorsTimetableDAO.getDoctorsTimetableFromLastName("House");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctorNameTimetableDouble",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> returnRecording(/*long doctorId*/){
        List<ArrayList<String>> list = doctorsTimetableDAO.getDoctorTimetableFromDoctorId(2);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
