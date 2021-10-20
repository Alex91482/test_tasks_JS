package com.example.hospitalregistration.service.patientservice;


import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PatientServiceImpl implements PatientService{

    private static final List<Date> PATIENT_REPOSITORY_LIST = new ArrayList<>(); //Хранилище зарезервированых дат
    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    @Override //создать запись
    public void createRecord(Date date){
        if(!PATIENT_REPOSITORY_LIST.contains(date)){ //если такой записи нет то создаем
            PATIENT_REPOSITORY_LIST.add(date);
        }
    }

    @Override
    public List<Date> getRecord(String dateFromJs){ //вернуть время для конкретного дня
        List<Date> list = new ArrayList<>(); //дата в формате 2021-10-19
        try {
            Date date=formater.parse(dateFromJs);
            for(int i=0; i<PATIENT_REPOSITORY_LIST.size(); i++) {
                Date x = PATIENT_REPOSITORY_LIST.get(i);
                if (x.getYear() == date.getYear() &
                        x.getMonth() == date.getMonth() &
                        x.getDay() == date.getDay())
                {
                    list.add(x);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override //вернуть все даты записей
    public List<String> allRecord(){
        List<String> list = new ArrayList<>();
        for(int i=0; i<PATIENT_REPOSITORY_LIST.size();i++){
            Date x = PATIENT_REPOSITORY_LIST.get(i);
            list.add(x.toString());
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
