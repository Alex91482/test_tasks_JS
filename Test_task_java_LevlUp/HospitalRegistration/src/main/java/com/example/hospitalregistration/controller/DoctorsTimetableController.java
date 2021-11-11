package com.example.hospitalregistration.controller;

import com.example.hospitalregistration.dao.DoctorsTimetableDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DoctorsTimetableController {

    @Autowired
    DoctorsTimetableDAO doctorsTimetableDAO;

    private final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    private final DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @RequestMapping(value = "/doctorTimetable", method = RequestMethod.GET)
    public String showToMonthTimetable(Model model) throws Exception{
        //строка formater.parse( (format1.format(LocalDate.now().withDayOfMonth(1)) )); означает что берем локальную дату переводим на первый день месяца и приводим к виду гггг.мм.дд на выходе будет строка
        List<ArrayList<String>> list = doctorsTimetableDAO.getDoctorsTimetableFromMonth(format1.format(LocalDate.now().withDayOfMonth(1)));
        model.addAttribute("doctorTimetable", list);
        return "pageTimetableDoctors";
    }

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
        List<ArrayList<String>> list = doctorsTimetableDAO.getDoctorsTimetableFromMonth(month);
        return new ResponseEntity<>(list, HttpStatus.OK); //возвращаем расписание врачей на месяц принимаем строку формата гггг.мм.дд что в принципе не обезательно т.к. происходит приведение к определенному формату
    }

}
