package com.example.democoffee.repository;

import com.example.democoffee.entity.CoffeeMachineA.CoffeeMachineA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CoffeeMachineARepository extends JpaRepository<CoffeeMachineA, Long> {

    @Query(value = "SELECT * FROM event ORDER BY event_date DESC LIMIT 1", nativeQuery = true)
    CoffeeMachineA getTheLatestEntry();                                                     //получить последнюю запись из бд

    @Query(value = "INSERT INTO event (occurred_event, event_date, water_fill_level, coffee_fill_level) values ( ?, ?, 1000, ?);", nativeQuery = true)
    void setFillTheWaterTank(String occurredEvent, Date eventDate, int coffeeFillLevel); //заполнить бак с водой

    @Query(value = "INSERT INTO event (occurred_event, event_date, water_fill_level, coffee_fill_level) values ( ?, ?, ?, 1000);", nativeQuery = true)
    void setFillCoffeeTank(String occurredEvent, Date eventDate, int waterFillLevel);    //заполнить бак с кофе

}

