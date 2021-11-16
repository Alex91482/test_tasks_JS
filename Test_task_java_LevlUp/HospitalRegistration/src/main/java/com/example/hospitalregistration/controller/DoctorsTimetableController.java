package com.example.hospitalregistration.controller;

import com.example.hospitalregistration.dao.DoctorsTimetableDAO;

import org.springframework.beans.factory.annotation.Autowired;
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
            // означает что берем локальную дату переводим на первый день месяца и приводим к виду гггг-мм-дд
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


    @RequestMapping(value = "/doctorDayTimetable",method = RequestMethod.POST)
    public String getPageDoctorDay(Model model, @RequestParam("dateInput") String day){ //вывод расписания врачей за конкретный день
        if(!day.equals("")){
            List<Map<String, Object>> list = doctorsTimetableDAO.getDoctorsTimetableFromDay(day);
            model.addAttribute("doctorTimetable", list);
        }
        return "pageTimetableDoctorsDay"; //возвращаем страницу с записями расписания врачей за конкретный день
    }
    @RequestMapping(value = "/doctorDayTimetable",method = RequestMethod.GET)
    public String showPageDoctorDay(){return "pageTimetableDoctorsDay";}


    @RequestMapping(value = "/doctorMonthTimetable",method = RequestMethod.POST)
    public String getPageDoctorMonth(Model model, @RequestParam("dateInput") String month){
        if(!month.equals("")){
            List<Map<String, Object>> list = doctorsTimetableDAO.getDoctorsTimetableFromMonth(month);
            model.addAttribute("doctorTimetable", list);
        }
        return "pageTimetableDoctorsMonth"; //возвращаем страницу с записями расписания врачей за месяц
    }
    @RequestMapping(value = "/doctorMonthTimetable",method = RequestMethod.GET)
    public String showPageDoctorMonth(){
        return "pageTimetableDoctorsMonth";
    }

    /*
    @RequestMapping(value = "/doctorIdTimetable/{id}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRecordDoctorId(@PathVariable(name = "id") long doctorId){
        List<Map<String, Object>> list = doctorsTimetableDAO.getDoctorTimetableFromDoctorId(doctorId);
        return new ResponseEntity<>(list, HttpStatus.OK); //возвращаем расписание врачей по id доктора
    }
    */
}
