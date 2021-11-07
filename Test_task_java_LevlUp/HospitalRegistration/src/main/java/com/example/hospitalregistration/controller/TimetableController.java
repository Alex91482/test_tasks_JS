package com.example.hospitalregistration.controller;


import com.example.hospitalregistration.dao.TimetableDAO;
import com.example.hospitalregistration.entity.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

//@Controller
public class TimetableController {  //будет предназначен для зарегистрированного персонала для детального просмотра записей на конкретный день
                                    //либо вообще удален... функционал для управления записями персоналом пока еще на стадии планирования
    //@Autowired
    //private TimetableDAO timetableDAO;

    //получение всех зарегистрированных записей для конкретного врача по id доктора
    //получение всех зарегистрированных записей для конкретного врача по фамилии доктора

    //получение всех зарегистрированных пользователей по фамилии врача и конкретному дню
    /*@RequestMapping(value = "/getRecord/{doctorLastName}/{dateOfVisit}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByTheNameOfTheDoctorOfAll(@PathVariable(name = "doctorLastName") String docLaNa, @PathVariable(name = "dateOfVisit") String dat){
        //List<Timetable> list = timetableDAO.getTimetable();
        return new ResponseEntity<>(list, HttpStatus.OK); //
    }*/
}
