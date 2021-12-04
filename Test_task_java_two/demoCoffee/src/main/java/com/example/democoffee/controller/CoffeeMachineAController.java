package com.example.democoffee.controller;

import com.example.democoffee.entity.CoffeeMachineA.CoffeeMachineA;
import com.example.democoffee.service.CoffeeMachineA.NavigationBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoffeeMachineAController { //localhost:8080/swagger-ui.html


    @Autowired
    NavigationBar navigationBar;

    @GetMapping(value = "/getAmericano")
    public ResponseEntity<Boolean> getAmericano() {             //приготовить америкона на выходе логическое значение и статус
        boolean a = navigationBar.prepareAmericano();
        return a != false
                ? new ResponseEntity<>(a, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/getEspresso")
    public ResponseEntity<Boolean> getEspresso() {              //приготовить эспрессо на выходе логическое значение и статус
        boolean a = navigationBar.prepareEspresso();
        return a != false
                ? new ResponseEntity<>(a, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/getDoubleEspresso")
    public ResponseEntity<Boolean> getDoubleEspresso() {        //приготовить двойной эспрессо на выходе логическое значение и статус
        boolean a = navigationBar.prepareDoubleEspresso();
        return a != false
                ? new ResponseEntity<>(a, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/upWaterTank")
    public ResponseEntity<Boolean> getUpWaterTank() {        //заполнить бак водой
        boolean a = navigationBar.fillTheWaterTank();
        return a != false
                ? new ResponseEntity<>(a, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/upCoffeeTank")
    public ResponseEntity<Boolean> getUpCoffeeTank(){       //заполнить бак кофе
        boolean a = navigationBar.fillCoffeeTank();
        return a != false
                ? new ResponseEntity<>(a, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/getInfoCoffeeMachine")
    public ResponseEntity<String> getInfoCoffeeMachine(){   //получить информацию по наличаю кофе и воды
        String a = navigationBar.showTheCurrentVolumeOfWaterAndCoffee();
        return a != null
                ? new ResponseEntity<>(a, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/getStatistics")                  //получить статистику
    public ResponseEntity<List<CoffeeMachineA>> getStatistics(){
        List<CoffeeMachineA> a = navigationBar.getInformation();
        return a.size() > 0
                ? new ResponseEntity<>(a, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
