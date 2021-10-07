package com.example.antipatterns.dao;

import com.example.antipatterns.entity.Doctor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDAO extends CrudRepository<Doctor, Long>{
}
