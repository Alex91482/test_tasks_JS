package com.example.hospitalregistration.controller;


import com.example.hospitalregistration.service.patientservice.PatientForm;
import com.example.hospitalregistration.service.patientservice.PatientService;
import com.example.hospitalregistration.entity.*;

import org.json.JSONArray;
import org.json.JSONObject;
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

    /*@CrossOrigin
    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String patientFrom(Model model){
        model.addAttribute("patient1",new Patient1());
        return "page"; //форма для регистрации
    }
    @CrossOrigin
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Patient1 patient, Model model) {
        model.addAttribute("patient1", patient);
        return "page";
    }*/

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
        String doctorSpetialisation = patientForm.getDoctorSpetialisation();
        String toWhichDoctor = patientForm.getToWhichDoctor();
        Date dateOfVisit = patientForm.getDateOfVisit();

        System.out.println("firstName " + firstName );
        System.out.println("lastName " + lastName);
        System.out.println("passportSerial " + passportSerial);
        System.out.println("mail " + mail);
        System.out.println("doctorSpetialisation " + doctorSpetialisation);
        System.out.println("toWhichDoctor " + toWhichDoctor);
        System.out.println("dateOfVisit " + dateOfVisit);

        if(/*firstName != null & lastName != null & 0 < passportSerial & mail !=null
            & doctorSpetialisation != null &*/ dateOfVisit != null){ //проверяем валидность данных
            Patient1 patient1 = new Patient1(firstName,lastName,passportSerial,mail,doctorSpetialisation,toWhichDoctor,dateOfVisit);
            //patientDAO.save(patient1);
            patientService.createRecord(dateOfVisit);
            return "redirect:/patientList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "page";
    }

    @CrossOrigin//(origins = "http://localhost:8080")
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
