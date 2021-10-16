package com.example.hospitalregistration.service.patientservice;


import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private static final List<Date> PATIENT_REPOSITORY_LIST = new ArrayList<>(); //Хранилище зарезервированых дат

    @Override //создать запись
    public void createRecord(Date date){
        if(!PATIENT_REPOSITORY_LIST.contains(date)){ //если такой записи нет то создаем
            PATIENT_REPOSITORY_LIST.add(date);
        }
    }

    @Override //вернуть все даты записей
    public List<Date> allRecord(){
        //List<Date> list = new ArrayList<>(PATIENT_REPOSITORY_LIST.size());
        //Collections.copy(list, PATIENT_REPOSITORY_LIST);
        List<Date> list = new ArrayList<>();
        for(int i=0; i<PATIENT_REPOSITORY_LIST.size();i++){
            Date x = PATIENT_REPOSITORY_LIST.get(i);
            list.add(x);
        }
        return list;
    }

    @Override //есть ли такая дата
    public boolean read(Date date){
        if(PATIENT_REPOSITORY_LIST.contains(date)){
            return true; //запись существует
        }
        return false; //запись не существует
    }

    @Override //удаляет дату
    public boolean delete(Date date){
        if(PATIENT_REPOSITORY_LIST.contains(date)){ //если запись существует
            PATIENT_REPOSITORY_LIST.remove(date);
            return true; //запись удалена
        }
        return false; //строка не найдена дибо удалена
    }
}
