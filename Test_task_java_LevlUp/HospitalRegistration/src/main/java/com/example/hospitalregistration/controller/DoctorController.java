package com.example.hospitalregistration.controller;

import com.example.hospitalregistration.dao.DoctorDAO;
import com.example.hospitalregistration.entity.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorDAO doctorDAO;

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public String showDoctor(Model model) {
        List<Doctor> list = doctorDAO.getDoctors();

        model.addAttribute("doctorInfos", list);

        return "doctorsPage";
    }
}
