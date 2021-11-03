package com.example.hospitalregistration.service.patientservice;

import com.example.hospitalregistration.entity.Recording;

import java.util.HashSet;

public interface MaintenanceSerialization {

    void loadRecording(HashSet<Recording> recordingHashSet); //загрузка записей из сериализованного файла
    HashSet<Recording> getAllRecord();

}
