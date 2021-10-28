package com.example.hospitalregistration.controller;


import com.example.hospitalregistration.service.patientservice.PatientForm;
import com.example.hospitalregistration.service.patientservice.PatientService;
import com.example.hospitalregistration.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    public PatientServiceController (PatientService patientService){
        this.patientService = patientService;
    }

    @CrossOrigin
    @RequestMapping(value="/patientList", method = RequestMethod.GET)
    public String patientList(){
        return "patientList"; //список зарегестрировавшихся
    }

    @CrossOrigin
    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String patientFrom(Model model){
        PatientForm patientForm = new PatientForm();
        model.addAttribute("patientForm",patientForm);
        return "page"; //форма для регистрации
    }

    @CrossOrigin
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String savePatient(Model model, @ModelAttribute("patientForm") PatientForm patientForm){
        String firstName = patientForm.getFirstName();
        String lastName = patientForm.getLastName();
        int passportSerial = patientForm.getPassportSerial();
        String mail = patientForm.getMail();
        String doctorSpecialisation = patientForm.getDoctorSpecialisation();
        String toWhichDoctor = patientForm.getToWhichDoctor();
        Date dateOfVisit = patientForm.getDateOfVisit();

        boolean doesEntry = patientService.read(dateOfVisit,doctorSpecialisation);
        if(dateOfVisit != null && doctorSpecialisation != null && !doesEntry){ //проверяем валидность данных
            //Patient patient = new Patient(firstName,lastName,passportSerial,mail,doctorSpecialisation,toWhichDoctor,dateOfVisit);
            //patientDAO.savePatient(patient);
            patientService.createRecord(dateOfVisit,doctorSpecialisation);
            return "redirect:/patientList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "page";
    }

    @CrossOrigin
    @RequestMapping(value = "/getRecord/{specFromJs}/{dateFromJs}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> testGetRecord(@PathVariable(name = "specFromJs") String spec,@PathVariable(name = "dateFromJs") String dat){
        List<Date> list = patientService.getRecord(dat,spec);
        return new ResponseEntity<>(list, HttpStatus.OK); //метод возвращает список в котором ({dateFromJs} будет переданно как "yyyy-MM-dd") будут возвращенны совавшие даты со временем которые зарезервированны
    }

    @RequestMapping(value = "/allRecord",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> returnRecord(){
        List<String> list = patientService.allRecord(); //запрос на список дат все которые есть в репозитории
        return new ResponseEntity<Object>(list, HttpStatus.OK);
    }
}
