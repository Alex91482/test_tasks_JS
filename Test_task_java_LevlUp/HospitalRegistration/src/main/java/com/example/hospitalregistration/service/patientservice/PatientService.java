package com.example.hospitalregistration.service.patientservice;

import com.example.hospitalregistration.entity.Recording;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public interface PatientService extends MaintenanceSerialization{

    boolean createRecord(Date date, String specialization); //создать запись
    List<Date> getRecord(String dateFromJS, String specialization); //вернуть все записи времени по конкретной дате
    List<String> allRecord(); //вернуть все даты записей
    boolean read(Date date, String specialization); //есть ли такая дата
    boolean delete(Date date, String specialization); //удаляет дату

}
