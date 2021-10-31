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

    private static final List<Recording> PATIENT_REPOSITORY_LIST = new ArrayList<>(); //Хранилище зарезервированых дат
    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = LogManager.getLogger(PatientServiceImpl.class);
    private static final long serialVersionUID = 1L;


    @Override //создать запись
    public boolean createRecord(Date date, String specialization){

        for(int i=0; i<PATIENT_REPOSITORY_LIST.size(); i++){
            Date dateVis = PATIENT_REPOSITORY_LIST.get(i).getToVisit();
            if(date.getTime() == dateVis.getTime()){
                String str = PATIENT_REPOSITORY_LIST.get(i).getSpecialization();
                if(str.equals(specialization)){
                    return false;
                }
            }
        }
        PATIENT_REPOSITORY_LIST.add(new Recording(date,specialization));
        return true;
    }

    @Override
    public List<Date> getRecord(String dateFromJs, String specialization){ //вернуть время для конкретного дня
        List<Date> list = new ArrayList<>(); //дата в формате 2021-10-19
        try {
            Date date=formater.parse(dateFromJs);
            for(int i=0; i<PATIENT_REPOSITORY_LIST.size(); i++) {
                Date x = PATIENT_REPOSITORY_LIST.get(i).getToVisit();
                if (x.getYear() == date.getYear() &
                        x.getMonth() == date.getMonth() &
                        x.getDay() == date.getDay()) //первая проверка на совпадение дат
                //if(formater.format(x).equals(date))
                {
                    String str = PATIENT_REPOSITORY_LIST.get(i).getSpecialization();
                    if(str.equals(specialization)){ //вторая проверка на совпадение специализации
                        list.add(x);
                    }
                }
            }
        }catch(Exception e){
            logger.warn(e.getMessage());
        }
        return list;
    }

    @Override //вернуть все даты записей
    public List<String> allRecord(){
        List<String> list = new ArrayList<>();
        for(int i=0; i<PATIENT_REPOSITORY_LIST.size();i++){
            Date x = PATIENT_REPOSITORY_LIST.get(i).getToVisit();
            String str = PATIENT_REPOSITORY_LIST.get(i).getSpecialization();
            list.add("Specialization: " + str + ", date of visit: " + x.toString());
        }
        return list;
    }

    @Override //есть ли такая дата
    public boolean read(Date date,String specialization){
        for(int i=0; i<PATIENT_REPOSITORY_LIST.size();i++){
            Date x = PATIENT_REPOSITORY_LIST.get(i).getToVisit();
            String str = PATIENT_REPOSITORY_LIST.get(i).getSpecialization();
            if(x.getTime() == date.getTime() & str.equals(specialization)){
                return true; //запись существует
            }
        }
        return false; //запись не существует
    }

    @Override //удаляет дату
    public boolean delete(Date date,String specialization){

        for(int i=0; i<PATIENT_REPOSITORY_LIST.size();i++){
            Date x = PATIENT_REPOSITORY_LIST.get(i).getToVisit();
            String str = PATIENT_REPOSITORY_LIST.get(i).getSpecialization();
            if(x.getTime() == date.getTime() & str.equals(specialization)){
                PATIENT_REPOSITORY_LIST.remove(i);
                return true; //запись удалена
            }
        }
        return false; //строка не найдена дибо удалена
    }
}
