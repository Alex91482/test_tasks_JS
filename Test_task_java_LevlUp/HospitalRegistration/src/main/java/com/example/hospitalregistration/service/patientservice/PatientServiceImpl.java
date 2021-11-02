package com.example.hospitalregistration.service.patientservice;

import com.example.hospitalregistration.entity.Recording;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PatientServiceImpl implements PatientService,Serializable{ //сериализация класса после каждого обновления

    private static final HashSet<Recording> PATIENT_REPOSITORY_LIST = new HashSet<>(); //Хранилище зарезервированых дат

    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = LogManager.getLogger(PatientServiceImpl.class);
    private static final long serialVersionUID = 1L;


    @Override //создать запись
    public boolean createRecord(Date date, String specialization){
        Recording recording = new Recording(date,specialization);
        if(PATIENT_REPOSITORY_LIST.contains(recording)){return false;}
        PATIENT_REPOSITORY_LIST.add(recording);
        return true;
    }

    @Override //вернуть время для конкретного дня
    public List<Date> getRecord(String dateFromJs, String specialization){
        List<Date> list = new ArrayList<>(); //дата в формате 2021-10-19
        try {
            for(Recording r : PATIENT_REPOSITORY_LIST){
                Date date = r.getToVisit();
                String x = formater.format(date);
                if (x.equals(dateFromJs) &&
                        specialization.equals(r.getSpecialization())) //совмещаем проверки по дате и специализации
                {
                    list.add(date);
                }
            }
        }catch(Exception e){
            logger.warn(e.getMessage());
        }
        return list;
    }

    @Override //вернуть все даты записей
    public List<String> allRecord(){ //добавить сортировку по записи и по времени
        List<String> list = new ArrayList<>();
        for(Recording r : PATIENT_REPOSITORY_LIST){
            list.add("Specialization: " +
                    r.getSpecialization() +
                    ", date of visit: " +
                    r.getToVisit().toString());
        }
        return list;
    }

    @Override //есть ли такая запись
    public boolean read(Date date,String specialization){
        Recording recording = new Recording(date,specialization);
        if(PATIENT_REPOSITORY_LIST.contains(recording)){
            return true; //запись найдена
        }
        return false; //запись не существует
    }

    @Override //удаляет запись
    public boolean delete(Date date,String specialization){ //протестить данный метод

        Recording recording = new Recording(date,specialization);
        for(Iterator<Recording> i = PATIENT_REPOSITORY_LIST.iterator(); i.hasNext();){
            Recording r = i.next();
            if(r.equals(recording)){
                i.remove();
                return true; //удалено
            }
        }
        //PATIENT_REPOSITORY_LIST.removeIf(i ->(i.equals(recording))); //можно и так но тогда не получим флаг
        return false; //строка не найдена либо уже была удалена
    }
}
