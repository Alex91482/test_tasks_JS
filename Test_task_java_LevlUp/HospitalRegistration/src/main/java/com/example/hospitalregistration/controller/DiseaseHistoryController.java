package com.example.hospitalregistration.controller;


import com.example.hospitalregistration.dao.DiseaseHistoryDAO;
import com.example.hospitalregistration.entity.DiseaseHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DiseaseHistoryController {

    @Autowired
    DiseaseHistoryDAO diseaseHistoryDAO;

    @RequestMapping(value = "/getDiseaseHistory",method = RequestMethod.POST)
    public DiseaseHistory getDiseaseHistoryFromPatientId(long id){
        DiseaseHistory dh = diseaseHistoryDAO.findDiseaseHistoryFromPatientId(id);
        return dh;
    }
}
