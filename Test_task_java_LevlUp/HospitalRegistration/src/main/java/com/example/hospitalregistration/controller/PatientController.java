package com.example.hospitalregistration.controller;

import com.example.hospitalregistration.dao.PatientDAO;
import com.example.hospitalregistration.entity.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController { //на данный момент тут будут rest архетектура

    @Autowired
    private PatientDAO patientDAO;

    @GetMapping(value = "/patients")
    public ResponseEntity<List<Patient>> getAllPatient() {
        final List<Patient> patients = patientDAO.getPatient();

        return patients != null &&  !patients.isEmpty()
                ? new ResponseEntity<>(patients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/patients/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable(name = "id") long id) {
        final Patient patient = patientDAO.findPatient(id);

        return patient != null
                ? new ResponseEntity<>(patient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable(name = "id") long id) {
        final boolean deleted = patientDAO.deletePatient(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/patients")
    public ResponseEntity<?> create(@RequestBody Patient patient) {
        patientDAO.savePatient(patient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    @PutMapping(value = "/patients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody Patient patient) {
        final boolean updated = patientDAO.update();

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    */
}
