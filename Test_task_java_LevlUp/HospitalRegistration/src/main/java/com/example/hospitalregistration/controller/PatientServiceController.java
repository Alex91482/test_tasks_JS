package com.example.hospitalregistration.controller;


import com.example.hospitalregistration.service.patientservice.PatientService;
import com.example.hospitalregistration.entity.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.swing.text.html.parser.Entity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PatientServiceController {

    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //Date d1 = formater.parse("2021-10-19 16:20");

    private final PatientService patientService;

    @Autowired
    public PatientServiceController (PatientService patientService){
        this.patientService = patientService;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String patientFrom(Model model){
        model.addAttribute("patient1",new Patient1());
        return "page"; //форма для регистрации
    }

    /*@RequestMapping(value="/registration", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Patient1 patient, Model model) {
        model.addAttribute("patient1", patient);
        return "page";*/

    @RequestMapping(value = "/getRecord/{dateFromJs}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> testGetRecord(@PathVariable(name = "dateFromJs") String str){
        List<Date> list = patientService.getRecord(str);
        return new ResponseEntity<>(list, HttpStatus.OK); //метод возвращает список в котором ({dateFromJs} будет переданно как "yyyy-MM-dd") будут возвращенны совавшие даты со временем которые зарезервированны
    }

    @RequestMapping(value = "/allRecord",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> returnRecord(){
        List<Date> list = patientService.allRecord(); //запрос на список дат все которые есть в репозитории
        return new ResponseEntity<Object>(list, HttpStatus.OK);
    }
}
