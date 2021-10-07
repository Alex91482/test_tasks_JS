package com.example.antipatterns.service;

import com.example.antipatterns.entity.Patient;

public interface PrintInterface { //пример нарушения ISP, в интерфейсе существуют методы которые можно разделить на два интерфейса
        String getFullNamePatient(Patient patient, boolean flag); //при создании строки в кодировке UTF-8 этот метод не нужен
        void stringToAscii(); //при создании строки в кодировке UTF-8 этот метод не нужен
        String getFullNamePatient(Patient patient);
    }
