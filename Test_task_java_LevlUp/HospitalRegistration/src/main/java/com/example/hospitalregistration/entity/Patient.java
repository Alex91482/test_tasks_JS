package com.example.hospitalregistration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Patient {

    private Long id;
    private String firstName;
    private String lastName;
    private int passportSerial;
    private String mail;
    private Date dateOfVisit;
    private String doctorSpecialization;
    private String toWhichDoctor;

    public Patient(String firstName, String lastName, int passportSerial, String mail,
                   String doctorSpecialisation, String toWhichDoctor, Date dateOfVisit) {
        this.id = idGenerator();
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportSerial = passportSerial;
        this.mail = mail;
        this.doctorSpecialization = doctorSpecialisation;
        this.toWhichDoctor = toWhichDoctor;
        this.dateOfVisit = dateOfVisit;
    }

    private Long idGenerator(){
        //более "эпической" идеи не возникло так как id должен создаватся до записи в бд
        //потому что он сохраняется в разных таблицах и делать еще один запрос просто для того что бы получить id
        //нереально, например пациент записался к двум врачам тогда будет возвращатся первое найденное id?
        //в том случае если id будут генерироватся бд. Поскольку в проекте используется jdbc то альтернатив как бы не много
        //по этой же причне использовать номер паспорта не кажется хорошей идеей
        Long i =(Long) UUID.randomUUID().getMostSignificantBits();
        System.out.println(i);
        return i; //генерирует так же отрицательные числа
    }
}
