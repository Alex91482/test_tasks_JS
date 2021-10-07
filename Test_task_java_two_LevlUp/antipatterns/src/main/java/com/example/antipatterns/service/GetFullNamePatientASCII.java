package com.example.antipatterns.service;

import com.example.antipatterns.entity.Patient;

public class GetFullNamePatientASCII extends GetFullNamePatient implements PrintInterface{ //принцип открытости/закрытости OCP

    private Patient patient;
    private String strASCII;

    public void stringToAscii(){
        try{
            StringBuilder sb = new StringBuilder();
            sb.append(patient.getFirstName() + " " + patient.getLastName());
            String data =sb.toString();
            byte[] array = data.getBytes("ASCII");
            String data1 = new String(array, "ASCII");
            strASCII = data1;
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String getFullNamePatient(Patient patient){ //переопределили метод добавили новую функциональность
        this.patient = patient;
        stringToAscii();
        return strASCII;
    }

    @Override
    public String getFullNamePatient(Patient patient, boolean flag){ //что бы нарушить принцип подстановки Барбары Лисков LSP
        if (flag == true) {   // переопределим метод так что он будет выбрасывать исключение вместо строки в формате UTF-8
            throw new IllegalStateException(String.format("String encoding is not valid!")); //везде где использовалась данная функция ожидалась строка а не исключение
        }
        else {               //return encoding ASCII
            try{
                StringBuilder sb = new StringBuilder();
                sb.append(patient.getFirstName() + " " + patient.getLastName());
                String data = sb.toString();
                byte[] array = data.getBytes("ASCII");
                String data1 = new String(array, "ASCII");
                return data1;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return "Oops...";
    }
}
