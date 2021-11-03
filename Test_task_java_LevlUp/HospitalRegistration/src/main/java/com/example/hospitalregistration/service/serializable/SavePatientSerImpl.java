package com.example.hospitalregistration.service.serializable;


import com.example.hospitalregistration.entity.Recording;
import com.example.hospitalregistration.service.patientservice.PatientServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

@Service
public class SavePatientSerImpl {

    @Autowired
    PatientServiceImpl patientService;

    private static final Logger logger = LogManager.getLogger(PatientServiceImpl.class);
    private static final String location = "./src/main/resources/config/saveFile.out";

    public void serPatientSerImpl(){ //сериализуем
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(location))){
            objectOutputStream.writeObject(patientService);
            System.out.println("Serialization complete");
        }catch (Exception e){
            System.out.println("Error in serPatientSerImpl method");
            logger.error(e.getMessage());
        }
    }

    public HashSet<Recording> recoveryPatientSerImpl(){ //десеареализуем
        HashSet<Recording> arr = new HashSet<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(location))){
            PatientServiceImpl psi = (PatientServiceImpl) objectInputStream.readObject();
            arr = psi.getAllRecord();
        }catch (Exception e){
            logger.info("Error in recoveryPatientSerImpl \n arr is Empty? " + arr.isEmpty());
            logger.error(e.getMessage());
        }
        return arr;
    }

    public boolean existenceFile(){ //существует ли файл
        Path path = Paths.get(location);
        if (Files.exists(path)) {
            System.out.println("Loading a previously serialized file");
            logger.info("File exists: ./src/main/resources/config/saveFile.out");
            return true;
        }
        System.out.println("Serialized entries not found");
        logger.info("Serialized entries not found: ./src/main/resources/config/saveFile.out");
        return false;
    }


}
