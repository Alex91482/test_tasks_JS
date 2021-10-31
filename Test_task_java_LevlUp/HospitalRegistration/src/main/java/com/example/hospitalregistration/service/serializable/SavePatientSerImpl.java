package com.example.hospitalregistration.service.serializable;


import com.example.hospitalregistration.entity.Recording;
import com.example.hospitalregistration.service.patientservice.PatientServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SavePatientSerImpl {

    private static final Logger logger = LogManager.getLogger(PatientServiceImpl.class);
    private static final String location = "./src/main/resources/config/saveFile.out";

    public void savePatientSerImpl(PatientServiceImpl patientService){ //сериализуем
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(location))){
            objectOutputStream.writeObject(patientService);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public ArrayList<Recording> recoveryPatientSerImpl(){ //десеареализуем
        ArrayList<Recording> arr = new ArrayList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(location))){
            arr = (ArrayList<Recording>) objectInputStream.readObject();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return arr;
    }

    public boolean existenceFile(){ //существует ли файл
        Path path = Paths.get(location);
        if (Files.exists(path)) {
            return true;
        }
        System.out.println("Serialized entries not found");
        logger.info("Serialized entries not found: ./src/main/resources/config/saveFile.out");
        return false;
    }


}
