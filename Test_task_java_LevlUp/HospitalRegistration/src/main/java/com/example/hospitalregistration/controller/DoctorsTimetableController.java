package com.example.hospitalregistration.controller;

import com.example.hospitalregistration.dao.DoctorsTimetableDAO;

import com.example.hospitalregistration.service.patientservice.PatientForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class DoctorsTimetableController {

    @Autowired
    DoctorsTimetableDAO doctorsTimetableDAO;

    private final DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @RequestMapping(value = "/doctorTimetable", method = RequestMethod.GET)
    public String showToMonthTimetable(Model model){
            //строка format1.format(LocalDate.now().withDayOfMonth(1));
            // означает что берем локальную дату переводим на первый день месяца и приводим к виду гггг.мм.дд
        String date = format1.format(LocalDate.now().withDayOfMonth(1));
        List<Map<String, Object>> list = doctorsTimetableDAO.getDoctorsTimetableFromMonth(date);
        model.addAttribute("doctorTimetable", list);
        return "pageTimetableDoctors"; //страница с расписанием врачей
    }

    @RequestMapping(value = "/doctorNameTimetable",method = RequestMethod.POST) //метод отвечает за ввыод информации о расписании врача по фамилии
    public String getRecordDoctorLastName(Model model, @RequestParam("lastNameDoctor") String lastNameDoctor){
        List<Map<String, Object>> list = doctorsTimetableDAO.getDoctorsTimetableFromLastName(lastNameDoctor);
        model.addAttribute("doctorTimetable", list);
        return "pageTimetableDoctorsLast"; //возвращаем расписание врача по фамилии доктора
    }
    @RequestMapping(value="/doctorNameTimetable", method = RequestMethod.GET)
    public String showPageDoctorLastName(){
        return "pageTimetableDoctorsLast"; //возвращаем страницу в которой нужно ввести фамилию доктора
    }

    /*
    @RequestMapping(value = "/doctorNameTimetable/{id}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRecordDoctorId(@PathVariable(name = "id") long doctorId){
        List<Map<String, Object>> list = doctorsTimetableDAO.getDoctorTimetableFromDoctorId(doctorId);
        return new ResponseEntity<>(list, HttpStatus.OK); //возвращаем расписание врачей по id доктора
    }
    */
    @RequestMapping(value = "/doctorMonthTimetable",method = RequestMethod.POST)
    public String getPageDoctorMonth(Model model, @RequestParam("monthInput") String month){

        System.out.println(month);
        
        //List<Map<String, Object>> list = doctorsTimetableDAO.getDoctorsTimetableFromMonth(month);
        //model.addAttribute("doctorTimetable", list);
        return "pageTimetableDoctorsMonth";
    }
    @RequestMapping(value = "/doctorMonthTimetable",method = RequestMethod.GET)
    public String showPageDoctorMonth(){
        return "pageTimetableDoctorsMonth";
    }

}
