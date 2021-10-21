package com.example.hospitalregistration.service.patientservice;

import java.util.Date;
import java.util.List;

public interface PatientService {

    boolean createRecord(Date date, String specialization); //создать запись
    List<Date> getRecord(String dateFromJS, String specialization); //вернуть все записи времени по конкретной дате
    List<String> allRecord(); //вернуть все даты записей
    boolean read(Date date, String specialization); //есть ли такая дата
    boolean delete(Date date, String specialization); //удаляет дату

    /*void createRecord(Date date); //создать запись
    List<Date> getRecord(String dateFromJS); //вернуть все записи времени по конкретной дате
    List<String> allRecord(); //вернуть все даты записей
    boolean read(Date date); //есть ли такая дата
    boolean delete(Date date); //удаляет дату*/
}
