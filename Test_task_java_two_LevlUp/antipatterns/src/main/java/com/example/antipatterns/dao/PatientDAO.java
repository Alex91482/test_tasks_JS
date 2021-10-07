package com.example.antipatterns.dao;

import com.example.antipatterns.entity.Patient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDAO extends CrudRepository<Patient, Long>{
}
