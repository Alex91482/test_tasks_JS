package com.example.antipatterns.service;

import com.example.antipatterns.entity.Patient;

public class Human { //в данном классе демонстрируется принцип инверсии зависимостей (DIP)

    private PrintInterface printInterface;

    public Human(PrintInterface printInterface){ //зависимость не от конкретных деталей а от абстракций
        this.printInterface = printInterface;
    }

    public void printPatient(Patient patient){
        printInterface.getFullNamePatient(patient);
    }

}

class Human1{ //не соблюдение принципа инверсии зависимостей (DIP)

    private void printPatient(Patient patient){

        GetFullNamePatientASCII gfnpa = new GetFullNamePatientASCII(); //в данном случае класс зависит от конкретного класса
        gfnpa.getFullNamePatient(patient);

    }
}
