package com.example.hospitalregistration.controller;


import com.example.hospitalregistration.service.patientservice.PatientService;
import com.example.hospitalregistration.entity.Patient;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("page",new Patient());
        return "page";
    }

    @RequestMapping(value = "/allRecord",method = RequestMethod.GET)
    public ResponseEntity<Object> returnRecord(){

        List<Date> list = patientService.allRecord(); //запрос на список дат

        /*List<JSONObject> entities = new ArrayList<>();
        for(Date date : list){
            JSONObject entity = new JSONObject();
            entity.put("reserved ", date);
            entities.add(entity);
        }*/
        JSONObject entities = new JSONObject();
        for(int i=0;i<list.size();i++){
        entities.put("reserved",list.get(i));
        System.out.println(list.get(i));
        }

        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
}
