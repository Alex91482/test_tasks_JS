package com.example.antipatterns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.antipatterns.entity.*;
import com.example.antipatterns.dao.*;

@Controller
public class DoctorController {

    @Autowired
    private DoctorDAO doctorDAO;

    @ResponseBody
    @RequestMapping("/get-all-doctor")
    public String index() {
        Iterable<Doctor> all = doctorDAO.findAll();

        StringBuilder sb = new StringBuilder();

        all.forEach(p -> sb.append(p.getFirstName() + " "
                                    + p.getLastName() + " "
                                    + p.getSpetialization() + "<br>"));
        return sb.toString();
    }
}
