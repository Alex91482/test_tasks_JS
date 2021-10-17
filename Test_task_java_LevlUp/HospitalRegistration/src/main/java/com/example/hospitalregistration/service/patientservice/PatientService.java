package com.example.hospitalregistration.service.patientservice;

import java.util.Date;
import java.util.List;

public interface PatientService {
    void createRecord(Date date); //создать запись
    List<Date> getRecord(String dateFromJS); //вернуть все записи времени по конкретной дате
    List<Date> allRecord(); //вернуть все даты записей
    boolean read(Date date); //есть ли такая дата
    boolean delete(Date date); //удаляет дату
}
