package com.example.democoffee.entity.CoffeeMachineA;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event", schema = "public")
public class CoffeeMachineA{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "Id", nullable = false, updatable = false)
    private long Id;                    //айди записи состояния

    @Column(name = "occurred_event", length = 64, nullable = false)
    @Getter @Setter
    private String occurredEvent;       //произошедшее событие

    @Temporal(TemporalType.DATE)
    @Column(name = "event_date", nullable = false)
    @Getter @Setter
    private Date eventTime;             //время когда произошло событие

    @Column(name = "water_fill_level", nullable = false)
    @Getter @Setter
    private int fillTheWaterTank;       //уровень воды в баке

    @Column(name = "coffee_fill_level", nullable = false)
    @Getter @Setter
    private int fillCoffeeTank;         //заполнение бака кофе

    public CoffeeMachineA(){}

    public CoffeeMachineA(String occurredEvent, Date eventTime, int fillTheWaterTank, int fillCoffeeTank){
        this.occurredEvent = occurredEvent;
        this.eventTime = eventTime;
        this.fillTheWaterTank = fillTheWaterTank;
        this.fillCoffeeTank = fillCoffeeTank;
    }
}
