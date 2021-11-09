package com.example.hospitalregistration.controller;

import com.example.hospitalregistration.dao.DoctorsTimetableDAO;
import com.example.hospitalregistration.entity.DoctorsTimetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorsTimetableController {

    @Autowired
    DoctorsTimetableDAO doctorsTimetableDAO;

    @RequestMapping(value = "/doctorNameTimetable/{lastNameDoctor}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRecordDoctorLastName(@PathVariable(name = "lastNameDoctor") String lastNameDoctor){
        List<ArrayList<String>> list = doctorsTimetableDAO.getDoctorsTimetableFromLastName(lastNameDoctor);
        return new ResponseEntity<>(list, HttpStatus.OK); //возвращаем расписание врачей по фамилии доктора
    }

    @RequestMapping(value = "/doctorNameTimetable/{id}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRecordDoctorId(@PathVariable(name = "id") long doctorId){
        List<ArrayList<String>> list = doctorsTimetableDAO.getDoctorTimetableFromDoctorId(doctorId);
        return new ResponseEntity<>(list, HttpStatus.OK); //возвращаем расписание врачей по id доктора
    }

    @RequestMapping(value = "/doctorTimetable/{month}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDoctorTimetable(@PathVariable(name = "month") String month){
        List <DoctorsTimetable> list = doctorsTimetableDAO.getDoctorsTimetableFromMonth(month);
        return new ResponseEntity<>(list, HttpStatus.OK); //возвращаем расписание врачей на месяц принимаем строку формата гггг.мм.дд что в принципе не обезательно т.к. происходит приведение к определенному формату
    }
}
