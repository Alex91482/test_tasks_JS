package com.example.antipatterns.controller;

import com.example.antipatterns.dao.PatientDAO;
import com.example.antipatterns.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PatientResController {

    @Autowired
    PatientDAO patientDAO;

    @PostMapping(value = "/create-patient")        //сохранение
    public ResponseEntity<?> create(@RequestBody Patient patient) {
        patientDAO.save(patient);              //обдумать альтернативы
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-patient/{id}")     //получение
    public ResponseEntity<Patient> read(@PathVariable(name = "id") long id) {

        final Optional<Patient> p1 = patientDAO.findById(id);
        if (p1.isPresent()) {
            final Patient patient = p1.get();
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@PutMapping(value = "/update-patient/{id}")    //на основе CrudRepository создать метод обновления данных
    public ResponseEntity<?> update(@PathVariable(name = "id") long id) {

        final Optional<Patient> p1 = patientDAO.findById(id);
        if (p1.isPresent()) {
            //save
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @DeleteMapping(value = "/delete-patient/{id}") //удаление
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        final Optional<Patient> p1 = patientDAO.findById(id);
        if (p1.isPresent()) {
            patientDAO.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
